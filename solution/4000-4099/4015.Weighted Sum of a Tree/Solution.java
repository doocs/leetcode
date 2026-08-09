class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = nums.length;

        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());

        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }

        long ans = 0;

        List<Integer> q = new ArrayList<>();
        q.add(0);

        int d = 0;

        while (!q.isEmpty()) {
            d++;

            List<Integer> nq = new ArrayList<>();

            for (int i : q) {
                ans += (long) nums[i] * (1 - d);
                nq.addAll(g[i]);
            }

            q = nq;
        }

        long sum = 0;
        for (int x : nums) {
            sum += x;
        }

        ans += (long) d * sum;

        return ans;
    }
}