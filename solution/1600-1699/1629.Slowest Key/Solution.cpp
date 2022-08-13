class Solution {
public:
    char slowestKey(vector<int>& releaseTimes, string keysPressed) {
        char ans = keysPressed[0];
        int mx = releaseTimes[0];
        for (int i = 1, n = releaseTimes.size(); i < n; ++i) {
            int d = releaseTimes[i] - releaseTimes[i - 1];
            if (d > mx || (d == mx && keysPressed[i] > ans)) {
                mx = d;
                ans = keysPressed[i];
            }
        }
        return ans;
    }
};