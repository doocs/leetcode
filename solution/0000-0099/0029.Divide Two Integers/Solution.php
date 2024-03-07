class Solution {
    /**
     * @param integer $dividend
     * @param integer $divisor
     * @return integer
     */

    function divide($dividend, $divisor) {

        if ($divisor == 0) {
            throw new Exception("Can not divide by 0");
        } else if ($dividend == 0) {
            return 0;
        }
        if ($dividend == -2147483648 && $divisor == -1) {
            return 2147483647;
        }
        $isNegative = ($dividend < 0) != ($divisor < 0);

        $dividend = abs($dividend);
        $divisor = abs($divisor);
        $quotient = 0;
        while ($dividend >= $divisor) {
            $tempDivisor = $divisor;
            $count = 1;
            while ($dividend >= ($tempDivisor << 1)) {
                $tempDivisor <<= 1;
                $count <<= 1;
            }
            $dividend -= $tempDivisor;
            $quotient += $count;
        }

        return $isNegative ? -$quotient : $quotient;
    }
}
