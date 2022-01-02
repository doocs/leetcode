class Solution {
    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        int t = 0, mx = 0;
        int j = 0;
        for (int i = 0; i < array.length; ++i) {
            t += Character.isDigit(array[i].charAt(0)) ? 1 : -1;
            if (seen.containsKey(t)) {
                if (mx < i - seen.get(t)) {
                    mx = i - seen.get(t);
                    j = seen.get(t) + 1;
                }
            } else {
                seen.put(t, i);
            }
        }
        String[] ans = new String[mx];
        for (int i = 0; i < mx; ++i) {
            ans[i] = array[i + j];
        }
        return ans;
    }
}