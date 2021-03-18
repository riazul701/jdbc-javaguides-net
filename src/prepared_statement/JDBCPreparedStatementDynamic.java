package prepared_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * JDBC PreparedStatement with list of parameters in a IN clause Example
 * @author Ramesh Fadatare
 *
 */
public class JDBCPreparedStatementDynamic {

    public static void main(String[] args) {
        List < Integer > ids = new ArrayList < > ();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        processDynamicQuery(ids);

    }
    public static void processDynamicQuery(List < Integer > ids) {

        String query = createQuery(ids.size());

        System.out.println("Query=" + query);
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = null;

            int parameterIndex = 1;
            for (Iterator < Integer > iterator = ids.iterator(); iterator.hasNext();) {
                Integer id = (Integer) iterator.next();
                preparedStatement.setInt(parameterIndex++, id);
            }
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("User ID=" + rs.getInt("id") + ", Name=" + rs.getString("name"));
            }

            // close the resultset here
            try {
                rs.close();
            } catch (SQLException e) {}

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String createQuery(int length) {
        String query = "select id, name from users where id in (";
        StringBuilder queryBuilder = new StringBuilder(query);
        for (int i = 0; i < length; i++) {
            queryBuilder.append(" ?");
            if (i != length - 1)
                queryBuilder.append(",");
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }
}
