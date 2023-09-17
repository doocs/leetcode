class Solution {
    int n;
    int k;
    int budget;
    List<List<Integer>> composition;
    List<Integer> stock;
    List<Integer> cost;

    boolean isValid(long target) {
        for (int i = 0; i < k; i++) {
            long remain = budget;
            List<Integer> currMachine = composition.get(i);
            for (int j = 0; j < n && remain >= 0; j++) {
                long need = Math.max(0, currMachine.get(j) * target - stock.get(j));
                remain -= need * cost.get(j);
            }
            if (remain >= 0) {
                return true;
            }
        }
        return false;
    }

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition,
            List<Integer> stock, List<Integer> cost) {
        this.n = n;
        this.k = k;
        this.budget = budget;
        this.composition = composition;
        this.stock = stock;
        this.cost = cost;
        int l = -1;
        int r = budget / cost.get(0) + stock.get(0);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (isValid(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}