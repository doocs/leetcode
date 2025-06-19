class Solution {
    /**
     * @param String $s
     * @param String $p
     * @return Boolean
     */
    function isMatch($s, $p) {
        $m = strlen($s);
        $n = strlen($p);

        $f = array_fill(0, $m + 1, array_fill(0, $n + 1, false));
        $f[0][0] = true;

        for ($i = 0; $i <= $m; $i++) {
            for ($j = 1; $j <= $n; $j++) {
                if ($p[$j - 1] == '*') {
                    $f[$i][$j] = $f[$i][$j - 2];
                    if ($i > 0 && ($p[$j - 2] == '.' || $p[$j - 2] == $s[$i - 1])) {
                        $f[$i][$j] = $f[$i][$j] || $f[$i - 1][$j];
                    }
                } elseif ($i > 0 && ($p[$j - 1] == '.' || $p[$j - 1] == $s[$i - 1])) {
                    $f[$i][$j] = $f[$i - 1][$j - 1];
                }
            }
        }

        return $f[$m][$n];
    }
}