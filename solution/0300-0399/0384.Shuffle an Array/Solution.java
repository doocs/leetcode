class Solution {
    private int[] src;
    private int[] arr;
    private Random random;

    public Solution(int[] nums) {
        src = nums;
        arr = Arrays.copyOf(src, src.length);
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return src;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = arr.length - 1; i >= 0; --i) {
            swap(i, random.nextInt(i + 1));
        }
        return arr;
    }

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
