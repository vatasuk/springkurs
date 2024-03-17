package com.example.springkurs.Repository;

import com.example.springkurs.Repository.interfaces.RegionRepository;
import com.example.springkurs.entity.Region;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JDBCRegionRepository implements RegionRepository {

    private JdbcTemplate jdbcTemplate;

    public JDBCRegionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List findAll() {
        return jdbcTemplate.query("select * from region order by nameregion asc", BeanPropertyRowMapper.newInstance(Region.class));
    }

    @Override
    public Region findById(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("select * from region where id = ?",args,BeanPropertyRowMapper.newInstance(Region.class));
    }
    @Override
    public List<Region> findByCountryId(int id) {
        Object[] args = {id};
        return jdbcTemplate.query("select * from region where country_id = ?",args,BeanPropertyRowMapper.newInstance(Region.class));
    }

    @Override
    public void save(Region region) {
        jdbcTemplate.update("insert into region (country_id,nameregion) values (?,? )",region.getCountry_id(),region.getNameregion());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from region where id = ?", id);
    }

    @Override
    public void update(Region region) {
        jdbcTemplate.update("update region set country_id = ?, nameregion = ? where id = ?",region.getCountry_id(),region.getNameregion(),region.getId());
    }
}
