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

class MRUQueue {
    private int n;
    private int[] data;
    private BinaryIndexedTree tree;

    public MRUQueue(int n) {
        this.n = n;
        data = new int[n + 2010];
        for (int i = 1; i <= n; ++i) {
            data[i] = i;
        }
        tree = new BinaryIndexedTree(n + 2010);
    }
    
    public int fetch(int k) {
        int left = 1;
        int right = n++;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid - tree.query(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        data[n] = data[left];
        tree.update(left, 1);
        return data[left];
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue obj = new MRUQueue(n);
 * int param_1 = obj.fetch(k);
 */