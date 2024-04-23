class Solution {
    /**
     * @param string $s
     * @param string $p
     * @return boolean
     */

    function isMatch($s, $p) {
        $lengthS = strlen($s);
        $lengthP = strlen($p);
        $dp = array();
        for ($i = 0; $i <= $lengthS; $i++) {
            $dp[$i] = array_fill(0, $lengthP + 1, false);
        }
        $dp[0][0] = true;

        for ($i = 1; $i <= $lengthP; $i++) {
            if ($p[$i - 1] == '*') {
                $dp[0][$i] = $dp[0][$i - 1];
            }
        }
        for ($i = 1; $i <= $lengthS; $i++) {
            for ($j = 1; $j <= $lengthP; $j++) {
                if ($p[$j - 1] == '?' || $s[$i - 1] == $p[$j - 1]) {
                    $dp[$i][$j] = $dp[$i - 1][$j - 1];
                } else if ($p[$j - 1] == '*') {
                    $dp[$i][$j] = $dp[$i][$j - 1] || $dp[$i - 1][$j];
                }
            }
        }
        return $dp[$lengthS][$lengthP];
    }
}
