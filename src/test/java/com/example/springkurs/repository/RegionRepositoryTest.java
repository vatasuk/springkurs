package com.example.springkurs.repository;
import static org.assertj.core.api.Assertions.assertThat;
import com.example.springkurs.Repository.interfaces.CountryRepository;

import com.example.springkurs.Repository.interfaces.RegionRepository;
import com.example.springkurs.entity.Country;
import com.example.springkurs.entity.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RegionRepositoryTest {
    private RegionRepository regionRepository;
    @Autowired
    public RegionRepositoryTest(
            RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    private Region region;
    @BeforeEach
    public void setup(){
        region = Region.builder().nameregion("212121").build();
    }
    @Test
    public void saveRegionTest()
    {
        Region savedRegion = regionRepository.save(region);
        assertThat(savedRegion).isNotNull();
        assertThat(savedRegion.getRegionid()).isGreaterThan(0);
    }
    @Test
    public void getRegionListTest()
    {
        Region region1 = Region.builder().nameregion("12333121").build();

        regionRepository.save(region1);
        regionRepository.save(region1);
        Iterable<Region> list = regionRepository.findAll();

        assertThat(list).isNotNull();
    }
    @Test
    public void findByIdTest()
    {
        regionRepository.save(region);

        Region country2 = regionRepository.findById(region.getRegionid()).get();

        assertThat(country2).isNotNull();
    }
}