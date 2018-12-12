package com.chinthakad.delifoodstore.seedwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Base class of every model.
 *
 * <p> NOTE: Rule of thumb: Every model <b>MUST</b> inherit from the AbstractModel. It will give us a common base that will help
 * in future in injecting common functionality as well as in building non-intrusive logics such as aspects.
 *
 * @author Virtusa Inc.
 */
public abstract class AbstractModel implements Serializable {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @JsonIgnore
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
