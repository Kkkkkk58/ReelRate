package ru.kslacker.reelrate.dataaccess.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import ru.kslacker.reelrate.dataaccess.builders.OptionalInfoUserBuilder;
import ru.kslacker.reelrate.dataaccess.builders.PasswordUserBuilder;
import ru.kslacker.reelrate.dataaccess.builders.ReelRateUserUserBuilder;
import ru.kslacker.reelrate.dataaccess.builders.UsernameUserBuilder;
import ru.kslacker.reelrate.dataaccess.enums.UserRole;
import ru.kslacker.reelrate.exceptions.UserBuilderException;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reelrate_user_id")
    @ToString.Exclude
    private ReelRateUser reelRateUser;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    protected User(
            String username,
            String email,
            String password,
            ReelRateUser reelRateUser,
            Set<UserRole> roles) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.reelRateUser = reelRateUser;
        this.roles = roles;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() :
                getClass().hashCode();
    }

    public static class UserBuilder implements
            UsernameUserBuilder,
            PasswordUserBuilder,
            ReelRateUserUserBuilder,
            OptionalInfoUserBuilder {

        private String username;
        private String password;
        private String email = null;
        private ReelRateUser reelRateUser;
        private Set<UserRole> roles = new HashSet<>();

        @Override
        public OptionalInfoUserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public OptionalInfoUserBuilder addRole(UserRole role) {
            roles.add(role);
            return this;
        }

        @Override
        public ReelRateUserUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public OptionalInfoUserBuilder withReelRateUser(ReelRateUser reelRateUser) {
            this.reelRateUser = reelRateUser;
            return this;
        }

        @Override
        public PasswordUserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public User build() {
            if (username == null || password == null) {
                throw UserBuilderException.missingCredentials();
            }
            if (reelRateUser == null) {
                throw UserBuilderException.missingReelRateUser();
            }
            if (roles.isEmpty()) {
                roles.add(UserRole.USER);
            }

            return new User(
                    username,
                    email,
                    password,
                    reelRateUser,
                    roles
            );
        }
    }
}