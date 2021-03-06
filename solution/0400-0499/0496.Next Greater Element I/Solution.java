class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        int n = nums1.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}