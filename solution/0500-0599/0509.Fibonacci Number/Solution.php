class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function fib($n) {
        if ($n == 0 || $n == 1) {
            return $n;
        }
        $dp = [0, 1];
        for ($i = 2; $i <= $n; $i++) {
            $dp[$i] = $dp[$i - 2] + $dp[$i - 1];
        }
        return $dp[$n];
    }
}