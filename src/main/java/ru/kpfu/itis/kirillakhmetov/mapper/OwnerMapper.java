package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerMapper implements RowMapper<Owner> {
    @Override
    public Owner mapRow(ResultSet resultSet) throws SQLException {
        return new Owner(
                resultSet.getLong("owner_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("patronymic"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("business_name")
        );
    }
}
