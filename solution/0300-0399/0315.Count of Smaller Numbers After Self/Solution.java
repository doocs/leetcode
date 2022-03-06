class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort(Comparator.comparingInt(a -> a));
        int n = alls.size();
        Map<Integer, Integer> m = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            m.put(alls.get(i), i + 1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            int x = m.get(nums[i]);
            tree.update(x, 1);
            ans.addFirst(tree.query(x - 1));
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
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}