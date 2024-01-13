class Solution {
    public int maxProduct(String[] words) {
        Map<Integer, Integer> mask = new HashMap<>();
        int ans = 0;
        for (var s : words) {
            int a = s.length();
            int x = 0;
            for (char c : s.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            for (var e : mask.entrySet()) {
                int y = e.getKey(), b = e.getValue();
                if ((x & y) == 0) {
                    ans = Math.max(ans, a * b);
                }
            }
            mask.merge(x, a, Math::max);
        }
        return ans;
    }
}