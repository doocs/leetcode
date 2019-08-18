class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] arr : A) {
            reverse(arr, 0, arr.length - 1);
            for (int i = 0; i < arr.length; ++i) {
                arr[i] ^= 1;
            }
        }
        return A;
    }

    private void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
