package the.grid.smp.desertum.util;

import java.io.File;
import java.sql.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class SQLite {

    private final Logger logger;
    private final Connection connection;

    private SQLite(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    public static SQLite open(Logger logger, File file) {
        if (file.isDirectory())
            file = new File(file, "database.db");

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file);
            connection.setAutoCommit(false);

            return new SQLite(logger, connection);
        } catch (SQLException e) {
            logger.severe("Couldn't create connection to the database: " + e.getMessage());
        }

        return null;
    }

    public void query(SQLConsumer<ResultSet> consumer, String query, Object... args) {
        query = this.asQuery(query, args);

        try (Statement statement = this.connection.createStatement(); ResultSet result = statement.executeQuery(query)) {
            consumer.accept(result);
        } catch (SQLException e) {
            this.logger.severe("Couldn't execute query '" + query + "': " + e.getMessage());
        }
    }

    public void update(String update, Object... args) {
        update = this.asQuery(update, args);

        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(update);
        } catch (SQLException e) {
            this.logger.severe("Couldn't execute update '" + update + "': " + e.getMessage());
        }
    }

    private String asQuery(String query, Object... args) {
        query = query.formatted(args);

        if (!query.endsWith(";"))
            query += ";";

        return query;
    }

    public void close() {
        try {
            this.connection.commit();
            this.connection.close();
        } catch (SQLException e) {
            this.logger.severe("Couldn't close the database: " + e.getMessage());
        }
    }

    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }
}
