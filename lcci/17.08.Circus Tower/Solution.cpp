class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int val) {
        while (x <= n) {
            c[x] = max(c[x], val);
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s = max(s, c[x]);
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int bestSeqAtIndex(vector<int>& height, vector<int>& weight) {
        int n = height.size();
        vector<pair<int, int>> people;
        for (int i = 0; i < n; ++i) {
            people.emplace_back(height[i], weight[i]);
        }
        sort(people.begin(), people.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
            if (a.first == b.first) {
                return a.second > b.second;
            }
            return a.first < b.first;
        });
        vector<int> alls = weight;
        sort(alls.begin(), alls.end());
        alls.erase(unique(alls.begin(), alls.end()), alls.end());
        BinaryIndexedTree tree(alls.size());
        int ans = 1;
        for (auto& [_, w] : people) {
            int x = lower_bound(alls.begin(), alls.end(), w) - alls.begin() + 1;
            int t = tree.query(x - 1) + 1;
            ans = max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
};