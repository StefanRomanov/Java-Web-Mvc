package com.excersise.virus.entities.models;

import com.excersise.virus.entities.enums.Magnitude;
import com.excersise.virus.entities.enums.Mutation;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddVirusModel {

    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int DESCRIPTION_MIN_LENGTH = 5;
    private static final int DESCRIPTION_MAX_LENGTH = 100;
    private static final int SIDE_EFFECTS_LENGTH = 50;
    private static final int TURNOVER_RATE_MIN = 0;
    private static final int TURNOVER_RATE_MAX = 100;
    private static final int HOURS_UNTIL_TURN_MIN = 1;
    private static final int HOURS_UNTIL_TURN_MAX = 12;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String CREATOR_REGEX = "^Corp$|^corp\\.$";

    private Long id;

    @Length(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    private String name;


    @Length(min = DESCRIPTION_MIN_LENGTH, max = DESCRIPTION_MAX_LENGTH)
    private String description;


    @Length(max = SIDE_EFFECTS_LENGTH)
    private String sideEffects;


    @Pattern(regexp = CREATOR_REGEX )
    private String creator;


    @NotNull
    private Boolean isDeadly;


    @NotNull
    private Boolean isCurable;


    @NotNull
    private Mutation mutation;


    @NotNull
        @Range(min = TURNOVER_RATE_MIN, max = TURNOVER_RATE_MAX)
        private Integer turnoverRate;


    @NotNull
    @Range(min = HOURS_UNTIL_TURN_MIN, max = HOURS_UNTIL_TURN_MAX)
    private Integer hoursUntilTurn;

    @NotNull
    private Magnitude magnitude;

    @NotNull
    @Past
    @DateTimeFormat(pattern = DATE_FORMAT)
    private LocalDate releasedOn;

    @NotEmpty
    private List<Long> capIds = new ArrayList<>();

    private List<CapitalNameAndIdViewModel> capitals = new ArrayList<>();
    private List<String> mutations = new ArrayList<>();
    private List<String> magnitudes = new ArrayList<>();

    public AddVirusModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIsDeadly() {
        return isDeadly;
    }

    public void setIsDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getIsCurable() {
        return isCurable;
    }

    public void setIsCurable(Boolean curable) {
        isCurable = curable;
    }

    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<CapitalNameAndIdViewModel> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<CapitalNameAndIdViewModel> capitals) {
        this.capitals = capitals;
    }

    public List<String> getMutations() {
        return mutations;
    }

    public void setMutations(List<String> mutations) {
        this.mutations = mutations;
    }

    public List<String> getMagnitudes() {
        return magnitudes;
    }

    public void setMagnitudes(List<String> magnitudes) {
        this.magnitudes = magnitudes;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public List<Long> getCapIds() {
        return capIds;
    }

    public void setCapIds(List<Long> capIds) {
        this.capIds = capIds;
    }

    @Override
    public String toString() {
        return "VirusAddEditBindingModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", creator='" + creator + '\'' +
                ", isDeadly=" + isDeadly +
                ", isCurable=" + isCurable +
                ", mutation=" + mutation +
                ", turnoverRate=" + turnoverRate +
                ", hoursUntilMutation=" + hoursUntilTurn +
                ", magnitude=" + magnitude +
                ", releasedOn=" + releasedOn +
                ", capIds=" + capIds +
                ", allCapitals=" + capitals.size() +
                ", allMutations=" + mutations.size() +
                ", allMagnitudes=" + magnitudes.size() +
                '}';
    }
}
