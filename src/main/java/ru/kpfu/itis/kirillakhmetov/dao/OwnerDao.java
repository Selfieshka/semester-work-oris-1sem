package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.Owner;
import ru.kpfu.itis.kirillakhmetov.mapper.OwnerMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OwnerDao extends BaseDao<Owner> {
    //language=sql
    private static final String SQL_SAVE = """
                INSERT INTO owner (first_name, email, password, business_name)
                VALUES (?, ?, ?, ?)
            """;
    // language=sql
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM owner WHERE email = ?";

    public OwnerDao() {
        this.mapper = new OwnerMapper();
    }

    @Override
    public List<Owner> findAll() {
        return List.of();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Owner owner) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE)) {

            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getEmail());
            statement.setString(3, owner.getPassword());
            statement.setString(4, owner.getBusinessName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    public Optional<Owner> findByEmail(String email) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapper.mapRow(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
