class Solution {
    public int[] sortByAbsoluteValue(int[] nums) {
        return Arrays.stream(nums)
            .boxed()
            .sorted(Comparator.comparingInt(Math::abs))
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
