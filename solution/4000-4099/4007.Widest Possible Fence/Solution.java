class Solution {
    public int maximumWidth(int[] planks) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : planks) {
            cnt.merge(x, 1, Integer::sum);
        }

        Map<Integer, Integer> t = new HashMap<>();
        int ans = 0;

        for (var e1 : cnt.entrySet()) {
            int x = e1.getKey();
            int v1 = e1.getValue();

            t.merge(x, v1, Integer::sum);
            ans = Math.max(ans, t.get(x));

            t.merge(x * 2, v1 / 2, Integer::sum);
            ans = Math.max(ans, t.get(x * 2));

            for (var e2 : cnt.entrySet()) {
                int y = e2.getKey();
                int v2 = e2.getValue();
                if (y > x) {
                    int key = x + y;
                    t.merge(key, Math.min(v1, v2), Integer::sum);
                    ans = Math.max(ans, t.get(key));
                }
            }
        }

        return ans;
    }
}