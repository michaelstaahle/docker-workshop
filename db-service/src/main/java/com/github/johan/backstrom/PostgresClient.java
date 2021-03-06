package com.github.johan.backstrom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Component
public class PostgresClient {

    @Value("${db.host}")
    String host;

    @Value("${db.username}")
    String user;

    @Value("${db.password}")
    String password;


    public List<Person> getPeople() {

        Sql2o sql2o = new Sql2o("postgresql://" + host + ":5432/" + user, user, password);
        String sql = "SELECT id, firstName, lastName FROM Person";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Person.class);
        }
    }
}