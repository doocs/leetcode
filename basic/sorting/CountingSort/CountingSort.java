public static void sort(int[] nums, int min, int max) {
    int n = nums.length;
    int k = max - min + 1;
    int[] c = new int[k];
    for (int v : nums) {
        c[v - min]++;
    }
    for (int i = 1; i < k; i++) {
        c[i] += c[i - 1];
    }
    int[] r = new int[n];
    for (int i = n - 1; i >= 0; i--) {
        int v = nums[i];
        int a = c[v];
        r[a - 1] = v + min;
        c[v]--;
    }
    System.arraycopy(r, 0, nums, 0, n);
}