class Solution {
    /**
     * @param integer[] $nums
     * @param integer $target
     * @return integer
     */

    function searchInsert($nums, $target) {
        $key = array_search($target, $nums);
        if ($key !== false) {
            return $key;
        }

        $nums[] = $target;
        sort($nums);
        return array_search($target, $nums);
    }
}
