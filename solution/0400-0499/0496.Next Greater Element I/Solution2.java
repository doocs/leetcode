class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stk = new ArrayDeque<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            while (!stk.isEmpty() && stk.peek() <= nums2[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                m.put(nums2[i], stk.peek());
            }
            stk.push(nums2[i]);
        }
        int n = nums1.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = m.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}