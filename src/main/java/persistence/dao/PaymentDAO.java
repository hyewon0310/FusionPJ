package main.java.persistence.dao;
import main.java.persistence.PooledDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
}
