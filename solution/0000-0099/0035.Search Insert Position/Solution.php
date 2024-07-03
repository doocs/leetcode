class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer
     */
    function searchInsert($nums, $target) {
        $l = 0;
        $r = count($nums);
        while ($l < $r) {
            $mid = $l + $r >> 1;
            if ($nums[$mid] >= $target) {
                $r = $mid;
            } else {
                $l = $mid + 1;
            }
        }
        return $l;
    }
}
