import java.util.*;

public class Solution {
    private Map<List<Integer>, int[]> memo = new HashMap<>();

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        return dp(firstPlayer, n - secondPlayer + 1, n);
    }

    private int[] dp(int l, int r, int k) {
        if (l == r) return new int[]{1, 1};
        if (l > r) return dp(r, l, k);

        List<Integer> key = Arrays.asList(l, r, k);
        if (memo.containsKey(key)) return memo.get(key);

        int earliest = Integer.MAX_VALUE;
        int latest = Integer.MIN_VALUE;

        // Enumerate all possible positions
        for (int i = 1; i <= l; ++i) {
            for (int j = l - i + 1; j <= r - i + 1; ++j) {
                if (!(l + r - k / 2 <= i + j && i + j <= (k + 1) / 2)) continue;

                int[] result = dp(i, j, (k + 1) / 2);
                earliest = Math.min(earliest, result[0] + 1);
                latest = Math.max(latest, result[1] + 1);
            }
        }

        int[] result = new int[]{earliest, latest};
        memo.put(key, result);
        return result;
    }
}
