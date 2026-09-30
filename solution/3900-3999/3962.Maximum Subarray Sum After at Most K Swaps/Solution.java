class Fenwick {
    int n;
    int[] count;
    long[] sum;

    Fenwick(int n) {
        this.n = n;
        count = new int[n + 1];
        sum = new long[n + 1];
    }

    void add(int idx, int cnt, long val) {
        ++idx;
        while (idx <= n) {
            count[idx] += cnt;
            sum[idx] += val;
            idx += idx & -idx;
        }
    }

    int prefixCount(int idx) {
        int res = 0;
        while (idx > 0) {
            res += count[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    long prefixSum(int idx) {
        long res = 0;
        while (idx > 0) {
            res += sum[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    int kth(int k) {
        int idx = 0;
        for (int bit = Integer.highestOneBit(n); bit > 0; bit >>= 1) {
            int next = idx + bit;
            if (next <= n && count[next] < k) {
                idx = next;
                k -= count[next];
            }
        }
        return idx;
    }

    long sumSmallest(int k, int[] values) {
        if (k <= 0) {
            return 0;
        }
        int pos = kth(k);
        int before = prefixCount(pos);
        return prefixSum(pos) + (long) (k - before) * values[pos];
    }

    long sumLargest(int k, int[] values) {
        int total = prefixCount(n);
        if (k <= 0) {
            return 0;
        }
        if (k >= total) {
            return prefixSum(n);
        }
        return prefixSum(n) - sumSmallest(total - k, values);
    }
}

class Solution {
    public long maxSum(int[] nums, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }
        int[] values = Arrays.copyOf(sorted, m);
        int[] idx = new int[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = Arrays.binarySearch(values, nums[i]);
        }

        long ans = Long.MIN_VALUE;
        for (int l = 0; l < n; ++l) {
            Fenwick inside = new Fenwick(m);
            Fenwick outside = new Fenwick(m);
            for (int i = 0; i < n; ++i) {
                outside.add(idx[i], 1, nums[i]);
            }
            long window = 0;
            for (int r = l; r < n; ++r) {
                outside.add(idx[r], -1, -nums[r]);
                inside.add(idx[r], 1, nums[r]);
                window += nums[r];
                int inCnt = r - l + 1;
                int outCnt = n - inCnt;
                int limit = Math.min(k, Math.min(inCnt, outCnt));
                if (limit == 0) {
                    ans = Math.max(ans, window);
                    continue;
                }
                int lo = 1;
                int hi = limit;
                int best = 0;
                while (lo <= hi) {
                    int mid = (lo + hi) >>> 1;
                    int small = inside.kth(mid);
                    int large = outside.kth(outCnt - mid + 1);
                    if (values[small] < values[large]) {
                        best = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                if (best == 0) {
                    ans = Math.max(ans, window);
                } else {
                    long cand = window + outside.sumLargest(best, values)
                        - inside.sumSmallest(best, values);
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans;
    }
}
