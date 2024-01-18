class BinaryIndexedTree {
private:
    int n;
    vector<int> c;
    vector<int> d;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1, 0)
        , d(n + 1, 0) {}

    void update(int x, int v, int cnt) {
        while (x <= n) {
            if (c[x] < v) {
                c[x] = v;
                d[x] = cnt;
            } else if (c[x] == v) {
                d[x] += cnt;
            }
            x += x & -x;
        }
    }

    pair<int, int> query(int x) {
        int v = 0, cnt = 0;
        while (x > 0) {
            if (c[x] > v) {
                v = c[x];
                cnt = d[x];
            } else if (c[x] == v) {
                cnt += d[x];
            }
            x -= x & -x;
        }
        return {v, cnt};
    }
};

class Solution {
public:
    int findNumberOfLIS(vector<int>& nums) {
        vector<int> arr = nums;
        sort(arr.begin(), arr.end());
        arr.erase(unique(arr.begin(), arr.end()), arr.end());
        int m = arr.size();
        BinaryIndexedTree tree(m);
        for (int x : nums) {
            auto it = lower_bound(arr.begin(), arr.end(), x);
            int i = distance(arr.begin(), it) + 1;
            auto [v, cnt] = tree.query(i - 1);
            tree.update(i, v + 1, max(cnt, 1));
        }
        return tree.query(m).second;
    }
};