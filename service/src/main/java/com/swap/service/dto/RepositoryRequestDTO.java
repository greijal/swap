package com.swap.service.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class RepositoryRequestDTO implements Serializable {

    @NotBlank
    private String owner;
    @NotBlank
    private String repository;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryRequestDTO that = (RepositoryRequestDTO) o;
        return Objects.equals(owner, that.owner) && Objects.equals(repository, that.repository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, repository);
    }
}
