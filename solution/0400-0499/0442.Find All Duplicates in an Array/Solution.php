class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function findDuplicates($nums) {
        $numsNew = array_unique($nums);
        return array_diff_assoc($nums, $numsNew);
    }
}