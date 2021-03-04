class Solution {
    public List<Integer> transformArray(int[] arr) {
        int n = arr.length;
        int[] copy = Arrays.copyOf(arr, n);
        boolean hasChange = true;
        while (hasChange) {
            hasChange = false;
            for (int i = 1; i < n - 1; ++i) {
                if (arr[i] < copy[i - 1] && arr[i] < copy[i + 1]) {
                    ++arr[i];
                    hasChange = true;
                } else if (arr[i] > copy[i - 1] && arr[i] > copy[i + 1]) {
                    --arr[i];
                    hasChange = true;
                }
            }
            System.arraycopy(arr, 0, copy, 0, n);
        }
        List<Integer> res = new ArrayList<>();
        for (int e : arr) {
            res.add(e);
        }
        return res;
    }
}