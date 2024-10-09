class Solution {
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int x = coordinate1.charAt(0) - coordinate2.charAt(0);
        int y = coordinate1.charAt(1) - coordinate2.charAt(1);
        return (x + y) % 2 == 0;
    }
}
