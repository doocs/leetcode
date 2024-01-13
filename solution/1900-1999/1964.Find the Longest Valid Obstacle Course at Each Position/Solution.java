class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] nums = obstacles.clone();
        Arrays.sort(nums);
        int n = nums.length;
        int[] ans = new int[n];
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int k = 0; k < n; ++k) {
            int x = obstacles[k];
            int i = Arrays.binarySearch(nums, x) + 1;
            ans[k] = tree.query(i) + 1;
            tree.update(i, ans[k]);
        }
        return ans;
    }
}