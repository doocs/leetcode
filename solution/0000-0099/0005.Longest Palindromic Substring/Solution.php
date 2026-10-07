class Solution {
    /**
     * @param string $s
     * @return string
     */
    function longestPalindrome($s) {
        $n = strlen($s);
        $f = [];
        for ($i = 0; $i < $n; $i++) {
            $f[$i] = array_fill(0, $n, true);
        }
        $k = 0;
        $mx = 1;
        for ($i = $n - 2; $i >= 0; $i--) {
            for ($j = $i + 1; $j < $n; $j++) {
                $f[$i][$j] = false;
                if ($s[$i] == $s[$j]) {
                    $f[$i][$j] = $f[$i + 1][$j - 1];
                    if ($f[$i][$j] && $mx < $j - $i + 1) {
                        $k = $i;
                        $mx = $j - $i + 1;
                    }
                }
            }
        }
        return substr($s, $k, $mx);
    }
}
