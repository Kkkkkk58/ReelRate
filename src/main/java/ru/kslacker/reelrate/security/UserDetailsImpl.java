package ru.kslacker.reelrate.security;

import java.util.Collection;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kslacker.reelrate.dataaccess.entities.ReelRateUser;
import ru.kslacker.reelrate.dataaccess.entities.User;
import ru.kslacker.reelrate.exceptions.ReelRateUserIsNotLinkedException;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UUID getReelRateUserId() {
        ReelRateUser reelRateUser = user.getReelRateUser();
        if (reelRateUser == null) {
            throw new ReelRateUserIsNotLinkedException();
        }

        return reelRateUser.getId();
    }
}
