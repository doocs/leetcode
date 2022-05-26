class Solution {
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> s = count(nums);
        int n = nums.length;
        for (int i = 0; i < n + 1; ++i) {
            if (!s.contains(i)) {
                return "1".repeat(i) + "0".repeat(n - i);
            }
        }
        return "";
    }

    private Set<Integer> count(String[] nums) {
        Set<Integer> s = new HashSet<>();
        for (String num : nums) {
            int t = 0;
            for (char c : num.toCharArray()) {
                if (c == '1') {
                    ++t;
                }
            }
            s.add(t);
        }
        return s;
    }
}