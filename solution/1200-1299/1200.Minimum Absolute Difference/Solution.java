class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int n = arr.length;
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; ++i) {
            int a = arr[i], b = arr[i + 1];
            int d = b - a;
            if (d < mi) {
                ans.clear();
                ans.add(Arrays.asList(a, b));
                mi = d;
            } else if (d == mi) {
                ans.add(Arrays.asList(a, b));
            }
        }
        return ans;
    }
}