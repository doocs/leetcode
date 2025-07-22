class Solution {
    public int minOperations(int[] nums) {
        List<Integer> g = new ArrayList<>();
        for (int x : nums) {
            int l = 0, r = g.size();
            while (l < r) {
                int mid = (l + r) >> 1;
                if (g.get(mid) < x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (l == g.size()) {
                g.add(x);
            } else {
                g.set(l, x);
            }
        }
        return g.size();
    }
}