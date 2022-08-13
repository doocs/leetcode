class Solution {
public:
    int numFriendRequests(vector<int>& ages) {
        vector<int> counter(121);
        for (int age : ages) ++counter[age];
        int ans = 0;
        for (int i = 1; i < 121; ++i) {
            int n1 = counter[i];
            for (int j = 1; j < 121; ++j) {
                int n2 = counter[j];
                if (!(j <= 0.5 * i + 7 || j > i || (j > 100 && i < 100))) {
                    ans += n1 * n2;
                    if (i == j) ans -= n2;
                }
            }
        }
        return ans;
    }
};