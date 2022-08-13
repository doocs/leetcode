class Solution {
public:
    vector<int> addToArrayForm(vector<int>& num, int k) {
        int i = num.size() - 1, carry = 0;
        vector<int> ans;
        for (; i >= 0 || k || carry; --i) {
            carry += (i < 0 ? 0 : num[i]) + k % 10;
            ans.push_back(carry % 10);
            carry /= 10;
            k /= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};