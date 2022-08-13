typedef pair<int, int> PII;

class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        int ans = INT_MAX;
        int n = nums.size();
        for (auto& [a, n1] : get(0, nums))
            for (auto& [b, n2] : get(1, nums))
                if (a != b)
                    ans = min(ans, n - (n1 + n2));
        return ans;
    }

    vector<PII> get(int i, vector<int>& nums) {
        unordered_map<int, int> freq;
        for (; i < nums.size(); i += 2) ++freq[nums[i]];
        int a = 0, n1 = 0, b = 0, n2 = 0;
        for (auto& [k, v] : freq) {
            if (v > n1) {
                b = a;
                n2 = n1;
                a = k;
                n1 = v;
            } else if (v > n2) {
                b = k;
                n2 = v;
            }
        }
        return {{a, n1}, {b, n2}};
    }
};