class Solution {
    public int[][] highFive(int[][] items) {
        List<Integer>[] d = new List[1001];
        Arrays.setAll(d, k -> new ArrayList<>());
        for (var item : items) {
            int i = item[0], x = item[1];
            d[i].add(x);
        }
        for (var xs : d) {
            xs.sort((a, b) -> b - a);
        }
        List<int[]> ans = new ArrayList<>();
        for (int i = 1; i <= 1000; ++i) {
            var xs = d[i];
            if (!xs.isEmpty()) {
                int s = 0;
                for (int j = 0; j < 5; ++j) {
                    s += xs.get(j);
                }
                ans.add(new int[] {i, s / 5});
            }
        }
        return ans.toArray(new int[0][]);
    }
}