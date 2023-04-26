class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function maxSubArray($nums) {
        $pre = 0;
        $max = $nums[0];
        for ($i = 0; $i < count($nums); $i++) {
            $pre = max($pre + $nums[$i], $nums[$i]);
            $max = max($pre, $max);
        }
        return $max;
    }
}