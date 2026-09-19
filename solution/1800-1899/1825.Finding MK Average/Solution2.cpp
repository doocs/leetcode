class MKAverage {
public:
    MKAverage(int m, int k) {
        this->m = m;
        this->k = k;
    }

    void addElement(int num) {
        q.push_back(num);
        if ((int) q.size() == m) {
            for (int x : q) {
                update(x, 1);
            }
            for (int i = k; i < m - k; ++i) {
                s += kth(i);
            }
        } else if ((int) q.size() > m) {
            int i = rank(num);
            if (i < k) {
                s += kth(k - 1);
            } else if (i <= m - k) {
                s += num;
            } else {
                s += kth(m - k);
            }
            update(num, 1);

            int x = q.front();
            q.pop_front();
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

    int calculateMKAverage() {
        return (int) q.size() < m ? -1 : s / (m - k * 2);
    }

private:
    static const int N = 100001;
    int m, k;
    long long s = 0;
    deque<int> q;
    int c[N]{};

    void update(int x, int d) {
        for (; x < N; x += x & -x) {
            c[x] += d;
        }
    }

    int query(int x) {
        int ans = 0;
        for (; x > 0; x -= x & -x) {
            ans += c[x];
        }
        return ans;
    }

    int rank(int x) {
        return query(x - 1);
    }

    int kth(int k) {
        int need = k + 1, idx = 0;
        for (int p = 1 << 16; p > 0; p >>= 1) {
            int nxt = idx + p;
            if (nxt < N && c[nxt] < need) {
                need -= c[nxt];
                idx = nxt;
            }
        }
        return idx + 1;
    }
};
