class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum(candidates, target, candidates.length - 1, new ArrayList<>());
        return result;
    }

    private void combinationSum(int[] candidates, int target, int length, List<Integer> integers) {
        List<Integer> list;
        for (int i = length; i >= 0; i--) {
            int nc = candidates[i];
            if (nc > target || i < length && nc == candidates[i + 1]) {
                continue;
            }
            list = new ArrayList<>(integers);
            list.add(nc);
            if (nc == target) {
                result.add(list);
            } else {
                combinationSum(candidates, target - nc, i - 1, list);
            }
        }
    }
}
