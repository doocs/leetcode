class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function removeDuplicates(&$nums) {
        $k = 0;
        foreach ($nums as $x) {
            if ($k == 0 || $x != $nums[$k - 1]) {
                $nums[$k++] = $x;
            }
        }
        return $k;
    }
}