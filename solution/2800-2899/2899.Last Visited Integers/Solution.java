class Solution {
    public List<Integer> lastVisitedIntegers(List<String> words) {
        List<Integer> nums = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        for (var w : words) {
            if ("prev".equals(w)) {
                ++k;
                int i = nums.size() - k;
                ans.add(i < 0 ? -1 : nums.get(i));
            } else {
                k = 0;
                nums.add(Integer.valueOf(w));
            }
        }
        return ans;
    }
}