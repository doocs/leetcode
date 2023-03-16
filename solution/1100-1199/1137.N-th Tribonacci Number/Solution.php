class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function tribonacci($n) {
        if ($n == 0) {
            return 0;
        } else if ($n == 1 || $n == 2) {
            return 1;
        }
        $dp = [0, 1, 1];
        for ($i = 3; $i <= $n; $i++) {
            $dp[$i] = $dp[$i - 1] + $dp[$i - 2] + $dp[$i - 3];
        }
        return $dp[$n];
    }
}