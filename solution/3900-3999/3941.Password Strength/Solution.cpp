class Solution {
public:
    int passwordStrength(string password) {
        unordered_set<char> st(password.begin(), password.end());

        int ans = 0;

        for (char ch : st) {
            if (islower(ch)) {
                ans += 1;
            } else if (isupper(ch)) {
                ans += 2;
            } else if (isdigit(ch)) {
                ans += 3;
            } else {
                ans += 5;
            }
        }

        return ans;
    }
};