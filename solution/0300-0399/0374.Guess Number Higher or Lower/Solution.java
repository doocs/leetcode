public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + r >>> 1;
            if (guess(mid) <= 0) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}
