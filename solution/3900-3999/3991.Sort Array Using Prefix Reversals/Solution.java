class Solution {
    public int sortArray(int[] nums, int[] pre) {
        int n = nums.length;

        int target = 0;
        for (int i = 0; i < n; i++) {
            target = target * 8 + i;
        }

        int start = 0;
        for (int x : nums) {
            start = start * 8 + x;
        }

        if (start == target) {
            return 0;
        }

        Set<Integer> vis = new HashSet<>();
        vis.add(start);

        Deque<int[]> q = new ArrayDeque<>();
        Deque<Integer> dist = new ArrayDeque<>();
        q.offer(nums.clone());
        dist.offer(0);

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int d = dist.poll();
            int nd = d + 1;

            for (int x : pre) {
                int[] nxt = state.clone();

                int l = 0, r = x - 1;
                while (l < r) {
                    int t = nxt[l];
                    nxt[l] = nxt[r];
                    nxt[r] = t;
                    l++;
                    r--;
                }

                int key = 0;
                for (int v : nxt) {
                    key = key * 8 + v;
                }

                if (key == target) {
                    return nd;
                }

                if (vis.add(key)) {
                    q.offer(nxt);
                    dist.offer(nd);
                }
            }
        }

        return -1;
    }
}