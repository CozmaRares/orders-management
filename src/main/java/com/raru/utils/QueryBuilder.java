package com.raru.utils;

import java.util.Map;

public class QueryBuilder {
    private QueryBuilder() {
    }

    /**
     * Generates a CREATE statement for a given object and table name.
     * 
     * @param obj   The object for which to generate the CREATE statement.
     * @param table The name of the table to be created.
     * @return The generated CREATE statement as a string.
     */
    public static String create(Object obj, String table) {
        Map<String, Object> values = Utils.getFieldValues(obj);
        return new StringBuilder()
                .append("CREATE ")
                .append(table)
                .append(" CONTENT ")
                .append(Utils.JSONFormat(values))
                .append(";")
                .toString();
    }

    /**
     * Generates a SELECT statement for a given table and fields.
     * 
     * @param table  The name of the table to select from.
     * @param fields The fields to select (optional).
     * @return The generated SELECT statement as a string.
     */
    public static String select(String table, String... fields) {
        return fields.length == 0
                ? String.format("SELECT * FROM %s;", table)
                : String.format("SELECT %s FROM %s", String.join(", ", fields), table);
    }

    /**
     * Generates a SELECT...FETCH query for retrieving records with a
     * fetched alias from a table.
     * 
     * @param table   the name of the table
     * @param fetched the alias for the fetched field
     * @param field   the field to be fetched
     * @return the SELECT FETCH SQL query
     */
    public static String selectFetch(String field, String table, String fetched) {
        return String.format("SELECT %s AS %s FROM %s FETCH %s",
                field, fetched, table, fetched);
    }

    /**
     * Generates an UPDATE statement for a given object.
     * 
     * @param obj The object for which to generate the UPDATE statement.
     * @return The generated UPDATE statement as a string.
     */
    public static String update(Object obj) {
        Map<String, Object> values = Utils.getFieldValues(obj);
        return new StringBuilder()
                .append("UPDATE ")
                .append(Utils.getId(obj))
                .append(" MERGE ")
                .append(Utils.JSONFormat(values))
                .append(";")
                .toString();
    }

    /**
     * Generates a DELETE statement for a given ID.
     * 
     * @param id The ID of the record to be deleted.
     * @return The generated DELETE statement as a string.
     */
    public static String delete(String id) {
        return "DELETE " + id + ";";
    }

    /**
     * Generates a RELATE statement to establish a relationship between two IDs with
     * a given verb.
     * 
     * @param id1  The ID of the first record.
     * @param verb The relationship.
     * @param id2  The ID of the second record.
     * @return The generated RELATE statement as a string.
     */
    public static String relate(String id1, String verb, String id2) {
        return String.format("RELATE %s -> %s -> %s;", id1, verb, id2);
    }
}
