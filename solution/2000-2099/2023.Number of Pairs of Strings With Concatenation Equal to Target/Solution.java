class Solution {
    public int numOfPairs(String[] nums, String target) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        int ans = 0;
        for (int i = 1; i < target.length(); ++i) {
            String a = target.substring(0, i);
            String b = target.substring(i);
            int x = cnt.getOrDefault(a, 0);
            int y = cnt.getOrDefault(b, 0);
            if (!a.equals(b)) {
                ans += x * y;
            } else {
                ans += x * (y - 1);
            }
        }
        return ans;
    }
}