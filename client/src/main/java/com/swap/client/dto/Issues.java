package com.swap.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Issues implements Serializable {

    @JsonProperty("title")
    private String title;
    @JsonProperty("labels")
    private List<Label> labels;
    @JsonProperty("user")
    private User user;
    @JsonProperty("created_at")
    private Date createdAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issues issues = (Issues) o;
        return Objects.equals(title, issues.title) && Objects.equals(labels, issues.labels) && Objects.equals(user, issues.user) && Objects.equals(createdAt, issues.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, labels, user, createdAt);
    }

    @Override
    public String toString() {
        return "Issues{" +
                "title='" + title + '\'' +
                ", labels=" + labels +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }

    public class Label implements Serializable{
        @JsonProperty("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Label label = (Label) o;
            return Objects.equals(name, label.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Label{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
