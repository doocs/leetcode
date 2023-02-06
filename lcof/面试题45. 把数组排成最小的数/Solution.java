class Solution {
    public String minNumber(int[] nums) {
        return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (a + b).compareTo(b + a))
            .reduce((a, b) -> a + b)
            .orElse("");
    }
}