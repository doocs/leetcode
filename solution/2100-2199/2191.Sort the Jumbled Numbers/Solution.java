class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> m = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            int a = v, b = 0, t = 1;
            while (true) {
                int x = a % 10;
                x = mapping[x];
                a /= 10;
                b = x * t + b;
                t *= 10;
                if (a == 0) {
                    break;
                }
            }
            m.add(new int[]{b, i, v});
        }
        m.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return 0;
        });
        for (int i = 0; i < m.size(); ++i) {
            nums[i] = m.get(i)[2];
        }
        return nums;
    }
}