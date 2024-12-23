package kz.bitlab.g130jdbcpractice.service;

import kz.bitlab.g130jdbcpractice.entity.ApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService {

    private final Connection connection;

    public List<ApplicationRequest> getAllApplicationRequests() {
        List<ApplicationRequest> applicationRequests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM application_requests"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ApplicationRequest applicationRequest = ApplicationRequest.builder()
                        .id(resultSet.getLong("id"))
                        .userName(resultSet.getString("user_name"))
                        .courseName(resultSet.getString("course_name"))
                        .commentary(resultSet.getString("commentary"))
                        .phone(resultSet.getString("phone"))
                        .handled(resultSet.getBoolean("handled"))
                        .build();
                applicationRequests.add(applicationRequest);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applicationRequests;
    }

    public void addApplicationRequest(ApplicationRequest applicationRequest) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO application_requests(user_name, course_name, commentary, phone, handled) " +
                            "VALUES(?, ?, ?, ?, ?)"
            );
            statement.setString(1, applicationRequest.getUserName());
            statement.setString(2, applicationRequest.getCourseName());
            statement.setString(3, applicationRequest.getCommentary());
            statement.setString(4, applicationRequest.getPhone());
            statement.setBoolean(5, applicationRequest.isHandled());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateApplicationRequest(ApplicationRequest applicationRequest) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE application_requests SET user_name = ?, course_name = ?, commentary = ?, phone = ?, handled = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1, applicationRequest.getUserName());
            statement.setString(2, applicationRequest.getCourseName());
            statement.setString(3, applicationRequest.getCommentary());
            statement.setString(4, applicationRequest.getPhone());
            statement.setBoolean(5, applicationRequest.isHandled());
            statement.setLong(6, applicationRequest.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteApplicationRequestById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM application_requests WHERE id = ?"
            );
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApplicationRequest getApplicationRequestById(Long id) {
        ApplicationRequest applicationRequest = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM application_requests WHERE id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                applicationRequest = ApplicationRequest.builder()
                        .id(id)
                        .userName(resultSet.getString("user_name"))
                        .courseName(resultSet.getString("course_name"))
                        .commentary(resultSet.getString("commentary"))
                        .phone(resultSet.getString("phone"))
                        .handled(resultSet.getBoolean("handled"))
                        .build();
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicationRequest;
    }

}
