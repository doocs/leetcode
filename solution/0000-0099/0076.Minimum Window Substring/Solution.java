class Solution {
    public String minWindow(String s, String t) {
        int[] count = new int['z' - 'A' + 1];
        int uniq = 0;
        for (int i = 0; i < t.length(); ++i) {
            if (++count[t.charAt(i) - 'A'] == 1) uniq++;
        }
        int found = 0,i = 0,j = 0;
        int minLen = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        while (found < uniq) {
            while (i < s.length()) {
                if (found >= uniq) break;
                if (--count[s.charAt(i) - 'A'] == 0) found++;
                i++;
            }
            if (found < uniq) break;
            while (j < i && count[s.charAt(j) - 'A'] < 0) count[s.charAt(j++) - 'A']++;
            if (i - j < minLen) {
                minLen = i - j;
                minJ = j;
            }
            count[s.charAt(j++) - 'A']++;
            found--;
        }
        return minLen < Integer.MAX_VALUE ? s.substring(minJ, minJ + minLen) : "";
    }
}