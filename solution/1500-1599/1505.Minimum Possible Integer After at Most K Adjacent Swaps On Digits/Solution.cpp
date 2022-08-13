class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    string minInteger(string num, int k) {
        vector<queue<int>> pos(10);
        int n = num.size();
        for (int i = 0; i < n; ++i) pos[num[i] - '0'].push(i + 1);
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        string ans = "";
        for (int i = 1; i <= n; ++i) {
            for (int v = 0; v < 10; ++v) {
                auto& q = pos[v];
                if (!q.empty()) {
                    int j = q.front();
                    int dist = tree->query(n) - tree->query(j) + j - i;
                    if (dist <= k) {
                        k -= dist;
                        q.pop();
                        ans += (v + '0');
                        tree->update(j, 1);
                        break;
                    }
                }
            }
        }
        return ans;
    }
};