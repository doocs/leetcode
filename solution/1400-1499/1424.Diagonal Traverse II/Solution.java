class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < nums.size(); ++i) {
            for (int j = 0; j < nums.get(i).size(); ++j) {
                arr.add(new int[] {i + j, j, nums.get(i).get(j)});
            }
        }
        arr.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] ans = new int[arr.size()];
        for (int i = 0; i < arr.size(); ++i) {
            ans[i] = arr.get(i)[2];
        }
        return ans;
    }
}