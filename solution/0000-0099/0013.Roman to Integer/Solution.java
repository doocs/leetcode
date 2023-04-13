class Solution {
    public int romanToInt(String s) {
        String cs = "IVXLCDM";
        int[] vs = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> d = new HashMap<>();
        for (int i = 0; i < vs.length; ++i) {
            d.put(cs.charAt(i), vs[i]);
        }
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n - 1; ++i) {
            if (d.get(s.charAt(i)) < d.get(s.charAt(i + 1))) {
                ans -= d.get(s.charAt(i));
            } else {
                ans += d.get(s.charAt(i));
            }
        }
        ans += d.get(s.charAt(n - 1));
        return ans;
    }
}