package eu.comparegroup.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;

/**
 * Utilities for search
 */
public class SearchUtils {

	/**
	 * Returns a String where those characters that QueryParser
	 * expects to be escaped are escaped by a preceding <code>\</code>.
	 *
	 * This is a modified version of {@link org.apache.lucene.queryparser.classic.QueryParser} because
	 * some characters don't need to be escaped like :
	 */
	public static String escape(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// These characters are part of the query syntax and must be escaped
			if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '|' || c == '&' || c == '/') {
				sb.append('\\');
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Round the decimal to the given pattern
	 * s
	 * @param d double to transform
	 * @param pattern the pattern to use, default is "#.##"
	 * @return
	 */
	public static double round(double d, String pattern) {
		String p = StringUtils.isBlank(pattern) ? "#.##" : pattern;
		DecimalFormat twoDForm = new DecimalFormat(p);
		return Double.valueOf(twoDForm.format(d));
	}
}
