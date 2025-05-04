class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, List<Integer>> g1 = new HashMap<>();
        Map<Integer, List<Integer>> g2 = new HashMap<>();

        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            g1.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            g2.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        for (var e : g1.entrySet()) {
            Collections.sort(e.getValue());
        }
        for (var e : g2.entrySet()) {
            Collections.sort(e.getValue());
        }

        int ans = 0;

        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            List<Integer> l1 = g1.get(x);
            List<Integer> l2 = g2.get(y);

            if (l2.get(0) < x && x < l2.get(l2.size() - 1) && l1.get(0) < y
                && y < l1.get(l1.size() - 1)) {
                ans++;
            }
        }

        return ans;
    }
}