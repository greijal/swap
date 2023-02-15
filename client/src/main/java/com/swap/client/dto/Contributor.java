package com.swap.client.dto;

import java.io.Serializable;
import java.util.Objects;

public class Contributor implements Serializable {
    private int total;
    private User author;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contributor that = (Contributor) o;
        return total == that.total && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, author);
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "total=" + total +
                ", author=" + author +
                '}';
    }
}
