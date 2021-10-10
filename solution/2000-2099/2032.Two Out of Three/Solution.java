class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> s1 = get(nums1);
        Set<Integer> s2 = get(nums2);
        Set<Integer> s3 = get(nums3);
        for (int i = 1; i <= 100; ++i) {
            int a = s1.contains(i) ? 1 : 0;
            int b = s2.contains(i) ? 1 : 0;
            int c = s3.contains(i) ? 1 : 0;
            if (a + b + c > 1) {
                ans.add(i);
            }
        }
        return ans;
    }

    private Set<Integer> get(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        return s;
    }
}