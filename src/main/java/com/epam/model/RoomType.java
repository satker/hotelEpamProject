package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data

public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @OneToMany(mappedBy = "roomType")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private Set<RoomRequest> requests = new HashSet<>();

    @OneToMany(mappedBy = "roomType")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private Set<Room> rooms = new HashSet<>();
    @PostPersist
    public void onPrePersist() {
        audit("INSERT");
    }

    @PostUpdate
    public void onPreUpdate() {
        audit("UPDATE");
    }

    @PostRemove
    public void onPreRemove() {
        audit("DELETE");
    }

    private void audit(String operation) {
        log.debug("operation to room type table completed {}", operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(id, roomType.id) &&
                Objects.equals(name, roomType.name) &&
                Objects.equals(description, roomType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, description);
    }
}
