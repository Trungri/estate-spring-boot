package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.*;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable) {
        try{
            StringBuilder sql = new StringBuilder("SELECT * FROM building AS A WHERE 1=1");
            Map<String, Object> properties = buildMapSearch(builder);
            sql = createSQLFindAll(sql, properties);
            StringBuilder whereClause = builWhereClause(builder);
            sql.append(whereClause);
            Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
            if(pageable != null){
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            return query.getResultList();
        }catch (Exception e){
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    @Override
    public Long count(BuildingSearchBuilder builder) {
        try{
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM building AS A WHERE 1=1");
            Map<String, Object> properties = buildMapSearch(builder);
            sql = createSQLFindAll(sql, properties);
            StringBuilder whereClause = builWhereClause(builder);
            sql.append(whereClause);
            Query query = entityManager.createNativeQuery(sql.toString());
            List<BigInteger> resultList = query.getResultList();
            return Long.parseLong(resultList.get(0).toString(), 10);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    private StringBuilder builWhereClause(BuildingSearchBuilder builder) {
        StringBuilder whereClause = new StringBuilder();
        if (StringUtils.isNotBlank(builder.getCostRentFrom())) {
            whereClause.append(" AND costrent >= " + builder.getCostRentFrom() + "");
        }
        if (StringUtils.isNotBlank(builder.getCostRentTo())) {
            whereClause.append(" AND costrent <= " + builder.getCostRentTo() + "");
        }

        if (StringUtils.isNotBlank(builder.getAreaRentFrom()) || StringUtils.isNotBlank(builder.getAreaRentTo())) {
            whereClause.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingId = A.id");
            if (StringUtils.isNotBlank(builder.getAreaRentFrom())) {
                whereClause.append(" AND ra.value >= '" + builder.getAreaRentFrom() + "'");
            }
            if (StringUtils.isNotBlank(builder.getAreaRentTo())) {
                whereClause.append(" AND ra.value <= '" + builder.getAreaRentTo() + "'");
            }
            whereClause.append("))");
        }
        if (builder.getBuildingTypes().length > 0) {
            whereClause.append(" AND (A.type LIKE '%" + builder.getBuildingTypes()[0] + "%'");
            /*
             * //java7 for(String type : builder.getBuildingTypes()) {
             * if(!type.equals(builder.getBuildingTypes()[0])) {
             * whereClause.append(" OR A.type LIKE '%"+type+"%'"); } }
             */
            // java 8
            Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
                    .forEach(item -> whereClause.append(" OR A.type LIKE '%" + item + "%'"));
            whereClause.append(" )");
        }
        return whereClause;
    }

    private Map<String, Object> buildMapSearch(BuildingSearchBuilder builder) {
        Map<String, Object> result = new HashMap<>();
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
                        && !field.getName().startsWith("areaRent")) {
                    field.setAccessible(true);
                    if (field.get(builder) != null) {
                        if (field.getName().equals("numberOfBasement") || field.getName().equals("buildingArea")) {
                            if(StringUtils.isNotEmpty((String) field.get(builder))){
                                result.put(field.getName().toLowerCase(), Integer.parseInt((String) field.get(builder)));
                            }
                        } else {
                            result.put(field.getName().toLowerCase(), field.get(builder));
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private StringBuilder createSQLFindAll(StringBuilder result, Map<String, Object> properties) {
        if(properties != null && properties.size() > 0) {
            String[] params = new String[properties.size()];
            Object[] values = new Object[properties.size()];
            int i = 0;
            for(Map.Entry<?, ?> item: properties.entrySet()) {
                params[i] = (String) item.getKey();
                values[i] = item.getValue();
                i++;
            }
            for (int i1 = 0; i1 < params.length; i1++) {
                if(values[i1] instanceof String) {
                    result.append(" and LOWER("+params[i1]+") LIKE '%"+values[i1].toString().toLowerCase()+"%' ");
                }else if (values[i1] instanceof Integer) {
                    result.append(" and "+params[i1]+" = "+values[i1]+" ");
                }else if (values[i1] instanceof Long) {
                    result.append(" and "+params[i1]+" = "+values[i1]+" ");
                }

            }
        }
        return result;
    }
}
