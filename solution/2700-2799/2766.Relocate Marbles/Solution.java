class Solution {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> pos = new HashSet<>();
        for (int x : nums) {
            pos.add(x);
        }
        for (int i = 0; i < moveFrom.length; ++i) {
            pos.remove(moveFrom[i]);
            pos.add(moveTo[i]);
        }
        List<Integer> ans = new ArrayList<>(pos);
        ans.sort((a, b) -> a - b);
        return ans;
    }
}