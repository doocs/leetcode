class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = n * 2;
        int[] d = new int[m];
        Arrays.fill(d, m);

        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x = nums[i % n];
            if (left.containsKey(x)) {
                d[i] = Math.min(d[i], i - left.get(x));
            }
            left.put(x, i);
        }

        Map<Integer, Integer> right = new HashMap<>();
        for (int i = m - 1; i >= 0; i--) {
            int x = nums[i % n];
            if (right.containsKey(x)) {
                d[i] = Math.min(d[i], right.get(x) - i);
            }
            right.put(x, i);
        }

        for (int i = 0; i < n; i++) {
            d[i] = Math.min(d[i], d[i + n]);
        }

        List<Integer> ans = new ArrayList<>();
        for (int query : queries) {
            ans.add(d[query] >= n ? -1 : d[query]);
        }
        return ans;
    }
}
