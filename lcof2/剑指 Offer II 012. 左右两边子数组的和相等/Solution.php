class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function pivotIndex($nums) {
        $left = 0;
        $right = array_sum($nums);
        for ($i = 0; $i < count($nums); $i++) {
            $right -= $nums[$i];
            if ($left == $right) {
                return $i;
            }
            $left += $nums[$i];
        }
        return -1;
    }
}
