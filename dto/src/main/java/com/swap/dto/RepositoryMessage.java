package com.swap.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class RepositoryMessage implements Serializable {
    private String owner;
    private String repository;
    private Date requestDate;


    public RepositoryMessage(String owner, String repository, Date requestDate) {
        this.owner = owner;
        this.repository = repository;
        this.requestDate = requestDate;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryMessage that = (RepositoryMessage) o;
        return Objects.equals(owner, that.owner) && Objects.equals(repository, that.repository) && Objects.equals(requestDate, that.requestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, repository, requestDate);
    }


}
