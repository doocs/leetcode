class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i > 0; --i) {
            int j = i;
            for (; j > 0 && arr[j] != i + 1; --j);
            if (j < i) {
                if (j > 0) {
                    ans.add(j + 1);
                    reverse(arr, j);
                }
                ans.add(i + 1);
                reverse(arr, i);
            }
        }
        return ans;
    }

    private void reverse(int[] arr, int j) {
        for (int i = 0; i < j; ++i, --j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}