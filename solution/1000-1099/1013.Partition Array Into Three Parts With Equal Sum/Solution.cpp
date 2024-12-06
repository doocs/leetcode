class Solution {
public:
    bool canThreePartsEqualSum(vector<int>& arr) {
        int s = accumulate(arr.begin(), arr.end(), 0);
        if (s % 3) {
            return false;
        }
        s /= 3;
        int cnt = 0, t = 0;
        for (int x : arr) {
            t += x;
            if (t == s) {
                t = 0;
                cnt++;
            }
        }
        return cnt >= 3;
    }
};
