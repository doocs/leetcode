class Solution {
    private TreeSet<int[]> l = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    private TreeSet<int[]> r = new TreeSet<>(l.comparator());
    private Map<Integer, Integer> cnt = new HashMap<>();
    private int s;

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            remove(v);
            cnt.merge(v, 1, Integer::sum);
            add(v);
            int j = i - k + 1;
            if (j < 0) {
                continue;
            }
            while (!r.isEmpty() && l.size() < x) {
                var p = r.pollLast();
                s += p[0] * p[1];
                l.add(p);
            }
            while (l.size() > x) {
                var p = l.pollFirst();
                s -= p[0] * p[1];
                r.add(p);
            }
            ans[j] = s;

            remove(nums[j]);
            cnt.merge(nums[j], -1, Integer::sum);
            add(nums[j]);
        }
        return ans;
    }

    private void remove(int v) {
        if (!cnt.containsKey(v)) {
            return;
        }
        var p = new int[] {cnt.get(v), v};
        if (l.contains(p)) {
            l.remove(p);
            s -= p[0] * p[1];
        } else {
            r.remove(p);
        }
    }

    private void add(int v) {
        if (!cnt.containsKey(v)) {
            return;
        }
        var p = new int[] {cnt.get(v), v};
        if (!l.isEmpty() && l.comparator().compare(l.first(), p) < 0) {
            l.add(p);
            s += p[0] * p[1];
        } else {
            r.add(p);
        }
    }
}
