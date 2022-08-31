class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, int val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= lowbit(x);
        }
        return s;
    }
}

class Solution {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < target.length; ++i) {
            d.put(target[i], i);
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            if (d.containsKey(arr[i])) {
                nums.add(d.get(arr[i]));
            }
        }
        return target.length - lengthOfLIS(nums);
    }

    private int lengthOfLIS(List<Integer> nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> d = new HashMap<>();
        for (int v : ts) {
            d.put(v, idx++);
        }
        int ans = 0;
        BinaryIndexedTree tree = new BinaryIndexedTree(nums.size());
        for (int v : nums) {
            int x = d.get(v);
            int t = tree.query(x - 1) + 1;
            ans = Math.max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
}