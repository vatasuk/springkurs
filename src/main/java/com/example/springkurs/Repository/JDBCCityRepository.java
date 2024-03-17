package com.example.springkurs.Repository;

import com.example.springkurs.entity.City;
import com.example.springkurs.Repository.interfaces.CityRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JDBCCityRepository implements CityRepository {

    private JdbcTemplate jdbcTemplate;

    public JDBCCityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List findAll() {
        return jdbcTemplate.query("select * from city order by namecity asc", BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public City findById(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("select * from city where id = ?", args,BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public List<City> findByRegionId(int id) {
        Object[] args = {id};
        return jdbcTemplate.query("select * from city where region_id = ?", args,BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public void save(City city) {
        jdbcTemplate.update("insert into city (region_id,namecity) values (?,?)",city.getRegion_id(),city.getNamecity());
    }

    @Override
    public void delete(int id) {

        jdbcTemplate.update("delete from city where id = ?",id);
    }

    @Override
    public void update(City city) {
        jdbcTemplate.update("update city set region_id = ?, namecity = ? where id = ?", city.getRegion_id(),city.getNamecity(),city.getId());
    }
}
