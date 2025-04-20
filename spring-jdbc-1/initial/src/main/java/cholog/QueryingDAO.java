package cholog;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueryingDAO {
    private JdbcTemplate jdbcTemplate;

    public QueryingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };
    추후 rowMapper에 대해 학습해보고 이용해보기
    */

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType)
     */
    public int count() {
        String query = "SELECT COUNT(*) FROM customers";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
     */
    public String getLastName(Long id) {
        String sql = "select last_name from customers where id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    /**
     * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public Customer findCustomerById(Long id) {
        String sql = "select id, first_name, last_name from customers where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    return new Customer(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name")
                    );
                },
                id
        );
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
     */
    public List<Customer> findAllCustomers() {
        String sql = "select id, first_name, last_name from customers";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    return new Customer(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name")
                    );
                }
        );
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public List<Customer> findCustomerByFirstName(String firstName) {
        String sql = "select id, first_name, last_name from customers where first_name = ?";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    return new Customer(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name")
                    );
                },
                firstName
        );
    }
}
