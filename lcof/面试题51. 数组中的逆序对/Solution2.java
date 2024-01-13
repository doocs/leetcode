class Solution {
    public int reversePairs(int[] nums) {
        Set<Integer> s = new TreeSet<>();
        for (int v : nums) {
            s.add(v);
        }
        Map<Integer, Integer> alls = new HashMap<>();
        int i = 1;
        for (int v : s) {
            alls.put(v, i++);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(s.size());
        int ans = 0;
        for (i = nums.length - 1; i >= 0; --i) {
            int x = alls.get(nums[i]);
            ans += tree.query(x - 1);
            tree.update(x, 1);
        }
        return ans;
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}