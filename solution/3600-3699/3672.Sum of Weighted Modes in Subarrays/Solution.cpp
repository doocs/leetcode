class Solution {
public:
    long long modeWeight(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        priority_queue<pair<int, int>> pq; // {freq, -val}

        for (int i = 0; i < k; i++) {
            int x = nums[i];
            cnt[x]++;
            pq.push({cnt[x], -x});
        }

        auto get_mode = [&]() {
            while (true) {
                auto [freq, negVal] = pq.top();
                int val = -negVal;
                if (cnt[val] == freq) {
                    return 1LL * freq * val;
                }
                pq.pop();
            }
        };

        long long ans = 0;
        ans += get_mode();

        for (int i = k; i < nums.size(); i++) {
            int x = nums[i], y = nums[i - k];
            cnt[x]++;
            cnt[y]--;
            pq.push({cnt[x], -x});
            pq.push({cnt[y], -y});
            ans += get_mode();
        }

        return ans;
    }
};
