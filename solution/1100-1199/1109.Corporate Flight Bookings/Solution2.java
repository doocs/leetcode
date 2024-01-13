class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (var e : bookings) {
            int first = e[0], last = e[1], seats = e[2];
            tree.update(first, seats);
            tree.update(last + 1, -seats);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = tree.query(i + 1);
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