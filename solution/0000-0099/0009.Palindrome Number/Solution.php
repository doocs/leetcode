class Solution {
    /**
     * @param int $x
     * @return boolean
     */

    function isPalindrome($x) {
        $str = (string) $x;
        $str_reverse = strrev($str);
        return $str === $str_reverse;
    }
}
