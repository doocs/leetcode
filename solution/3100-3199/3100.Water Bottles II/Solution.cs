public class Solution {
    public int MaxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            ++numExchange;
            ++ans;
            ++numBottles;
        }
        return ans;
    }
}
