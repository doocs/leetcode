class BinaryIndexedTree {
    int n;
    int[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int[] countSmallerOppositeParity(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }
        BinaryIndexedTree[] bit = {new BinaryIndexedTree(m), new BinaryIndexedTree(m)};
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            int x = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
            ans[i] = bit[nums[i] & 1 ^ 1].query(x - 1);
            bit[nums[i] & 1].update(x, 1);
        }
        return ans;
    }
}
