package com.napier.sem;

/**
 * An interface for classes that manage a database connection,
 * allowing them to be used in a try-with-resources statement.
 */
public interface DatabaseConnection extends AutoCloseable {
    void connect();
    void disconnect();
}