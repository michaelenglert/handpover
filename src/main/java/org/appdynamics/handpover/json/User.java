package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

import java.util.List;

/**
 * Created by michi on 03.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class User {
    public int id;
    public int version;
    public String name;
    public Boolean nameUnique;
    public Boolean builtIn;
    public String createdBy;
    public String createdOn;
    public String modifiedBy;
    public String modifiedOn;
    public String displayName;
    public String password;
    public List<Roles> roles;
    public List<Integer> accountRoleIds;
    public String email;
    public String providerUniqueName;
    public String securityProviderType;
    public String mobileDeviceUuids;

    public User(){
    }

    public User(int id, int version, String name, Boolean nameUnique, Boolean builtIn, String createdBy, String createdOn, String modifiedBy, String modifiedOn, String displayName, String password, List<Roles> roles, List<Integer> accountRoleIds, String email, String providerUniqueName, String securityProviderType, String mobileDeviceUuids) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.nameUnique = nameUnique;
        this.builtIn = builtIn;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
        this.displayName = displayName;
        this.password = password;
        this.roles = roles;
        this.accountRoleIds = accountRoleIds;
        this.email = email;
        this.providerUniqueName = providerUniqueName;
        this.securityProviderType = securityProviderType;
        this.mobileDeviceUuids = mobileDeviceUuids;
    }

    public String toString() {
        return  id + Globals.SPACE + version + Globals.SPACE + name + Globals.SPACE + nameUnique + Globals.SPACE + builtIn + Globals.SPACE + createdBy + Globals.SPACE + createdOn + Globals.SPACE + modifiedBy + Globals.SPACE + modifiedOn + Globals.SPACE + displayName + Globals.SPACE + password + Globals.SPACE + roles + Globals.SPACE + accountRoleIds + Globals.SPACE + email + Globals.SPACE + providerUniqueName + Globals.SPACE + securityProviderType + Globals.SPACE + mobileDeviceUuids;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public Boolean getNameUnique() {
        return nameUnique;
    }

    public Boolean getBuiltIn() {
        return builtIn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public List<Integer> getAccountRoleIds() {
        return accountRoleIds;
    }

    public String getEmail() {
        return email;
    }

    public String getProviderUniqueName() {
        return providerUniqueName;
    }

    public String getSecurityProviderType() {
        return securityProviderType;
    }

    public String getMobileDeviceUuids() {
        return mobileDeviceUuids;
    }
}
