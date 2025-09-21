class Solution {
public:
    int minSplitMerge(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> target = nums2;
        vector<vector<int>> q{nums1};
        set<vector<int>> vis;
        vis.insert(nums1);

        for (int ans = 0;; ++ans) {
            vector<vector<int>> t = q;
            q.clear();
            for (auto& cur : t) {
                if (cur == target) {
                    return ans;
                }
                for (int l = 0; l < n; ++l) {
                    for (int r = l; r < n; ++r) {
                        vector<int> remain;
                        remain.insert(remain.end(), cur.begin(), cur.begin() + l);
                        remain.insert(remain.end(), cur.begin() + r + 1, cur.end());
                        vector<int> sub(cur.begin() + l, cur.begin() + r + 1);
                        for (int i = 0; i <= (int) remain.size(); ++i) {
                            vector<int> nxt;
                            nxt.insert(nxt.end(), remain.begin(), remain.begin() + i);
                            nxt.insert(nxt.end(), sub.begin(), sub.end());
                            nxt.insert(nxt.end(), remain.begin() + i, remain.end());

                            if (!vis.count(nxt)) {
                                vis.insert(nxt);
                                q.push_back(nxt);
                            }
                        }
                    }
                }
            }
        }
    }
};
