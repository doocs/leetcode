class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c = vector<int>(n + 1);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
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
};

class Solution {
public:
    vector<int> longestObstacleCourseAtEachPosition(vector<int>& obstacles) {
        vector<int> nums = obstacles;
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<int> ans(n);
        BinaryIndexedTree tree(n);
        for (int k = 0; k < n; ++k) {
            int x = obstacles[k];
            auto it = lower_bound(nums.begin(), nums.end(), x);
            int i = distance(nums.begin(), it) + 1;
            ans[k] = tree.query(i) + 1;
            tree.update(i, ans[k]);
        }
        return ans;
    }
};