class Solution {
public:
    bool threeConsecutiveOdds(vector<int>& arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v & 1)
                ++cnt;
            else
                cnt = 0;
            if (cnt == 3) return true;
        }
        return false;
    }
};