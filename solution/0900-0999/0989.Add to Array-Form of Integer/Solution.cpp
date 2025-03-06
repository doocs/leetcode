class Solution {
public:
    vector<int> addToArrayForm(vector<int>& num, int k) {
        vector<int> ans;
        for (int i = num.size() - 1; i >= 0 || k > 0; --i) {
            k += (i >= 0 ? num[i] : 0);
            ans.push_back(k % 10);
            k /= 10;
        }
        ranges::reverse(ans);
        return ans;
    }
};
