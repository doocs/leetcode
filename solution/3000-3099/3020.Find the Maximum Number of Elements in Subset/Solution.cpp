class Solution {
public:
    int maximumLength(vector<int>& nums) {
        long long ans = 0;
        map<int, int> freq;
        for (auto n : nums) {
            freq[n]++;
        }
        for (auto [k, f] : freq) {
            long long t = k, count = 0;
            if (t == 1) {
                count += freq[t];
                freq[t] = 0;
            }
            while (t < INT_MAX && freq[t] > 0) {
                count += 2;
                if (freq[t] == 1) {
                    break;
                }
                freq[t] = 0;
                t = t * t;
            }
            if (count % 2 == 0) {
                count--;
            }
            ans = max(ans, count);
        }
        return ans;
    }
};
