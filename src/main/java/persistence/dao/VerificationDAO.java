package main.java.persistence.dao;
import main.java.persistence.PooledDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.*;

public class VerificationDAO {
    public final DataSource ds = PooledDataSource.getDataSource();
}
