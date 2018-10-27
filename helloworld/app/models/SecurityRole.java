/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import be.objectify.deadbolt.java.models.Role;

import javax.persistence.*;


import io.ebean.*;

import java.util.List;

@Entity
public class SecurityRole extends Model implements Role
{
    @Id
    public Long id;

    public String name;

    @ManyToMany
    public List<AuthorisedUser> users;

    public static final Finder<Long, SecurityRole> find = new Finder<>(SecurityRole.class);


    public void setUsers(List<AuthorisedUser> users) {
        this.users = users;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SecurityRole findByName(String role){
        return find.query().where().eq("name", role).findOne();

    }

}