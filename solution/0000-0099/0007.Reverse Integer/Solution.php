class Solution
{
    /**
     * @param int $x
     * @return int
     */

    function reverse($x)
    {
        $isNegative = $x < 0;
        $x = abs($x);

        $reversed = 0;

        while ($x > 0) {
            $reversed = $reversed * 10 + $x % 10;
            $x = (int) ($x / 10);
        }

        if ($isNegative) {
            $reversed *= -1;
        }
        if ($reversed < -pow(2, 31) || $reversed > pow(2, 31) - 1) {
            return 0;
        }

        return $reversed;
    }
}
