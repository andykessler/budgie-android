package io.akessler.budgie.core.model;

import java.math.BigDecimal;
import java.util.List;

public class Category {

    String id;

    String name;

    String description;

    BigDecimal extremum;

    String parentId;

    List<String> childrenIds;

}
