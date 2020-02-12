package com.br.cursomc.resources.utis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreatmentUrl {

	public static List<Integer> decondeIntForList(String value) {

		return Arrays.asList(value.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

	public static String decodeParam(String valeu) {
		try {
			return URLDecoder.decode(valeu, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
