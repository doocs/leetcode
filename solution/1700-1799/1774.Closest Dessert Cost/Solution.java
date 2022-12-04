class Solution {
    private List<Integer> arr = new ArrayList<>();
    private int[] ts;
    private int inf = 1 << 30;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        ts = toppingCosts;
        dfs(0, 0);
        Collections.sort(arr);
        int d = inf, ans = inf;
        for (int x : baseCosts) {
            for (int y : arr) {
                int i = search(target - x - y);
                for (int j : new int[] {i, i - 1}) {
                    if (j >= 0 && j < arr.size()) {
                        int t = Math.abs(x + y + arr.get(j) - target);
                        if (d > t || (d == t && ans > x + y + arr.get(j))) {
                            d = t;
                            ans = x + y + arr.get(j);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private int search(int x) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private void dfs(int i, int t) {
        if (i >= ts.length) {
            arr.add(t);
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t + ts[i]);
    }
}