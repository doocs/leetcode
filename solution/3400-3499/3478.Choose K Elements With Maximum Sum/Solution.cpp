class Solution {
public:
    vector<long long> findMaxSum(vector<int>& nums1, vector<int>& nums2, int k) {
        int n = nums1.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {nums1[i], i};
        }
        ranges::sort(arr);
        priority_queue<int, vector<int>, greater<int>> pq;
        long long s = 0;
        int j = 0;
        vector<long long> ans(n);
        for (int h = 0; h < n; ++h) {
            auto [x, i] = arr[h];
            while (j < h && arr[j].first < x) {
                int y = nums2[arr[j].second];
                pq.push(y);
                s += y;
                if (pq.size() > k) {
                    s -= pq.top();
                    pq.pop();
                }
                ++j;
            }
            ans[i] = s;
        }
        return ans;
    }
};
