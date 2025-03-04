class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function findSpecialInteger($arr) {
        $n = count($arr);
        for ($i = 0; ; ++$i) {
            if ($arr[$i] == $arr[$i + ($n >> 2)]) {
                return $arr[$i];
            }
        }
    }
}
