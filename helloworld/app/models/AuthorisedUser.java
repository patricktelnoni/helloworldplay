package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import java.util.*;
import javax.persistence.*;
import io.ebean.*;


@Entity
@Table(name = "user")
public class AuthorisedUser extends Model implements Subject
{
    @Id
    public Long id;

    public String userName;

    public String password;

    @ManyToMany
    public List<SecurityRole> roles;

    @ManyToMany
    public List<UserPermission> permissions;

    public static final Finder<Long, AuthorisedUser> find = new Finder<>(AuthorisedUser.class);

    @Override
    public List<? extends Role> getRoles()
    {
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions()
    {
        return permissions;
    }

    @Override
    public String getIdentifier()
    {
        return userName;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }



    public static AuthorisedUser findByUserName(String userName)
    {

        return find.query().fetch("roles").where()
                   .eq("userName",
                       userName)
                   .findOne();
    }
    public static List<AuthorisedUser> doLogin(String userName, String Password)
    {

        return find.query().fetch("roles").where().eq("password", Password)
                   .eq("userName",
                       userName)
                   .findList();
    }
}