class Solution {

    /**
     * @param Integer[] $nums
     * @param Integer $val
     * @return Integer
     */
    function removeElement(&$nums, $val) {
        for ($i = count($nums) - 1; $i >= 0; $i--) {
            if ($nums[$i] == $val) {
                array_splice($nums, $i, 1);
            }
        }
    }
}
