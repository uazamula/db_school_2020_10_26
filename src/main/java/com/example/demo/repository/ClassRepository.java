// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
// https://stackoverflow.com/questions/13154818/how-to-define-a-jpa-repository-query-with-a-join
package com.example.demo.repository;

import com.example.demo.model.Class;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class,Long>{

//    @Query(value = "select users.last_name, users.first_name, " +
//            "classes.id_c, classes.class_int, classes.class_char " +
//            "from users inner join classes " +
//            "on users.id=classes.teacher_id;",
//            nativeQuery = true)




   /* @Query("SELECT  c.id, u.lastName, u.firstName, c.classInt, c.classChar"+
            "FROM  User u" +
            "INNER JOIN Class c ON c.id = u.id")
                */


    //+      "WHERE                 c.teacherId = :teacherId")
//    List<Class> findAll();



}
