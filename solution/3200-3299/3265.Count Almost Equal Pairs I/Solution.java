class Solution {
    public int countPairs(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            Set<Integer> vis = new HashSet<>();
            vis.add(x);
            char[] s = String.valueOf(x).toCharArray();
            for (int j = 0; j < s.length; ++j) {
                for (int i = 0; i < j; ++i) {
                    swap(s, i, j);
                    vis.add(Integer.parseInt(String.valueOf(s)));
                    swap(s, i, j);
                }
            }
            for (int y : vis) {
                ans += cnt.getOrDefault(y, 0);
            }
            cnt.merge(x, 1, Integer::sum);
        }
        return ans;
    }

    private void swap(char[] s, int i, int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }
}
