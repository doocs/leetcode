class Solution {
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        List<int[]> arr = new ArrayList<>();
        for (int k = 0; k < costs.length; k++) {
            int a = costs[k], b = capacity[k];
            if (a < budget) {
                arr.add(new int[] {a, b});
            }
        }
        if (arr.isEmpty()) {
            return 0;
        }
        arr.sort(Comparator.comparingInt(o -> o[0]));
        TreeSet<int[]> remain = new TreeSet<>((x, y) -> {
            if (x[0] != y[0]) {
                return x[0] - y[0];
            }
            return x[1] - y[1];
        });
        for (int i = 0; i < arr.size(); i++) {
            remain.add(new int[] {arr.get(i)[1], i});
        }
        int i = 0, j = arr.size() - 1;
        int ans = remain.last()[0];
        while (i < j) {
            remain.remove(new int[] {arr.get(i)[1], i});
            while (i < j && arr.get(i)[0] + arr.get(j)[0] >= budget) {
                remain.remove(new int[] {arr.get(j)[1], j});
                j--;
            }
            if (!remain.isEmpty()) {
                ans = Math.max(ans, arr.get(i)[1] + remain.last()[0]);
            }
            i++;
        }
        return ans;
    }
}
