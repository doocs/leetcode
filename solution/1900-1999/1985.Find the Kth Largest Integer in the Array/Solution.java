class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(
            nums, (a, b) -> a.length() == b.length() ? b.compareTo(a) : b.length() - a.length());
        return nums[k - 1];
    }
}