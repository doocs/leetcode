class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function romanToInt($s) {
        $d = [
            'I' => 1,
            'V' => 5,
            'X' => 10,
            'L' => 50,
            'C' => 100,
            'D' => 500,
            'M' => 1000,
        ];
        $ans = 0;
        $len = strlen($s);

        for ($i = 0; $i < $len - 1; $i++) {
            if ($d[$s[$i]] < $d[$s[$i + 1]]) {
                $ans -= $d[$s[$i]];
            } else {
                $ans += $d[$s[$i]];
            }
        }

        $ans += $d[$s[$len - 1]];
        return $ans;
    }
}