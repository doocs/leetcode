class Solution {
public:
    string getSmallestString(int n, int k) {
        string ans(n, 'a');
        int i = n - 1;
        int d = k - n;
        while (d > 25) {
            ans[i--] += 25;
            d -= 25;
        }
        ans[i] += d;
        return ans;
    }
};