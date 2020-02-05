package com.br.cursomc.dao;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.core.annotation.AnnotationUtils;

public class CreateQueryUtil {

	public CreateQueryUtil() {
		super();
	}

	static String extractTableName(Class<?> type) {
		Table table = AnnotationUtils.findAnnotation(type, Table.class);
		if (table != null)
			return table.name();
		else
			return "@Table not annotation not found!";
	}

	static Map<Field, String> extractColumnsWithoutIdColuns(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		Map<Field, String> names = new HashMap<>();

		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class)) {
				names.put(field, field.getDeclaredAnnotation(Column.class).name());
			}
		}

		return names;
	}

	static Map<Field, String> extraticColunsId(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		Map<Field, String> map = new HashMap<>();

		for (Field field : fields) {
			if (field.isAnnotationPresent(EmbeddedId.class)) {
				return extractColumnsFromEmbeddeId(field);
			} else if (field.isAnnotationPresent(Id.class)) {
				map.put(field, field.getDeclaredAnnotation(Column.class).name());
				return map;
			}
		}

		map.put(null, "@EmbeddedId or @Id not found!");
		return map;
	}

	private static Map<Field, String> extractColumnsFromEmbeddeId(Field field) {
		return extractColumnsWithoutIdColuns(field.getType());
	}

	static Field extractOnlyFieldId(Class<?> type) {

		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(EmbeddedId.class) || field.isAnnotationPresent(Id.class))
				return field;
		}

		return null;
	}
}