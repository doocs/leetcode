class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer
     */
    function search($nums, $target) {
        $n = count($nums);
        $left = 0;
        $right = $n - 1;
        while ($left < $right) {
            $mid = ($left + $right) >> 1;
            if ($nums[0] <= $nums[$mid]) {
                if ($nums[0] <= $target && $target <= $nums[$mid]) {
                    $right = $mid;
                } else {
                    $left = $mid + 1;
                }
            } else {
                if ($nums[$mid] < $target && $target <= $nums[$n - 1]) {
                    $left = $mid + 1;
                } else {
                    $right = $mid;
                }
            }
        }
        return $nums[$left] == $target ? $left : -1;
    }
}
