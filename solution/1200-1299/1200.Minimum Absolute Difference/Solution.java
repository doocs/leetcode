class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int mi = 1 << 30;
        for (int i = 0; i < n - 1; ++i) {
            mi = Math.min(mi, arr[i + 1] - arr[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            if (arr[i + 1] - arr[i] == mi) {
                ans.add(List.of(arr[i], arr[i + 1]));
            }
        }
        return ans;
    }
}