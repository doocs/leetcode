class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, target, 0, new Stack<>(), res);
        return res;
    }

    private void combinationSum(int[] candidates, int target, int index, Stack<Integer> stack, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            stack.push(candidates[i]);
            combinationSum(candidates, target - candidates[i], i, stack, res);
            stack.pop();
        }
    }
}