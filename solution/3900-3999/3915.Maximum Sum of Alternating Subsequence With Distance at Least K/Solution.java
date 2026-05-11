class Solution {
    public long maxAlternatingSum(int[] nums, int k) {
        long maxSum = 0;
        int n = nums.length;
        int m = Arrays.stream(nums).max().getAsInt();
        long[][] dp = new long[n][2];
        SegmentTree[] sts = new SegmentTree[2];
        for (int j = 0; j < 2; j++) {
            sts[j] = new SegmentTree(m + 1);
        }
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                sts[0].update(nums[i - k], dp[i - k][0]);
                sts[1].update(nums[i - k], dp[i - k][1]);
            }
            dp[i][0] = sts[1].getMax(0, nums[i] - 1) + nums[i];
            dp[i][1] = sts[0].getMax(nums[i] + 1, m) + nums[i];
            maxSum = Math.max(maxSum, Math.max(dp[i][0], dp[i][1]));
        }
        return maxSum;
    }
}

class SegmentTree {
    private int n;
    private long[] tree;

    public SegmentTree(int n) {
        this.n = n;
        this.tree = new long[n * 4];
    }

    public long getMax(int start, int end) {
        return getMax(start, end, 0, 0, n - 1);
    }

    public void update(int index, long value) {
        update(index, value, 0, 0, n - 1);
    }

    private long getMax(int rangeStart, int rangeEnd, int treeIndex, int treeStart, int treeEnd) {
        if (rangeStart > rangeEnd) {
            return 0;
        }
        if (rangeStart == treeStart && rangeEnd == treeEnd) {
            return tree[treeIndex];
        }
        int mid = treeStart + (treeEnd - treeStart) / 2;
        if (rangeEnd <= mid) {
            return getMax(rangeStart, rangeEnd, treeIndex * 2 + 1, treeStart, mid);
        } else if (rangeStart > mid) {
            return getMax(rangeStart, rangeEnd, treeIndex * 2 + 2, mid + 1, treeEnd);
        } else {
            return Math.max(getMax(rangeStart, mid, treeIndex * 2 + 1, treeStart, mid), getMax(mid + 1, rangeEnd, treeIndex * 2 + 2, mid + 1, treeEnd));
        }
    }

    private void update(int rangeIndex, long value, int treeIndex, int start, int end) {
        if (start == end) {
            tree[treeIndex] = value;
            return;
        }
        int mid = start + (end - start) / 2;
        if (rangeIndex <= mid) {
            update(rangeIndex, value, treeIndex * 2 + 1, start, mid);
        } else {
            update(rangeIndex, value, treeIndex * 2 + 2, mid + 1, end);
        }
        tree[treeIndex] = Math.max(tree[treeIndex * 2 + 1], tree[treeIndex * 2 + 2]);
    }
}