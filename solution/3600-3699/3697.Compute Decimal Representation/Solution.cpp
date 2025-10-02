class Solution {
public:
    vector<int> decimalRepresentation(int n) {
        vector<int> ans;
        long long p = 1;
        while (n > 0) {
            int v = n % 10;
            n /= 10;
            if (v != 0) {
                ans.push_back(p * v);
            }
            p *= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
