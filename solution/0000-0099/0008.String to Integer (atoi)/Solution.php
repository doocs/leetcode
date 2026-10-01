class Solution {
    /**
     * @param string $s
     * @return int
     */

    function myAtoi($s) {
        if ($s === null || $s === '') {
            return 0;
        }
        $n = strlen($s);
        $i = 0;
        while ($s[$i] == ' ') {
            $i++;
            if ($i == $n) {
                return 0;
            }
        }
        $sign = 1;
        if ($s[$i] == '-') {
            $sign = -1;
        }
        if ($s[$i] == '-' || $s[$i] == '+') {
            $i++;
        }
        $res = 0;
        $flag = 214748364;
        while ($i < $n) {
            if ($s[$i] < '0' || $s[$i] > '9') {
                break;
            }
            $c = ord($s[$i]) - ord('0');
            if ($res > $flag || ($res == $flag && $c > 7)) {
                return $sign > 0 ? 2147483647 : -2147483648;
            }
            $res = $res * 10 + $c;
            $i++;
        }
        return $sign * $res;
    }
}
