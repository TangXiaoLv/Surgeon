package com.surgeon.weaving.core;

import com.surgeon.weaving.core.exceptions.SurgeonException;
import com.surgeon.weaving.core.interfaces.Continue;
import com.surgeon.weaving.core.interfaces.IMaster;
import com.surgeon.weaving.core.interfaces.ISurgeon;
import com.surgeon.weaving.core.interfaces.Replacer;

import java.lang.reflect.InvocationTargetException;

import static android.text.TextUtils.isEmpty;
import static com.surgeon.weaving.core.ASPConstant.AFTER;
import static com.surgeon.weaving.core.ASPConstant.BEFORE;

/**
 * The master {@link ReplaceAbleAspect},Used for find replace function.
 */
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

    /**
     * Create {@link IMaster} and find replace function.
     *
     * @param namespace The namespace of function
     * @param prefix    {@link ASPConstant#BEFORE},{@link ASPConstant#EMPTY}, {@link
     *                  ASPConstant#AFTER}
     * @param function  The name of function.
     * @param target    Original instance or {@link TargetHandle}
     * @param args      Input params
     * @return new result
     */
    Object findAndInvoke(String namespace,
                         String prefix,
                         String function,
                         Object target,
                         Object[] args) throws SurgeonException {
        if (isEmpty(namespace) || isEmpty(function)) return Continue.class;
        try {
            String masterPath = PREFIX + namespace.replace(".", "_");
            IMaster master = InnerCache.getInstance().getMaster(masterPath);
            if (master == null) {
                Class<?> clazz = Class.forName(masterPath);
                master = (IMaster) clazz.newInstance();
                InnerCache.getInstance().putMaster(masterPath, master);
            }

            //copy args
            Object[] copyOfArgs = new Object[args.length + 1];
            System.arraycopy(args, 0, copyOfArgs, 1, args.length);
            copyOfArgs[0] = target;

            //runtime repalce
            Object wrapper;
            String runtimeKey = namespace + "." + function;
            if (AFTER.equals(prefix)) {
                wrapper = InnerCache.getInstance().popReplaceWapper(runtimeKey);
            } else {
                wrapper = InnerCache.getInstance().getReplaceWapper(runtimeKey);
            }

            if (wrapper != Continue.class) {
                ReplaceWapper resultWapper = (ReplaceWapper) wrapper;
                if (!resultWapper.isReplacer()) return resultWapper.getResult();

                Replacer replacer = (Replacer) resultWapper.getResult();
                if (BEFORE.equals(prefix)) {
                    replacer.before(copyOfArgs);
                } else if (AFTER.equals(prefix)) {
                    replacer.after(copyOfArgs);
                } else {
                    return replacer.replace(copyOfArgs);
                }
                return null;
            }

            //static repalce
            SurgeonMethod replaceMethod = master.find(prefix + function);
            if (replaceMethod != null) {
                return invoke(replaceMethod, copyOfArgs);
            }
        } catch (ClassNotFoundException ignored) {
            //ignored
        } catch (Exception e) {
            throw new SurgeonException(e);
        }
        return Continue.class;
    }

    private Object invoke(SurgeonMethod method, Object[] args)
            throws IllegalAccessException,
            InstantiationException,
            InvocationTargetException {
        Object ownerInstance = InnerCache.getInstance().getMethodOwner(method.getOwner());
        //cache owner instance
        if (ownerInstance == null) {
            Class clazz = method.getOwner();
            ownerInstance = clazz.newInstance();
            if (ownerInstance instanceof ISurgeon && ((ISurgeon) ownerInstance).singleton()) {
                InnerCache.getInstance().putMethodOwner(method.getOwner(), ownerInstance);
            }
        }
        if (ownerInstance instanceof ISurgeon) {
            return method.getNewMethod().invoke(ownerInstance, args);
        }
        return Continue.class;
    }
}
