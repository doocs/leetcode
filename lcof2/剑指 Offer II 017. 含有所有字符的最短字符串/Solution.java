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
        String res = "";
        int n = s.length(), m = t.length();
        if (n<m) return res;
        int hit=0;
        int left=0;
        int[] arrays = new int[26+26];
        //建立数组
        for (int i=0; i<m; i++)
            changeArray(t.charAt(i), arrays, false);

        for (int i=0; i<n; i++){
            changeArray(s.charAt(i), arrays, true);

            if (getArrOne(s.charAt(i), arrays)<=0) hit++;//命中

            while (hit==m && getArrOne(s.charAt(left), arrays)>0)
                changeArray(s.charAt(left++), arrays, false);

            if (hit==m)
                if (res.equals("")) res = s.substring(left, i+1);
                else res = res.length()<i-left+1? res:s.substring(left, i+1);

        }

        return res;
    }
    void changeArray(char one, int[] array, boolean flag){
        if (flag){
            if (one >= 'a') array[one-'a']++;
            else array[one-'A'+26]++;
        }else {
            if (one >= 'a') array[one-'a']--;
            else array[one-'A'+26]--;
        }
    }
    int getArrOne(char one, int[] array){
        if (one >= 'a') return array[one-'a'];
        else return array[one-'A'+26];
    }
}
