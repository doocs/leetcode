class Solution {
public:
    bool canAliceWin(vector<int>& nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x < 10) {
                a += x;
            } else {
                b += x;
            }
        }
        return a != b;
    }
};