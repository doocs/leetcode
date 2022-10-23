class Solution {
public:
    long long makeSimilar(vector<int>& nums, vector<int>& target) {
        sort(nums.begin(), nums.end());
        sort(target.begin(), target.end());
        vector<int> a1;
        vector<int> a2;
        vector<int> b1;
        vector<int> b2;
        for (int v : nums) {
            if (v & 1)
                a1.emplace_back(v);
            else
                a2.emplace_back(v);
        }
        for (int v : target) {
            if (v & 1)
                b1.emplace_back(v);
            else
                b2.emplace_back(v);
        }
        long long ans = 0;
        for (int i = 0; i < a1.size(); ++i) ans += abs(a1[i] - b1[i]);
        for (int i = 0; i < a2.size(); ++i) ans += abs(a2[i] - b2[i]);
        return ans / 4;
    }
};