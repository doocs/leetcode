class Solution {
public:
    vector<int> decode(vector<int>& encoded) {
        int n = encoded.size() + 1;
        vector<int> ans(n);
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i += 2) a ^= encoded[i];
        for (int i = 0; i < n + 1; ++i) b ^= i;
        ans[n - 1] = a ^ b;
        for (int i = n - 2; i >= 0; --i) ans[i] = ans[i + 1] ^ encoded[i];
        return ans;
    }
};