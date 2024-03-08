<?php
class Solution {
    /**
     * @param integer $a
     * @param integer $b
     * @return integer
     */

    function divide($a, $b) {

        if ($b == 0) {
            throw new Exception("Can not divide by 0");
        } else if ($a == 0) {
            return 0;
        }
        if ($a == -2147483648 && $b == -1) {
            return 2147483647;
        }
        $sign = ($a < 0) != ($b < 0);

        $a = abs($a);
        $b = abs($b);
        $ans = 0;
        while ($a >= $b) {
            $x = $b;
            $cnt = 1;
            while ($a >= ($x << 1)) {
                $x <<= 1;
                $cnt <<= 1;
            }
            $a -= $x;
            $ans += $cnt;
        }

        return $sign ? -$ans : $ans;
    }
}
