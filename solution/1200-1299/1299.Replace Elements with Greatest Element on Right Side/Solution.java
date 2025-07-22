class Solution {
    public int[] replaceElements(int[] arr) {
        for (int i = arr.length - 1, mx = -1; i >= 0; --i) {
            int x = arr[i];
            arr[i] = mx;
            mx = Math.max(mx, x);
        }
        return arr;
    }
}
