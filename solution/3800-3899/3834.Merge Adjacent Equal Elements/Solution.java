class Solution {
    public List<Long> mergeAdjacent(int[] nums) {
        List<Long> stk = new ArrayList<>();
        for (int x : nums) {
            stk.add((long) x);
            while (stk.size() > 1 && stk.get(stk.size() - 1).equals(stk.get(stk.size() - 2))) {
                long a = stk.remove(stk.size() - 1);
                long b = stk.remove(stk.size() - 1);
                stk.add(a + b);
            }
        }
        return stk;
    }
}
