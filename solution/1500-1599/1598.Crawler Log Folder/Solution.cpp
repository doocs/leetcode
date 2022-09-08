class Solution {
public:
    int minOperations(vector<string>& logs) {
        int ans = 0;
        for (auto& v : logs) {
            if (v == "../") {
                ans = max(0, ans - 1);
            } else if (v[0] != '.') {
                ++ans;
            }
        }
        return ans;
    }
};