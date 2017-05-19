package com.surgeon.weaving.core;

import com.surgeon.weaving.core.interfaces.IMaster;

import java.lang.reflect.InvocationTargetException;

import static android.text.TextUtils.isEmpty;

class MasterFinder {

    private static final String MASTER = "Master_";

    private MasterFinder() {
    }

    private static class Lazy {
        static MasterFinder sRouterHelper = new MasterFinder();
    }

    static MasterFinder getInstance() {
        return Lazy.sRouterHelper;
    }

    Object findAndInvoke(String declaringTypeName, String fullName, Object target, Object[] args) {
        if (isEmpty(declaringTypeName) || isEmpty(fullName)) return void.class;
        try {
            String masterPath = MASTER + declaringTypeName.replace(".", "_");
            IMaster master = SurgeonCache.getInstance().getMaster(masterPath);
            if (master == null) {
                Class<?> clazz = Class.forName(masterPath);
                master = (IMaster) clazz.newInstance();
                SurgeonCache.getInstance().putMaster(masterPath, master);
            }
            SurgeonMethod newMethod = master.find(fullName);
            if (newMethod != null) {
                return invoke(master, newMethod, target, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return void.class;
    }

    private Object invoke(IMaster master, SurgeonMethod method, Object target, Object[] args)
            throws InvocationTargetException,
            IllegalAccessException,
            InstantiationException {
        Object ownerInstance = SurgeonCache.getInstance().getOwner(master);
        if (ownerInstance == null) {
            Class clazz = method.getOwner();
            ownerInstance = clazz.newInstance();
            SurgeonCache.getInstance().putTarget(master, ownerInstance);
        }
        return method.getNewMethod().invoke(ownerInstance, target, args);
    }
}
