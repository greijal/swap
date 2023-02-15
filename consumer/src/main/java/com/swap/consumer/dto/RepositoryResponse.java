package com.swap.consumer.dto;

import java.io.Serializable;
import java.util.List;

public class RepositoryResponse implements Serializable {

    private String owner;
    private String repository;
    private List<IssuesResponse> issues;
    private List<ContributorResponse> contributors;

    public String getOwner() {
        return owner;
    }

    public RepositoryResponse setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getRepository() {
        return repository;
    }

    public RepositoryResponse setRepository(String repository) {
        this.repository = repository;
        return this;
    }

    public List<IssuesResponse> getIssues() {
        return issues;
    }

    public RepositoryResponse setIssues(List<IssuesResponse> issues) {
        this.issues = issues;
        return this;
    }

    public List<ContributorResponse> getContributors() {
        return contributors;
    }

    public RepositoryResponse setContributors(List<ContributorResponse> contributors) {
        this.contributors = contributors;
        return this;
    }

    public static class ContributorResponse implements Serializable {

        private int totalCommit;
        private String author;

        public int getTotalCommit() {
            return totalCommit;
        }

        public ContributorResponse setTotalCommit(int totalCommit) {
            this.totalCommit = totalCommit;
            return this;
        }

        public String getAuthor() {
            return author;
        }

        public ContributorResponse setAuthor(String author) {
            this.author = author;
            return this;
        }
    }

    public static class IssuesResponse implements Serializable {
        private String title;
        private String author;
        private List<String> labels;

        public String getTitle() {
            return title;
        }

        public IssuesResponse setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getAuthor() {
            return author;
        }

        public IssuesResponse setAuthor(String author) {
            this.author = author;
            return this;
        }

        public List<String> getLabels() {
            return labels;
        }

        public IssuesResponse setLabels(List<String> labels) {
            this.labels = labels;
            return this;
        }
    }
}
