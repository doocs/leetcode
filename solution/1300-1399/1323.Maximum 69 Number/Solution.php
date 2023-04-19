class Solution {
    /**
     * @param Integer $num
     * @return Integer
     */
    function maximum69Number($num) {
        $num = strval($num);
        $n = strpos($num, "6");
        $num[$n] = 9;
        return intval($num);
    }
}