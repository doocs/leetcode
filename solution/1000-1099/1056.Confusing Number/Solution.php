class Solution {
    /**
     * @param Integer $n
     * @return Boolean
     */
    function confusingNumber($n) {
        $d = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6];
        $x = $n;
        $y = 0;
        while ($x > 0) {
            $v = $x % 10;
            if ($d[$v] < 0) {
                return false;
            }
            $y = $y * 10 + $d[$v];
            $x = intval($x / 10);
        }
        return $y != $n;
    }
}