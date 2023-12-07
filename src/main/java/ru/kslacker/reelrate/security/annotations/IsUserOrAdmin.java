package ru.kslacker.reelrate.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("""
hasAnyRole(T(ru.kslacker.reelrate.dataaccess.enums.UserRole).ADMIN, T(ru.kslacker.reelrate.dataaccess.enums.UserRole).USER)
""")
public @interface IsUserOrAdmin {
}
