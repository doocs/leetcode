class Solution {
    public char findKthBit(int n, int k) {
        if (k == 1 || n == 1) {
            return '0';
        }
        Set<Integer> set = new HashSet<>();
        int len = calcLength(n, set);
        if (set.contains(k)) {
            return '1';
        }
        // 中间，返回1
        if (k < len / 2) {
            return findKthBit(n - 1, k);
        } else {
            if (set.contains(len - k)) {
                return '1';
            }
            return r(findKthBit(n - 1, len - k + 1));
        }
    }

    private char r(char b) {
        if (b == '0') {
            return '1';
        }
        return '0';
    }

    private int calcLength(int n, Set<Integer> set) {
        if (n == 1) {
            return 1;
        }

        int ans = 2 * calcLength(n - 1, set) + 1;
        set.add(ans + 1);
        return ans;
    }
}