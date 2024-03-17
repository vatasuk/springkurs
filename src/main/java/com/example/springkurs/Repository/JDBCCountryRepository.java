package com.example.springkurs.Repository;

import com.example.springkurs.Repository.interfaces.CountryRepository;
import com.example.springkurs.entity.Country;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JDBCCountryRepository implements CountryRepository {

    private JdbcTemplate jdbcTemplate;

    public JDBCCountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List findAll() {
        return jdbcTemplate.query("select * from country order by fullname ASC", BeanPropertyRowMapper.newInstance(Country.class));
    }

    @Override
    public Country findById(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("select * from country where id = ?", args,BeanPropertyRowMapper.newInstance(Country.class));
    }

    @Override
    public void save(Country country) {
        jdbcTemplate.update(
                "INSERT INTO city(fullname,shortname) VALUES(?,?)", country.getFullname(), country.getShortname());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from country where id = ?", id);
    }
    @Override
    public void update(Country country) {
        Object[] args = {country.getFullname(),country.getShortname(),country.getId()};
        jdbcTemplate.update("UPDATE country SET fullname = ?,shortname = ? WHERE id=?",args);
    }
}
