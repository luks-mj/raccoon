package com.mujun.mng.commons.utils;

import com.mujun.mng.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static User getCurrentHr() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
