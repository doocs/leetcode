class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        int n = nums.size();
        int inv = 0;

        set<pair<long long, int>> sl;
        set<int> idx;
        vector<long long> arr(nums.begin(), nums.end());

        for (int i = 0; i < n; ++i) idx.insert(i);

        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                ++inv;
            }
            sl.insert({(long long) nums[i] + nums[i + 1], i});
        }

        int ans = 0;
        while (inv > 0) {
            ++ans;

            auto it = sl.begin();
            long long s = it->first;
            int i = it->second;
            sl.erase(it);

            auto j_it = idx.upper_bound(i);
            int j = *j_it;

            if (arr[i] > arr[j]) {
                --inv;
            }

            auto i_it = idx.find(i);
            if (i_it != idx.begin()) {
                auto h_it = prev(i_it);
                int h = *h_it;

                if (arr[h] > arr[i]) {
                    --inv;
                }
                sl.erase({arr[h] + arr[i], h});

                if (arr[h] > s) {
                    ++inv;
                }
                sl.insert({arr[h] + s, h});
            }

            auto k_it = next(j_it);
            if (k_it != idx.end()) {
                int k = *k_it;

                if (arr[j] > arr[k]) {
                    --inv;
                }
                sl.erase({arr[j] + arr[k], j});

                if (s > arr[k]) {
                    ++inv;
                }
                sl.insert({s + arr[k], i});
            }

            arr[i] = s;
            idx.erase(j);
        }

        return ans;
    }
};
