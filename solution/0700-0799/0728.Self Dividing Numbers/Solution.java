class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int x = left; x <= right; ++x) {
            if (check(x)) {
                ans.add(x);
            }
        }
        return ans;
    }

    private boolean check(int x) {
        for (int y = x; y > 0; y /= 10) {
            if (y % 10 == 0 || x % (y % 10) != 0) {
                return false;
            }
        }
        return true;
    }
}
