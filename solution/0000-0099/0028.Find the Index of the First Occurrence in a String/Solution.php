class Solution {
    /**
     * @param String $haystack
     * @param String $needle
     * @return Integer
     */
    function strStr($haystack, $needle) {
        $n = strlen($haystack);
        $m = strlen($needle);
        for ($i = 0; $i + $m <= $n; $i++) {
            if (substr($haystack, $i, $m) === $needle) {
                return $i;
            }
        }
        return -1;
    }
}
