package com.ptit.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


public class MethodUtil {


  public static String formatKeySearch(String str) {
    return str.replaceAll("%", "&%").replaceAll("_", "&_");
  }


  public static boolean isInteger(String str) {
    if (str == null || !str.matches("[0-9]+$")) {
      return false;
    }
    return true;
  }

 
  public static PageRequest Pagination(Paging paging, Sorting sorting) {
    // return PageRequest
    return new PageRequest(paging.getPageNumber() - 1, paging.getNumberRecordPerPage(),
        convertSort(sorting));
  }


  public static Sort convertSort(Sorting sorting) {
    // init field sort index 0
    Sort sort = new Sort(sorting.getSortTypes().get(0), sorting.getSortFields().get(0));
    // init field sort index from 1 --> end
    for (int i = 1; i < sorting.getSortTypes().size() && i < sorting.getSortFields().size(); i++) {
      sort = sort.and(new Sort(sorting.getSortTypes().get(i), sorting.getSortFields().get(i)));
    }
    return sort;
  }


  public static PageRequest Pagination(Paging paging) {
    return new PageRequest(paging.getPageNumber() - 1, paging.getNumberRecordPerPage());
  }


  public static boolean isNull(Object object) {
    return object == null || "".equals(object);
  }


  public static boolean checkListIsNull(List<?> list) {
    return list == null || list.size() == 0;
  }

  public static String sha1(String input) {
    if (input == null || input.equalsIgnoreCase("")) {
      return input;
    }
    try {
      MessageDigest mDigest;
      mDigest = MessageDigest.getInstance("SHA1");
      byte[] result = mDigest.digest(input.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < result.length; i++) {
        sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }


  public static boolean validateLoginParams(String data, String regexter) {
    return (data == null || regexter == null || "".equals(data)) ? false : data.matches(regexter);
  }


  public static boolean compare(String paramter1, String paramter2) {
    return (paramter1.equals(paramter2));
  }



  public static boolean checkRegularExpression(String input, String regular) {
    // validate input
    if (isNull(input)) {
      return false;
    }
    // validate success
    return Pattern.compile(regular).matcher(input).matches();
  }


  public static boolean validateStringlength(String input, int maxlength) {
    if (input.length() <= maxlength) {
      return true;
    }
    return false;
  }


  public static String removeAccent(String input) {
    String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
  }

  public static String convertContentSearch(String input) {
    if (!isNull(input)) {
      return MethodUtil.removeAccent(input) + " " + input;
    }
    return null;
  }


  public static String getLinkProject() {
    return System.getProperty("user.dir");
  }

  public static String decodeUTF8(String input) throws UnsupportedEncodingException {
    return URLDecoder.decode(input, "UTF-8");
  }

}
