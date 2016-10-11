package com.github.jchartier.repository;

import com.github.jchartier.dto.PlayerCorsi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerCorsiRepository extends ElasticsearchRepository<PlayerCorsi, String> {

    Page<PlayerCorsi> findByTeam(@Param("team") String team, Pageable pageable);
}
