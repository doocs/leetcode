class Solution {
public:
    vector<int> decode(vector<int>& encoded) {
        int n = encoded.size() + 1;
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i += 2) {
            a ^= encoded[i];
        }
        for (int i = 1; i <= n; ++i) {
            b ^= i;
        }
        vector<int> perm(n);
        perm[n - 1] = a ^ b;
        for (int i = n - 2; ~i; --i) {
            perm[i] = encoded[i] ^ perm[i + 1];
        }
        return perm;
    }
};