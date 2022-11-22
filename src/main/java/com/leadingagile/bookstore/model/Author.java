package com.leadingagile.bookstore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "authors")
@EntityListeners(AuditingEntityListener.class)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
        joinColumns = @JoinColumn(
            name = "author_id",
            referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
            name = "book_id",
            referencedColumnName = "id"))
    private Set<Book> books;

    @NotBlank
    private String displayName;

    @NotBlank
    private String surname;

    @NotBlank
    private String givenName;

    @NotBlank
    private String middleName;

    /**
     * This is to pacify Hibernate
     */
    public Author() {}

    @JsonCreator
    public Author(@JsonProperty("display-name") String displayName,
           @JsonProperty("surname") String surname,
           @JsonProperty("given-name") String givenName,
           @JsonProperty("middle-name") String middleName) {
        this.displayName = displayName;
        this.surname = surname;
        this.givenName = givenName;
        this.middleName = middleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("created-at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated-at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("display-name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonProperty("given-name")
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @JsonProperty("middle-name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!Author.class.isAssignableFrom(other.getClass())) return false;
        Author that = (Author) other;
        return (
            displayName.equals(that.getDisplayName()) &&
            surname.equals(that.getSurname()) &&
            givenName.equals(that.getGivenName()) &&
            middleName.equals(that.getMiddleName())
        );
    }
}
