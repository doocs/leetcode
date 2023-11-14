class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1, -1);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = -1;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    vector<int> maximumSumQueries(vector<int>& nums1, vector<int>& nums2, vector<vector<int>>& queries) {
        vector<pair<int, int>> nums;
        int n = nums1.size(), m = queries.size();
        for (int i = 0; i < n; ++i) {
            nums.emplace_back(-nums1[i], nums2[i]);
        }
        sort(nums.begin(), nums.end());
        sort(nums2.begin(), nums2.end());
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return queries[j][0] < queries[i][0]; });
        vector<int> ans(m);
        int j = 0;
        BinaryIndexedTree tree(n);
        for (int i : idx) {
            int x = queries[i][0], y = queries[i][1];
            for (; j < n && -nums[j].first >= x; ++j) {
                int k = nums2.end() - lower_bound(nums2.begin(), nums2.end(), nums[j].second);
                tree.update(k, -nums[j].first + nums[j].second);
            }
            int k = nums2.end() - lower_bound(nums2.begin(), nums2.end(), y);
            ans[i] = tree.query(k);
        }
        return ans;
    }
};