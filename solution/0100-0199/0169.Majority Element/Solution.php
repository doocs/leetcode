class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function majorityElement($nums) {
        sort($nums);
        $n = floor(count($nums) / 2);
        return $nums[$n];
    }
}