class Solution {
    /**
     * @param Integer[] $nums
     * @return Boolean
     */
    function containsDuplicate($nums) {
        $numsUnique = array_unique($nums);
        return count($nums) != count($numsUnique);
    }
}