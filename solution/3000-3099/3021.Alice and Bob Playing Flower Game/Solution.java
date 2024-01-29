class Solution {
    public long flowerGame(int n, int m) {
        long count = (n + 1) / 2;
        long tol = (m + 1) / 2;
        long ecount = n / 2;
        long etol = m / 2;
        return (count * etol + ecount * tol);
    }
}
