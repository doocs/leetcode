class Solution
{
    /**
     * @param string $s
     * @param int $numRows
     * @return string
     */

    function convert($s, $numRows)
    {
        if ($numRows == 1 || strlen($s) <= $numRows) {
            return $s;
        }

        $result = '';
        $cycleLength = 2 * $numRows - 2;
        $n = strlen($s);

        for ($i = 0; $i < $numRows; $i++) {
            for ($j = 0; $j + $i < $n; $j += $cycleLength) {
                $result .= $s[$j + $i];

                if ($i != 0 && $i != $numRows - 1 && $j + $cycleLength - $i < $n) {
                    $result .= $s[$j + $cycleLength - $i];
                }
            }
        }

        return $result;
    }
}
