class Solution {
public:
    int minTimeToType(string word) {
        int ans = 0;
        int prev = 0;
        for (char& c : word) {
            int curr = c - 'a';
            int t = abs(prev - curr);
            t = min(t, 26 - t);
            ans += t + 1;
            prev = curr;
        }
        return ans;
    }
};