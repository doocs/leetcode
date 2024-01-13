class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int mx = 0;
        for (int candy : candies) {
            mx = Math.max(mx, candy);
        }
        List<Boolean> res = new ArrayList<>();
        for (int candy : candies) {
            res.add(candy + extraCandies >= mx);
        }
        return res;
    }
}