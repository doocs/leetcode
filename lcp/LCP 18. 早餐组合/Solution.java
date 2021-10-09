class Solution {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int res = 0, n = drinks.length;
        Arrays.sort(drinks);
        for (int s : staple) {
            int remain = x - s;
            if (remain >= drinks[0]) {
                int left = 0, right = n - 1;
                while (left < right) {
                    int mid = (left + right + 1) >>> 1;
                    if (drinks[mid] <= remain) {
                        left = mid;
                    } else {
                        right = mid - 1;
                    }
                }
                res = (res + left + 1) % 1000000007;
            }
        }
        return res;
    }
}