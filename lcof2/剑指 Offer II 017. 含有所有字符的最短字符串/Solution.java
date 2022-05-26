// class Solution {
//     public String minWindow(String s, String t) {
//         int m = s.length(), n = t.length();
//         if (n > m) {
//             return "";
//         }
//         Map<Character, Integer> need = new HashMap<>();
//         Map<Character, Integer> window = new HashMap<>();
//         for (char ch : t.toCharArray()) {
//             need.merge(ch, 1, Integer::sum);
//         }
//         int start = 0, minLen = Integer.MAX_VALUE;
//         int left = 0, right = 0;
//         while (right < m) {
//             window.merge(s.charAt(right++), 1, Integer::sum);
//             while (check(need, window)) {
//                 if (right - left < minLen) {
//                     minLen = right - left;
//                     start = left;
//                 }
//                 window.merge(s.charAt(left++), -1, Integer::sum);
//             }
//         }
//         return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
//     }

//     private boolean check(Map<Character, Integer> need, Map<Character, Integer> window) {
//         for (Map.Entry<Character, Integer> entry : need.entrySet()) {
//             if (window.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int needCount = 0, windowCount = 0;
        for (char ch : t.toCharArray()) {
            if (!need.containsKey(ch)) {
                needCount++;
            }
            need.merge(ch, 1, Integer::sum);
        }
        int start = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < m) {
            char ch = s.charAt(right++);
            if (need.containsKey(ch)) {
                int val = window.getOrDefault(ch, 0) + 1;
                if (val == need.get(ch)) {
                    windowCount++;
                }
                window.put(ch, val);
            }
            while (windowCount == needCount) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                ch = s.charAt(left++);
                if (need.containsKey(ch)) {
                    int val = window.get(ch);
                    if (val == need.get(ch)) {
                        windowCount--;
                    }
                    window.put(ch, val - 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
