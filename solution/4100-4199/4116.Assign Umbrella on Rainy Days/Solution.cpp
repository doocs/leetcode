class Solution {
public:
    vector<string> assignUmbrella(vector<int>& rain) {
        int n = rain.size();
        int a = 0, b = 0;
        vector<string> ans(n);
        for (int i = 0; i < n; i++) {
            int x = rain[i];
            if (x == 0) {
                ans[i] = "None";
            } else if (a <= b && i < n - 1 && rain[i + 1] == 1) {
                ans[i] = "Alice";
                a++;
            } else {
                ans[i] = "Bob";
                b++;
            }
        }
        return ans;
    }
};