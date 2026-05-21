package com.app.util;

import com.app.enums.PackageName;
import com.app.enums.PolicyStatus;
import com.app.enums.ReviewStatus;
import com.app.model.Policy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PolicyMapperUtil implements RowMapper<Policy> {
    @Nullable
    @Override
    public Policy mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Policy(
                rs.getInt("id"),
                rs.getDate("startDate").toLocalDate(),
                rs.getDate("endDate").toLocalDate(),
                PackageName.valueOf(rs.getString("packageName")),
                PolicyStatus.valueOf(rs.getString("policyStatus")),
                rs.getDouble("premiumAmount"),
                rs.getString("reviewRemarks"),
                ReviewStatus.valueOf(rs.getString("reviewStatus")),
                rs.getInt("customer_id"),
                rs.getInt("officer_id"),
                rs.getInt("vehicle_id")
        );
    }
}
