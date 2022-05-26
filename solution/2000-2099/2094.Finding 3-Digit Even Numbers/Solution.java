class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] counter = count(digits);
        List<Integer> ans = new ArrayList<>();
        for (int i = 100; i < 1000; i += 2) {
            int[] t = new int[3];
            for (int j = 0, k = i; k > 0; ++j) {
                t[j] = k % 10;
                k /= 10;
            }
            int[] cnt = count(t);
            if (check(counter, cnt)) {
                ans.add(i);
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    private boolean check(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < 10; ++i) {
            if (cnt1[i] < cnt2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] count(int[] nums) {
        int[] counter = new int[10];
        for (int num : nums) {
            ++counter[num];
        }
        return counter;
    }
}