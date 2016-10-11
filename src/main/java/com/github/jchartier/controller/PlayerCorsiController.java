package com.github.jchartier.controller;

import com.github.jchartier.dto.CorsiResult;
import com.github.jchartier.dto.PlayerCorsi;
import com.github.jchartier.repository.PlayerCorsiRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stats")
public class PlayerCorsiController {

    private static final String TEAM_BUCKET = "team";
    private static final String CORSI_BUCKET = "CF%";
    private static final int MAX_TEAM = 30;

    @Resource
    private PlayerCorsiRepository repository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/perTeam")
    public List<CorsiResult> statisticsPerTeam() {

        AggregationBuilder corsiByTeam =
            AggregationBuilders.terms(TEAM_BUCKET).field(PlayerCorsi.Fields.TEAM.getField()).subAggregation(
                AggregationBuilders.avg(CORSI_BUCKET).field(PlayerCorsi.Fields.CF_PERCENTAGE.getField()))
                .size(MAX_TEAM);

        NativeSearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery());
        searchQuery.addAggregation(corsiByTeam);

        return elasticsearchTemplate.query(searchQuery, response -> {

            StringTerms teamTerms = response.getAggregations().get(TEAM_BUCKET);

            return teamTerms.getBuckets().stream().map(bucket -> new CorsiResult(bucket.getKeyAsString(),
                ((InternalAvg) bucket.getAggregations().get(CORSI_BUCKET)).getValue()))
                .sorted(Comparator.comparingDouble(CorsiResult::getCorsi))
                .collect(Collectors.toList());
        });
    }
}
