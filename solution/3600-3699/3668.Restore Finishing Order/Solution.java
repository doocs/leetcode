class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        int n = order.length;
        int[] d = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            d[order[i]] = i;
        }
        return Arrays.stream(friends)
            .boxed()
            .sorted((a, b) -> d[a] - d[b])
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
