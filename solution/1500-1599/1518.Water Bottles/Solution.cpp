class Solution {
public:
    int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        for (; numBottles >= numExchange; ++ans) {
            numBottles -= (numExchange - 1);
        }
        return ans;
    }
};