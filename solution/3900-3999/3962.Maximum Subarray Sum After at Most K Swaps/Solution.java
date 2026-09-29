class Solution {
    static class Fenwick {
        int n;
        int[] count;
        long[] sum;
        Fenwick(int n) {
            this.n = n;
            count = new int[n + 1];
            sum = new long[n + 1];
        }
        void add(int idx, int cnt, long val) {
            idx++;
            while (idx <= n) {
                count[idx] += cnt;
                sum[idx] += val;
                idx += idx & -idx;
            }
        }
        int count(int idx) {
            int res = 0;
            while (idx > 0) {
                res += count[idx];
                idx -= idx & -idx;
            }
            return res;
        }
        long sum(int idx) {
            long res = 0;
            while (idx > 0) {
                res += sum[idx];
                idx -= idx & -idx;
            }
            return res;
        }
        int kth(int k) {
            int idx = 0;
            for (int bit = Integer.highestOneBit(n); bit != 0; bit >>= 1) {
                int next = idx + bit;
                if (next <= n && count[next] < k) {
                    idx = next;
                    k -= count[next];
                }
            }
            return idx;
        }
        long firstK(int k, int[] values) {
            if (k <= 0)
                return 0;

            int pos = kth(k);

            int cntBefore = count(pos);
            long sumBefore = sum(pos);

            return sumBefore + (long)(k - cntBefore) * values[pos];
        }
        long lastK(int k, int[] values) {
            int total = count(n);

            if (k <= 0)
                return 0;

            if (k >= total)
                return sum(n);

            return sum(n) - firstK(total - k, values);
        }
        int totalCount() {
            return count(n);
        }
    }
    public long maxSum(int[] nums, int k) {
        int n = nums.length;
        int[] values = nums.clone();
        Arrays.sort(values);

        int m = 0;

        for (int x : values) {
            if (m == 0 || values[m - 1] != x) {
                values[m++] = x;
            }
        }
        values = Arrays.copyOf(values, m);
        Fenwick inside = new Fenwick(m);
        Fenwick outside = new Fenwick(m);
        for (int x : nums) {
            int idx = Arrays.binarySearch(values, x);
            outside.add(idx, 1, x);
        }
        long answer = Long.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            inside = new Fenwick(m);
            outside = new Fenwick(m);
            for (int x : nums) {
                int idx = Arrays.binarySearch(values, x);
                outside.add(idx, 1, x);
            }
            long windowSum = 0;
            for (int r = l; r < n; r++) {
                int idx = Arrays.binarySearch(values, nums[r]);
                outside.add(idx, -1, -nums[r]);
                inside.add(idx, 1, nums[r]);
                windowSum += nums[r];
                int insideCount = r - l + 1;
                int outsideCount = n - insideCount;
                int maxSwaps = Math.min(
                        k,
                        Math.min(insideCount, outsideCount)
                );
                if (maxSwaps == 0) {
                    answer = Math.max(answer, windowSum);
                    continue;
                }
                int lo = 1;
                int hi = maxSwaps;
                int best = 0;
                while (lo <= hi) {
                    int mid = (lo + hi) >>> 1;
                    int smallestPos = inside.kth(mid);
                    int largestPos =
                            outside.kth(outsideCount - mid + 1);
                    if (values[smallestPos] < values[largestPos]) {
                        best = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                if (best > 0) {
                    long smallest =
                            inside.firstK(best, values);
                    long largest =
                            outside.lastK(best, values);
                    long candidate =
                            windowSum + largest - smallest;
                    answer = Math.max(answer, candidate);
                } else {
                    answer = Math.max(answer, windowSum);
                }
            }
        }
        return answer;
    }
}
