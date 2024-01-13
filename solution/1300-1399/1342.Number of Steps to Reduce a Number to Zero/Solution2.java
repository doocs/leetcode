class Solution {

    public int numberOfSteps(int num) {
        if (num == 0) {
            return 0;
        }
        return 1 + numberOfSteps((num & 1) == 0 ? num >> 1 : num - 1);
    }
}