class Solution {
    public int longestWPI(int[] hours) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int s = 0;
        for (int i = 0; i < hours.length; ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                res = i + 1;
            } else {
                int b = map.getOrDefault(s - 1, -1);
                if (b != -1) {
                    res = Math.max(res, i - b);
                }
            }
            map.putIfAbsent(s, i);
        }
        return res;
    }
}
