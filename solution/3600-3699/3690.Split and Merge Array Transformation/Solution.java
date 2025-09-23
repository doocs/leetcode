class Solution {
    public int minSplitMerge(int[] nums1, int[] nums2) {
        int n = nums1.length;
        List<Integer> target = toList(nums2);
        List<Integer> start = toList(nums1);
        List<List<Integer>> q = List.of(start);
        Set<List<Integer>> vis = new HashSet<>();
        vis.add(start);
        for (int ans = 0;; ++ans) {
            var t = q;
            q = new ArrayList<>();
            for (var cur : t) {
                if (cur.equals(target)) {
                    return ans;
                }
                for (int l = 0; l < n; ++l) {
                    for (int r = l; r < n; ++r) {
                        List<Integer> remain = new ArrayList<>();
                        for (int i = 0; i < l; ++i) {
                            remain.add(cur.get(i));
                        }
                        for (int i = r + 1; i < n; ++i) {
                            remain.add(cur.get(i));
                        }
                        List<Integer> sub = cur.subList(l, r + 1);
                        for (int i = 0; i <= remain.size(); ++i) {
                            List<Integer> nxt = new ArrayList<>();
                            for (int j = 0; j < i; ++j) {
                                nxt.add(remain.get(j));
                            }
                            for (int x : sub) {
                                nxt.add(x);
                            }
                            for (int j = i; j < remain.size(); ++j) {
                                nxt.add(remain.get(j));
                            }
                            if (vis.add(nxt)) {
                                q.add(nxt);
                            }
                        }
                    }
                }
            }
        }
    }

    private List<Integer> toList(int[] arr) {
        List<Integer> res = new ArrayList<>(arr.length);
        for (int x : arr) {
            res.add(x);
        }
        return res;
    }
}
