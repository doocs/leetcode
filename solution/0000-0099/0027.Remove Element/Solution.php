class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $val
     * @return Integer
     */
    function removeElement(&$nums, $val) {
        $k = 0;
        foreach ($nums as $x) {
            if ($x != $val) {
                $nums[$k++] = $x;
            }
        }
        return $k;
    }
}
