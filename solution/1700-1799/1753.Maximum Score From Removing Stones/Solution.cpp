class Solution {
public:
    int maximumScore(int a, int b, int c) {
        vector<int> s = {a, b, c};
        sort(s.begin(), s.end());
        int ans = 0;
        while (s[1]) {
            ++ans;
            s[1]--;
            s[2]--;
            sort(s.begin(), s.end());
        }
        return ans;
    }
};