package com.github.jchartier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "statistics", type = "corsi")
public class PlayerCorsi {

    @Getter
    @AllArgsConstructor
    public enum Fields {

        TEAM("team"),
        CF_PERCENTAGE("CF_Percentage");

        String field;
    }

    @Id
    private String id;

    @JsonProperty(value = "year")
    private Integer year;

    @JsonProperty(value = "lastname")
    private String lastName;

    @JsonProperty(value = "firstname")
    private String firstFame;

    @JsonProperty(value = "team")
    private String team;

    @JsonProperty(value = "pos")
    private String position;

    @JsonProperty(value = "GP")
    private Integer gp;

    @JsonProperty(value = "TOI")
    private Integer toi;

    @JsonProperty(value = "GF")
    private Integer gf;

    @JsonProperty(value = "GF60")
    private Float gfRatio;

    @JsonProperty(value = "GA60")
    private Float gaRatio;

    @JsonProperty(value = "GF_Percentage")
    private Float gfPercentage;

    @JsonProperty(value = "SF")
    private Integer sf;

    @JsonProperty(value = "SA")
    private Integer sa;

    @JsonProperty(value = "Sh_Percentage")
    private Float shPercentage;

    @JsonProperty(value = "Sv_Percentage")
    private Float svPercentage;

    @JsonProperty(value = "PDO")
    private Float pdo;

    @JsonProperty(value = "CF")
    private Integer cf;

    @JsonProperty(value = "CA")
    private Integer ca;

    @JsonProperty(value = "CF60")
    private Float cfRatio;

    @JsonProperty(value = "CA60")
    private Float caRatio;

    @JsonProperty(value = "CF_Percentage")
    private Float cfPercentage;

    @JsonProperty(value = "CSh_Percentage")
    private Float cshPercentage;

    @JsonProperty(value = "CSv_Percentage")
    private Float csvPercentage;

    @JsonProperty(value = "CPDO")
    private Float cpdo;
}
