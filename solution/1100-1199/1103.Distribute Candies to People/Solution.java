class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        for (int i = 0, cur = 1; candies > 0; ++i, ++cur) {
            if (i == num_people) {
                i = 0;
            }
            if (candies >= cur) {
                res[i] += cur;
                candies -= cur;
            } else {
                res[i] += candies;
                candies = 0;
            }
        }
        return res;
    }
}
