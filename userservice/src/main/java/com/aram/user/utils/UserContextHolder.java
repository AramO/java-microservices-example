package com.aram.user.utils;

import org.springframework.util.Assert;

/**
 *
 * @author aram
 */
public class UserContextHolder {
    
    private static final ThreadLocal<UserContext> USER_CONTEXT = new ThreadLocal<UserContext>();

    public static final UserContext getContext(){
        UserContext context = USER_CONTEXT.get();

        if (context == null) {
            context = createEmptyContext();
            USER_CONTEXT.set(context);
        }
        return USER_CONTEXT.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        USER_CONTEXT.set(context);
    }

    public static final UserContext createEmptyContext(){
        return new UserContext();
    }
}
