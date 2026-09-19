class MKAverage {
    private static final int N = 100001;
    private final int m, k;
    private long s;
    private final Deque<Integer> q = new ArrayDeque<>();
    private final int[] c = new int[N];

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        q.offer(num);
        if (q.size() == m) {
            for (int x : q) {
                update(x, 1);
            }
            for (int i = k; i < m - k; ++i) {
                s += kth(i);
            }
        } else if (q.size() > m) {
            int i = rank(num);
            if (i < k) {
                s += kth(k - 1);
            } else if (i <= m - k) {
                s += num;
            } else {
                s += kth(m - k);
            }
            update(num, 1);

            int x = q.poll();
            i = rank(x);
            if (i < k) {
                s -= kth(k);
            } else if (i <= m - k) {
                s -= x;
            } else {
                s -= kth(m - k);
            }
            update(x, -1);
        }
    }

    public int calculateMKAverage() {
        return q.size() < m ? -1 : (int) (s / (m - k * 2));
    }

    private void update(int x, int d) {
        for (; x < N; x += x & -x) {
            c[x] += d;
        }
    }

    private int query(int x) {
        int ans = 0;
        for (; x > 0; x -= x & -x) {
            ans += c[x];
        }
        return ans;
    }

    private int rank(int x) {
        return query(x - 1);
    }

    private int kth(int k) {
        int need = k + 1;
        int idx = 0;
        for (int p = 1 << 16; p > 0; p >>= 1) {
            int nxt = idx + p;
            if (nxt < N && c[nxt] < need) {
                need -= c[nxt];
                idx = nxt;
            }
        }
        return idx + 1;
    }
}
