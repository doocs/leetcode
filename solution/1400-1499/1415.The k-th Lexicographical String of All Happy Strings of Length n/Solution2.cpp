class Solution {
public:
    string getHappyString(int n, int k) {
        if (k > 3 * (1 << (n - 1))) {
            return "";
        }
        string cs = "abc";
        string ans;
        for (int i = 0; i < n; ++i) {
            int remain = 1 << (n - i - 1);
            for (char c : cs) {
                if (!ans.empty() && ans.back() == c) {
                    continue;
                }
                if (k <= remain) {
                    ans.push_back(c);
                    break;
                }
                k -= remain;
            }
        }
        return ans;
    }
};
