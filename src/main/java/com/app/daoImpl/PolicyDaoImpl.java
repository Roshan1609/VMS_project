package com.app.daoImpl;

import com.app.dao.PolicyDao;
import com.app.enums.PackageName;
import com.app.enums.PolicyStatus;
import com.app.enums.ReviewStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Policy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PolicyDaoImpl implements PolicyDao {
    private JdbcTemplate jdbcTemplate;

    public PolicyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<Policy> mapper(){
        return (rs,num)->{
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
        };
    }

    @Override
    public void insert(Policy policy) {
    String sql = "INSERT INTO policy " +
                "(startDate, endDate, packageName, policyStatus, premiumAmount, " +
                "reviewRemarks, reviewStatus, customer_id, officer_id, vehicle_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                policy.getStartDate(),
                policy.getEndDate(),
                policy.getPackageName().toString(),
                policy.getPolicyStatus().toString(),
                policy.getPremiumAmount(),
                policy.getReviewRemarks(),
                policy.getReviewStatus().toString(),
                policy.getCustomer_id(),
                policy.getOfficer_id(),
                policy.getVehicle_id()
        );
    }

    @Override
    public List<Policy> getAll() {
        String sql="select * from policy";
        return jdbcTemplate.query(sql,mapper());
    }

    @Override
    public Policy getById(int id) throws ResourceNotFoundException {
        String sql="select * from policy where id=?";

        return jdbcTemplate.queryForObject(sql,mapper(),id);
    }

    @Override
    public void deletebyId(int id) throws ResourceNotFoundException {
        String sql="delete from policy where id=?";
        int rows=jdbcTemplate.update(sql,id);
        if(rows==0){
            throw new ResourceNotFoundException ("Invalid ID given") ;
        }
        System.out.println("Policy deleted.....");

    }

    @Override
    public void update(Policy policy) throws ResourceNotFoundException {
    String sql="update policy set reviewRemarks=? where id=?";
    jdbcTemplate.update(sql,policy.getReviewRemarks(),policy.getId());
    System.out.println("Policy updated");
    }
}
