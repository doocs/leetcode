class Solution {
    /**
     * @param Integer $n
     * @return Boolean
     */
    function isUgly($n) {
        while ($n) {
            if ($n % 2 == 0) $n = $n / 2;
            else if ($n % 3 == 0) $n = $n / 3;
            else if ($n % 5 == 0) $n = $n / 5;
            else break;
        }
        return $n == 1;
    }
}