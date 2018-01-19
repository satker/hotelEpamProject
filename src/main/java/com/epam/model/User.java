package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Min(6)
    private String password;
    @NotNull
    private boolean is_admin = false;

    @OneToMany(mappedBy = "user")
    private Set<RoomRequest> requests = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<RoomConfirm> confirmRooms = new HashSet<>();

}
