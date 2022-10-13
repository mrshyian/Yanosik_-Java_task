package com.javatask;

import com.javatask.model.UserModel;
import com.javatask.model.VehicleModel;

import java.sql.*;

public class GetDataFromDB {
    private final static String DBUrl = "jdbc:postgresql://localhost:5433/Yanosik_java_zadanie";
    private final static String DBUser = "postgres";
    private final static String DBPassword = "43676321";
    private final static String DBDriver = "org.postgresql.Driver";

    public UserModel connectToDB(String userId) {
        UserModel userModel = new UserModel();
        try {
            Class.forName(DBDriver);
            Connection connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
            Statement statement = connection.createStatement();
            String query = "SELECT " +
                    "users.nick AS userNick, " +
                    "vehicles.brand AS carBrand, " +
                    "vehicles.model AS carModel,  " +
                    "vehicles.insert_time AS carBoughtDate, " +
                    "insurance_offers.insurer AS insurerName, " +
                    "insurance_offers.price AS insurancePrice, " +
                    "insurance_offers.insert_time AS insuranceDate " +
                    "FROM " +
                    "((users INNER JOIN vehicles ON users.login = vehicles.login)" +
                    "INNER JOIN insurance_offers ON insurance_offers.vehicle_id = vehicles.id)" +
                    "WHERE users.id = " + userId;

            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                userModel.setNick(rs.getString("userNick"));

                VehicleModel vehicleModel = createVehicle(rs);
                userModel.setCar(vehicleModel);
            }
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public VehicleModel createVehicle(ResultSet rs) throws SQLException {
        VehicleModel vehicleModel = new VehicleModel();

        vehicleModel.setCarBrand(rs.getString("carBrand"));
        vehicleModel.setCarModel(rs.getString("carModel"));
        vehicleModel.setCarBoughtDate(rs.getString("carBoughtDate"));

        vehicleModel.setInsurerName(rs.getString("insurerName"));
        vehicleModel.setInsurancePrice(rs.getString("insurancePrice"));
        vehicleModel.setInsuranceDate(rs.getString("insuranceDate"));
        return vehicleModel;
    }
}
