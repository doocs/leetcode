class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] counter = new int[26];
        for (int i = 0; i < p.length(); ++i) {
            ++counter[p.charAt(i) - 'a'];
        }
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;
        int[] t = new int[26];
        while (right < s.length()) {
            int i = s.charAt(right) - 'a';
            ++t[i];
            while (t[i] > counter[i]) {
                --t[s.charAt(left) - 'a'];
                ++left;
            }
            if (right - left == p.length() - 1) {
                res.add(left);
            }
            ++right;
        }
        return res;
    }
}