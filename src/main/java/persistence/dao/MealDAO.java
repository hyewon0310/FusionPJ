package main.java.persistence.dao;
import main.java.persistence.PooledDataSource;
import main.java.persistence.dto.MealDTO;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class MealDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<MealDTO> getAllMeals() {
        String SQL = """
                SELECT MealType, DormitoryId, Cost
                FROM meal
                """;
        List<MealDTO> meals = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                MealDTO meal = new MealDTO();
                meal.setMealType(rs.getInt("MealType"));
                meal.setDormitoryId(rs.getInt("DormitoryId"));
                meal.setMealCost(rs.getInt("Cost"));

                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }


    // Meal 데이터 추가
    public void insertMeal(MealDTO meal) {
        String SQL = """
                INSERT INTO meal (MealType, DormitoryId, Cost)
                VALUES (?, ?, ?)
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, meal.getMealType());
            stmt.setInt(2, meal.getDormitoryId());
            stmt.setInt(3, meal.getMealCost());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMeal(int mealType, int dormitoryId, int newCost) {
        String SQL = """
                UPDATE meal
                SET Cost = ?
                WHERE MealType = ? AND DormitoryId = ?
                """;
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, newCost);
            stmt.setInt(2, mealType);
            stmt.setInt(3, dormitoryId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
