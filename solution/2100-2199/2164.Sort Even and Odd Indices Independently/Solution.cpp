class Solution {
public:
    vector<int> sortEvenOdd(vector<int>& nums) {
        int n = nums.size();
        vector<int> a;
        vector<int> b;
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0)
                a.push_back(nums[i]);
            else
                b.push_back(nums[i]);
        }
        sort(a.begin(), a.end());
        sort(b.begin(), b.end(), greater<int>());
        vector<int> ans(n);
        for (int i = 0, j = 0; j < a.size(); i += 2, ++j) ans[i] = a[j];
        for (int i = 1, j = 0; j < b.size(); i += 2, ++j) ans[i] = b[j];
        return ans;
    }
};