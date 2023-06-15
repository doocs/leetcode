class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function countElements($arr) {
        $cnt = 0;
        for ($i = 0; $i < count($arr); $i++) {
            if (in_array($arr[$i] + 1, $arr)) {
                $cnt++;
            }
        }
        return $cnt++;
    }
}