class Solution {
    /**
     * @param String $secret
     * @param String $guess
     * @return String
     */
    function getHint($secret, $guess) {
        $cnt1 = array_fill(0, 10, 0);
        $cnt2 = array_fill(0, 10, 0);
        $x = 0;
        for ($i = 0; $i < strlen($secret); ++$i) {
            if ($secret[$i] === $guess[$i]) {
                ++$x;
            } else {
                ++$cnt1[(int) $secret[$i]];
                ++$cnt2[(int) $guess[$i]];
            }
        }
        $y = 0;
        for ($i = 0; $i < 10; ++$i) {
            $y += min($cnt1[$i], $cnt2[$i]);
        }
        return "{$x}A{$y}B";
    }
}