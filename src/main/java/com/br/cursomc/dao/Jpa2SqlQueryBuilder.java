package com.br.cursomc.dao;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Query;

public class Jpa2SqlQueryBuilder {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Query querySql2o;
	private Map<Field, String> columnsAll;
	private Map<Field, String> columns;
	private Map<Field, String> columnsId;
	private Field fieldId;
	private Class<?> type;

	private static Set<Jpa2SqlQuery> jpa2SqlQuery = new HashSet<>();

	public Jpa2SqlQueryBuilder(Class<?> type) {
		this.type = type;
		columnsAll = new HashMap<>();
		columnsId = CreateQueryUtil.extraticColunsId(getType());
		columns = CreateQueryUtil.extractColumnsWithoutIdColuns(getType());
		columns.putAll(columns);
		columns.putAll(columnsId);
		fieldId = CreateQueryUtil.extractOnlyFieldId(getType());
	}

	private Class<?> getType() {
		return type;
	}

	public Jpa2SqlQueryBuilder withDelete() {

		if (checkExistsStaticQuery(EnumTypeQuery.DELETE) == null) {
			logger.debug("Build Merge quey..!!!");
			
			StringBuilder mergeQuery = new StringBuilder();
			
			mergeQuery.append(String.format("merge %s as target", CreateQueryUtil.extractTableName(getType())));
			
			Map<Field, String> columnsAllAsParameter = new HashMap<>(columnsAll);
			for (Map.Entry<Field, String> jpa2SqlQuery2 : columnsAllAsParameter.entrySet()) {
				jpa2SqlQuery2.setValue(":".concat(jpa2SqlQuery2.getValue()));
			}
			
			String joinColunsAll = String.join(",", columnsAll.values());
		}

		return null;
	}

	private Jpa2SqlQuery checkExistsStaticQuery(EnumTypeQuery enumTypeQuery) {
		return jpa2SqlQuery.stream()
				           .filter(sql -> sql.getClassType().equals(getType()) && 
				        		          sql.getTypeQuery().equals(enumTypeQuery)).findFirst().orElse(null);
		}

	public static class Jpa2SqlQuery {

		private Class<?> classType;
		private EnumTypeQuery typeQuery;

		public Class<?> getClassType() {
			return classType;
		}

		public EnumTypeQuery getTypeQuery() {
			return typeQuery;
		}
	}
}
