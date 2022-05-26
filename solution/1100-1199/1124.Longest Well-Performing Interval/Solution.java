class Solution {
    public int longestWPI(int[] hours) {
        int s = 0, ans = 0;
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < hours.length; ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                ans = i + 1;
            } else {
                seen.putIfAbsent(s, i);
                if (seen.containsKey(s - 1)) {
                    ans = Math.max(ans, i - seen.get(s - 1));
                }
            }
        }
        return ans;
    }
}