package com.surgeon.weaving.core;

import com.surgeon.weaving.core.exceptions.SurgeonException;
import com.surgeon.weaving.core.interfaces.Continue;
import com.surgeon.weaving.core.interfaces.IMaster;
import com.surgeon.weaving.core.interfaces.ISurgeon;
import com.surgeon.weaving.core.interfaces.Replacer;

import java.lang.reflect.InvocationTargetException;

import static android.text.TextUtils.isEmpty;

class MasterFinder {

    private static final String PREFIX = "com.surgeon.weaving.masters.Master_";

    private MasterFinder() {
    }

    private static class Lazy {
        static MasterFinder sMasterFinder = new MasterFinder();
    }

    static MasterFinder getInstance() {
        return Lazy.sMasterFinder;
    }

    Object findAndInvoke(String namespace, String fullName, Object target, Object[] args) throws SurgeonException {
        if (isEmpty(namespace) || isEmpty(fullName)) return Continue.class;
        try {
            String masterPath = PREFIX + namespace.replace(".", "_");
            IMaster master = InnerCache.getInstance().getMaster(masterPath);
            if (master == null) {
                Class<?> clazz = Class.forName(masterPath);
                master = (IMaster) clazz.newInstance();
                InnerCache.getInstance().putMaster(masterPath, master);
            }

            //copy arrary
            Object[] newArgs = new Object[args.length + 1];
            newArgs[0] = target;
            System.arraycopy(args, 0, newArgs, 1, args.length);

            //runtime repalce
            String methodPath = namespace + "." + fullName;
            Object result = InnerCache.getInstance().popResultWapper(methodPath);
            if (result != Continue.class) {
                ResultWapper resultWapper = (ResultWapper) result;
                if (resultWapper.isReplacer()) {
                    return ((Replacer) resultWapper.getResult()).invoke(newArgs);
                }
                return resultWapper.getResult();
            }

            //static repalce
            SurgeonMethod newMethod = master.find(fullName);
            if (newMethod != null) {
                return invoke(newMethod, newArgs);
            }
        } catch (Exception e) {
            throw new SurgeonException(e);
        }
        return Continue.class;
    }

    private Object invoke(SurgeonMethod method, Object[] args)
            throws InvocationTargetException,
            IllegalAccessException,
            InstantiationException {
        Object ownerInstance = InnerCache.getInstance().getMethodOwner(method.getOwner());
        if (ownerInstance == null) {
            Class clazz = method.getOwner();
            ownerInstance = clazz.newInstance();
            InnerCache.getInstance().putMethodOwner(method.getOwner(), ownerInstance);
        }

        if (ownerInstance instanceof ISurgeon) {
            return method.getNewMethod().invoke(ownerInstance, args);
        }
        return Continue.class;
    }
}
