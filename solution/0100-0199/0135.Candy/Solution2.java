class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int up = 0;
        int down = 0;
        int peak = 0;
        int candies = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i]) {
                up++;
                peak = up + 1;
                down = 0;
                candies += peak;
            } else if (ratings[i] == ratings[i - 1]) {
                peak = 0;
                up = 0;
                down = 0;
                candies++;
            } else {
                down++;
                up = 0;
                candies += down + (peak > down ? 0 : 1);
            }
        }
        return candies;
    }
}