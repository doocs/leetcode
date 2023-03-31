class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function findSpecialInteger($arr) {
        $len = count($arr);
        for ($i = 0; $i < $len; $i++) {
            if ($arr[$i] == $arr[$i + ($len >> 2)]) return $arr[$i];
        }
        return -1;
    }
}