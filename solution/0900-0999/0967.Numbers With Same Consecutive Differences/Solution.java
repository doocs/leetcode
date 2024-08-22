class Solution {
    private List<Integer> ans = new ArrayList<>();
    private int boundary;
    private int k;

    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        boundary = (int) Math.pow(10, n - 1);
        for (int i = 1; i < 10; ++i) {
            dfs(i);
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(int x) {
        if (x >= boundary) {
            ans.add(x);
            return;
        }
        int last = x % 10;
        if (last + k < 10) {
            dfs(x * 10 + last + k);
        }
        if (k != 0 && last - k >= 0) {
            dfs(x * 10 + last - k);
        }
    }
}
