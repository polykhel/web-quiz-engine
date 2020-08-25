package com.mpollente.webquizengine.util;

import java.util.Set;

public class Utils {
    public static <T extends Comparable<T>> boolean isEquals(Set<T> set1, Set<T> set2) {
        if (set1 == null && set2 == null) {
            return true;
        } else if (set1 == null || set2 == null) {
            return false;
        } else if (set1.size() != set2.size()) {
            return false;
        }
        return set1.equals(set2);
    }
}
