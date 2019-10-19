class Solution {
    public boolean canWinNim(int n) {
        return (n & 3) != 0;// n%4 != 0
    }
}