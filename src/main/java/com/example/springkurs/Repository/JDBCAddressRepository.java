package com.example.springkurs.Repository;

import com.example.springkurs.Repository.interfaces.AddressRepository;
import com.example.springkurs.entity.Address;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCAddressRepository implements AddressRepository {
    private JdbcTemplate jdbcTemplate;

    public JDBCAddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> findAll() {
        return jdbcTemplate.query("select * from address", BeanPropertyRowMapper.newInstance(Address.class));
    }

    @Override
    public Address findById(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("select * from address where id = ?", args,BeanPropertyRowMapper.newInstance(Address.class));
    }
    @Override
    public List<Address> findByCityId(int id) {
        Object[] args = {id};
        return jdbcTemplate.query("select * from address where city_id = ?", args,BeanPropertyRowMapper.newInstance(Address.class));
    }

    @Override
    public void save(Address address) {

        jdbcTemplate.update("insert into address (city_id,person,street,building,office) values (?,?,?,?,?)",address.getCity_id(),address.getPerson(),address.getStreet(),address.getBuilding(),address.getOffice());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from address where id = ?", id);
    }

    @Override
    public void update(Address address) {
        jdbcTemplate.update("update address set city_id = ?,person = ?,street = ?,building = ?,office = ? ",
                address.getCity_id(),address.getPerson(),address.getStreet(),address.getBuilding(),address.getOffice());
    }
}
