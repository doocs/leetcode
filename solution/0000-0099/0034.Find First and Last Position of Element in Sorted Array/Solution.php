class Solution {
    /**
     * @param integer[] $nums
     * @param integer $target
     * @return integer[]
     */

    function searchRange($nums, $target) {
        $min = -1;
        $max = -1;
        foreach ($nums as $key => $value) {
            if ($value == $target) {
                if ($min == -1) {
                    $min = $key;
                }

                if ($key > $max) {
                    $max = $key;
                }
            }
        }
        return [$min, $max];
    }
}
