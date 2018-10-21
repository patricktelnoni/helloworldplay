package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
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

    public static AuthorisedUser findByUserName(String userName)
    {

        return find.query().where()
                   .eq("userName",
                       userName)
                   .findOne();
    }
}