package com.bos.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class CustomerServiceImpl implements ICustomerService {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		});
		return list;
	}

	@Override
	public List<Customer> findlistnotassaction() {
		String sql = "select * from t_customer where decidedzone_id is null";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		});
		return list;
	}

	@Override
	public List<Customer> findlisthasassaction(String decidedzoneid) {
		String sql = "select * from t_customer where decidedzone_id = ?";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		}, decidedzoneid);
		return list;
	}

	@Override
	public void assigncustomerstodecidedzone(String decidedzoneid, Integer[] ids) {//005 1
		String sql = "UPDATE t_customer SET decidedzone_id = NULL WHERE decidedzone_id = ?";
		String sql2 = "UPDATE t_customer SET decidedzone_id = ? WHERE id = ?";
		jdbcTemplate.update(sql, decidedzoneid);
//		执行第二个update
		for (Integer id : ids) {
			jdbcTemplate.update(sql2, decidedzoneid, id);
		}
	}

	@Override
	public String findbydecidAddress(String address) {
		String sql = "select decidedzone_id from t_customer where address = ?";
//		第二个参数是方法返回的是一个字符串，第三个是参数
		String decidedzoneId = jdbcTemplate.queryForObject(sql, String.class, address);
		return decidedzoneId;
	}

	@Override
	public Customer findcustomerbytelephone(String telephone) {
		String sql = "select * from t_customer where telephone = ?";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		}, telephone);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
