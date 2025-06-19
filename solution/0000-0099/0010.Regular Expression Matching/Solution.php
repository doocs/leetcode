class Solution {
    /**
     * @param String $s
     * @param String $p
     * @return Boolean
     */
    function isMatch($s, $p) {
        $m = strlen($s);
        $n = strlen($p);
        $f = array_fill(0, $m + 1, array_fill(0, $n + 1, 0));

        $dfs = function ($i, $j) use (&$s, &$p, $m, $n, &$f, &$dfs) {
            if ($j >= $n) {
                return $i == $m;
            }
            if ($f[$i][$j] != 0) {
                return $f[$i][$j] == 1;
            }
            $res = -1;
            if ($j + 1 < $n && $p[$j + 1] == '*') {
                if (
                    $dfs($i, $j + 2) ||
                    ($i < $m && ($s[$i] == $p[$j] || $p[$j] == '.') && $dfs($i + 1, $j))
                ) {
                    $res = 1;
                }
            } elseif ($i < $m && ($s[$i] == $p[$j] || $p[$j] == '.') && $dfs($i + 1, $j + 1)) {
                $res = 1;
            }
            $f[$i][$j] = $res;
            return $res == 1;
        };

        return $dfs(0, 0);
    }
}