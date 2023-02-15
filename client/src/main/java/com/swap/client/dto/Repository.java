package com.swap.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Repository implements Serializable {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("private")
    private Boolean _private;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean get_private() {
        return _private;
    }

    public void set_private(Boolean _private) {
        this._private = _private;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(id, that.id) && Objects.equals(nodeId, that.nodeId) && Objects.equals(name, that.name) && Objects.equals(fullName, that.fullName) && Objects.equals(_private, that._private) && Objects.equals(htmlUrl, that.htmlUrl) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nodeId, name, fullName, _private, htmlUrl, description);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", _private=" + _private +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
