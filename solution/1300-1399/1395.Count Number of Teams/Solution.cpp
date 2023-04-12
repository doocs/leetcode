class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
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
    int numTeams(vector<int>& rating) {
        vector<int> nums = rating;
        sort(nums.begin(), nums.end());
        nums.erase(unique(nums.begin(), nums.end()), nums.end());
        int m = nums.size();
        BinaryIndexedTree tree1(m);
        BinaryIndexedTree tree2(m);
        for (int& v : rating) {
            int x = lower_bound(nums.begin(), nums.end(), v) - nums.begin() + 1;
            tree2.update(x, 1);
        }
        int ans = 0;
        int n = rating.size();
        for (int i = 0; i < n; ++i) {
            int x = lower_bound(nums.begin(), nums.end(), rating[i]) - nums.begin() + 1;
            tree1.update(x, 1);
            tree2.update(x, -1);
            int l = tree1.query(x - 1);
            int r = n - i - 1 - tree2.query(x);
            ans += l * r;
            ans += (i - l) * (n - i - 1 - r);
        }
        return ans;
    }
};