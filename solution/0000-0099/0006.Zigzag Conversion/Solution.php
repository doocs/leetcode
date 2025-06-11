class Solution {
    /**
     * @param String $s
     * @param Integer $numRows
     * @return String
     */
    function convert($s, $numRows) {
        if ($numRows == 1) {
            return $s;
        }

        $g = array_fill(0, $numRows, "");
        $i = 0;
        $k = -1;

        $length = strlen($s);
        for ($j = 0; $j < $length; $j++) {
            $c = $s[$j];
            $g[$i] .= $c;

            if ($i == 0 || $i == $numRows - 1) {
                $k = -$k;
            }

            $i += $k;
        }
        return implode("", $g);
    }
}
