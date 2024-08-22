class Solution {
    public String losingPlayer(int x, int y) {
        int k = Math.min(x / 2, y / 8);
        x -= k * 2;
        y -= k * 8;
        return x > 0 && y >= 4 ? "Alice" : "Bob";
    }
}