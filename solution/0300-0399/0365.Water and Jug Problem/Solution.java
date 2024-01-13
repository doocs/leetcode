class Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Deque<int[]> stk = new ArrayDeque<>();
        stk.add(new int[] {0, 0});
        Set<Long> seen = new HashSet<>();
        while (!stk.isEmpty()) {
            if (seen.contains(hash(stk.peek()))) {
                stk.pop();
                continue;
            }
            int[] cur = stk.pop();
            seen.add(hash(cur));
            int cur1 = cur[0], cur2 = cur[1];
            if (cur1 == targetCapacity || cur2 == targetCapacity || cur1 + cur2 == targetCapacity) {
                return true;
            }
            stk.offer(new int[] {jug1Capacity, cur2});
            stk.offer(new int[] {0, cur2});
            stk.offer(new int[] {cur1, jug1Capacity});
            stk.offer(new int[] {cur2, 0});
            if (cur1 + cur2 > jug1Capacity) {
                stk.offer(new int[] {jug1Capacity, cur2 - jug1Capacity + cur1});
            } else {
                stk.offer(new int[] {cur1 + cur2, 0});
            }
            if (cur1 + cur2 > jug2Capacity) {
                stk.offer(new int[] {cur1 - jug2Capacity + cur2, jug2Capacity});
            } else {
                stk.offer(new int[] {0, cur1 + cur2});
            }
        }
        return false;
    }

    public long hash(int[] nums) {
        return nums[0] * 10000006L + nums[1];
    }
}