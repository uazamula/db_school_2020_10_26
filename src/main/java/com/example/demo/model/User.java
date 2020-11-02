package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User implements Comparable<User>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Override    // This method doesn't work properly, e.g.: 10a, 11a, 11b, 2a, 4a, 6a
    public int compareTo(User other) {
        int i = this.getLastName().compareTo(other.getLastName());
        if (i==0) return this.getFirstName().compareTo(other.getFirstName());
        else
        return i;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //    @OneToOne()
//    //@JoinColumn(name = "teacher_id")
//    @JoinColumn(name = "id")
//    private Class aClass;


}
