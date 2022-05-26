class Solution {
public:
    string generateTheString(int n) {
        string ans(n, 'a');
        if (n % 2 == 0) ans[0] = 'b';
        return ans;
    }
};