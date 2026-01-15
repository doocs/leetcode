class Solution {
    record Pair(long s, int i) implements Comparable<Pair> {
        @Override
        public int compareTo(Pair other) {
            int compareS = Long.compare(this.s, other.s);
            return compareS != 0 ? compareS : Integer.compare(this.i, other.i);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        int inv = 0;
        TreeSet<Pair> sl = new TreeSet<>();
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                ++inv;
            }
            sl.add(new Pair(nums[i] + nums[i + 1], i));
        }
        TreeSet<Integer> idx = new TreeSet<>();
        long[] arr = new long[n];
        for (int i = 0; i < n; ++i) {
            idx.add(i);
            arr[i] = nums[i];
        }

        int ans = 0;
        while (inv > 0) {
            ++ans;
            var p = sl.pollFirst();
            long s = p.s;
            int i = p.i;
            int j = idx.higher(i);
            if (arr[i] > arr[j]) {
                --inv;
            }
            Integer h = idx.lower(i);
            if (h != null) {
                if (arr[h] > arr[i]) {
                    --inv;
                }
                sl.remove(new Pair(arr[h] + arr[i], h));
                if (arr[h] > s) {
                    ++inv;
                }
                sl.add(new Pair(arr[h] + s, h));
            }
            Integer k = idx.higher(j);
            if (k != null) {
                if (arr[j] > arr[k]) {
                    --inv;
                }
                sl.remove(new Pair(arr[j] + arr[k], j));
                if (s > arr[k]) {
                    ++inv;
                }
                sl.add(new Pair(s + arr[k], i));
            }
            arr[i] = s;
            idx.remove(j);
        }
        return ans;
    }
}
