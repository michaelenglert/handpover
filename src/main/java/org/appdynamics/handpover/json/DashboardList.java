package org.appdynamics.handpover.json;

import java.util.List;

/**
 * Created by michi on 31.10.16.
 **/
@SuppressWarnings({"WeakerAccess", "unused"})
public class DashboardList {
    public String description;
    public String id;
    public String name;
    public String version;
    public String nameUnique;
    public String builtIn;
    public String createdBy;
    public String createdOn;
    public String modifiedBy;
    public String modifiedOn;
    public String missingAssociatedEntities;
    public String widgets;
    public String securityToken;
    public String sharingRevoked;
    public String warRoom;
    public String template;
    public String templateEntityType;
    public String minutesBeforeAnchorTime;
    public String startTime;
    public String endTime;
    public String refreshInterval;
    public String backgroundColor;
    public String color;
    public String height;
    public String width;
    public String canvasType;
    public String layoutType;
    public List<DashboardProperties> properties;
    public String uiState;

    public DashboardList() {
    }

    public DashboardList(String description, String id, String name, String version, String nameUnique, String builtIn, String createdBy, String createdOn, String modifiedBy, String modifiedOn, String missingAssociatedEntities, String widgets, String securityToken, String sharingRevoked, String warRoom, String template, String templateEntityType, String minutesBeforeAnchorTime, String startTime, String endTime, String refreshInterval, String backgroundColor, String color, String height, String width, String canvasType, String layoutType, List<DashboardProperties> properties, String uiState) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.version = version;
        this.nameUnique = nameUnique;
        this.builtIn = builtIn;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
        this.missingAssociatedEntities = missingAssociatedEntities;
        this.widgets = widgets;
        this.securityToken = securityToken;
        this.sharingRevoked = sharingRevoked;
        this.warRoom = warRoom;
        this.template = template;
        this.templateEntityType = templateEntityType;
        this.minutesBeforeAnchorTime = minutesBeforeAnchorTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.refreshInterval = refreshInterval;
        this.backgroundColor = backgroundColor;
        this.color = color;
        this.height = height;
        this.width = width;
        this.canvasType = canvasType;
        this.layoutType = layoutType;
        this.properties = properties;
        this.uiState = uiState;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBuiltIn() {
        return builtIn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public List<DashboardProperties> getProperties() {
        return properties;
    }

    public String getMissingAssociatedEntities() {
        return missingAssociatedEntities;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCanvasType() {
        return canvasType;
    }

    public String getColor() {
        return color;
    }

    public String getHeight() {
        return height;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public String getMinutesBeforeAnchorTime() {
        return minutesBeforeAnchorTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public String getNameUnique() {
        return nameUnique;
    }

    public String getRefreshInterval() {
        return refreshInterval;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public String getSharingRevoked() {
        return sharingRevoked;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getTemplate() {
        return template;
    }

    public String getTemplateEntityType() {
        return templateEntityType;
    }

    public String getUiState() {
        return uiState;
    }

    public String getVersion() {
        return version;
    }

    public String getWarRoom() {
        return warRoom;
    }

    public String getWidgets() {
        return widgets;
    }

    public String getWidth() {
        return width;
    }
}

