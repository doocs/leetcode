class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function romanToInt($s) {
        $hashmap = array('I' => 1, 'V' => 5, 'X' => 10, 'L' => 50, 'C' => 100, 'D' => 500, 'M' => 1000);
        $rs = 0;
        for ($i = 0; $i < strlen($s); $i++) {
            $left = $hashmap[$s[$i]];
            $right = $hashmap[$s[$i + 1]];
            if ($left >= $right) $rs += $left;
            else $rs -= $left;
        }
        return $rs;
    }
}