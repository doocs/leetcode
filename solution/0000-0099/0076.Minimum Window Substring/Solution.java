class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> mp = new HashMap<>();
        int begin = 0, end = 0, counter = t.length(), minLen = Integer.MAX_VALUE, minStart = 0, size = s.length();

        for (char c : s.toCharArray())
            mp.put(c, 0);
        for (char c : t.toCharArray()) {
            if (mp.containsKey(c))
                mp.put(c, mp.get(c) + 1);
            else
                return "";
        }

        while (end < size) {
            if (mp.get(s.charAt(end)) > 0)
                counter--;

            mp.put(s.charAt(end), mp.get(s.charAt(end)) - 1);

            end++;

            while (counter == 0) {
                if (end - begin < minLen) {
                    minStart = begin;
                    minLen = end - begin;
                }
                mp.put(s.charAt(begin), mp.get(s.charAt(begin)) + 1);

                if (mp.get(s.charAt(begin)) > 0) {
                    counter++;
                }

                begin++;
            }
        }

        if (minLen != Integer.MAX_VALUE) {
            return s.substring(minStart, minStart + minLen);
        }
        return "";
    }
}


// class Solution {
//     public String minWindow(String s, String t) {
//         int[] count = new int['z' - 'A' + 1];
//         int uniq = 0;
//         for (int i = 0; i < t.length(); ++i) {
//             if (++count[t.charAt(i) - 'A'] == 1) uniq++;
//         }
//         int found = 0,i = 0,j = 0;
//         int minLen = Integer.MAX_VALUE;
//         int minJ = Integer.MAX_VALUE;
//         while (found < uniq) {
//             while (i < s.length()) {
//                 if (found >= uniq) break;
//                 if (--count[s.charAt(i) - 'A'] == 0) found++;
//                 i++;
//             }
//             if (found < uniq) break;
//             while (j < i && count[s.charAt(j) - 'A'] < 0) count[s.charAt(j++) - 'A']++;
//             if (i - j < minLen) {
//                 minLen = i - j;
//                 minJ = j;
//             }
//             count[s.charAt(j++) - 'A']++;
//             found--;
//         }
//         return minLen < Integer.MAX_VALUE ? s.substring(minJ, minJ + minLen) : "";
//     }
// }
