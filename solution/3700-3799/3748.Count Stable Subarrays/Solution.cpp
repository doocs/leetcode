class Solution {
public:
    vector<long long> countStableSubarrays(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<int> seg;
        vector<long long> s = {0};

        int l = 0;
        for (int r = 0; r < n; ++r) {
            if (r == n - 1 || nums[r] > nums[r + 1]) {
                seg.push_back(l);
                long long k = r - l + 1;
                s.push_back(s.back() + k * (k + 1) / 2);
                l = r + 1;
            }
        }

        vector<long long> ans;
        for (auto& q : queries) {
            int left = q[0], right = q[1];

            int i = upper_bound(seg.begin(), seg.end(), left) - seg.begin();
            int j = upper_bound(seg.begin(), seg.end(), right) - seg.begin() - 1;

            if (i > j) {
                long long k = right - left + 1;
                ans.push_back(k * (k + 1) / 2);
            } else {
                long long a = seg[i] - left;
                long long b = right - seg[j] + 1;
                ans.push_back(a * (a + 1) / 2 + s[j] - s[i] + b * (b + 1) / 2);
            }
        }

        return ans;
    }
};
