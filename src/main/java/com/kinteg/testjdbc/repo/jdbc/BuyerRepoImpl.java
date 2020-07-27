package com.kinteg.testjdbc.repo.jdbc;

import com.kinteg.testjdbc.model.Buyer;
import com.kinteg.testjdbc.repo.BuyerRepo;
import com.kinteg.testjdbc.repo.RSExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class BuyerRepoImpl implements BuyerRepo {

    private final JdbcTemplate template;

    private final String FIND_ALL = "select id, name, country, token from buyer";
    private final String FIND_BY_ID = "select id, name, country, token from buyer where id=?";
    private final String SAVE = "insert into buyer (id, name, country, token) values (?, ?, ?, ?)" +
            " on conflict do nothing";

    private static final RowMapper<Buyer> MAPPER_BUYER = ((resultSet, i) -> Buyer
            .builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .country(resultSet.getString("country"))
            .token(resultSet.getInt("token"))
            .build());

    private final ResultSetExtractor<Optional<Buyer>> EXTRACTOR_BUYER =
            RSExtractor.singletonOptionalExtractor(MAPPER_BUYER);

    public BuyerRepoImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Iterable<Buyer> findAll() {
        return template.query(FIND_ALL, MAPPER_BUYER);
    }

    @Override
    public Optional<Buyer> findById(Long id) {
        return template.query(FIND_BY_ID, EXTRACTOR_BUYER, id);
    }

    @Override
    public Optional<Buyer> save(Buyer buyer) {
        template.update(SAVE,
                buyer.getId(), buyer.getName(), buyer.getCountry(), buyer.getToken());

        return findById(buyer.getId());
    }

}
