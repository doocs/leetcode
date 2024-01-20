class Solution {
    /**
     * @param string $s
     * @return int
     */

    function myAtoi($s) {
        $s = str_replace('e', 'x', $s);
        if (intval($s) < pow(-2, 31)) {
            return -2147483648;
        }
        if (intval($s) > pow(2, 31) - 1) {
            return 2147483647;
        }
        return intval($s);
    }
}
