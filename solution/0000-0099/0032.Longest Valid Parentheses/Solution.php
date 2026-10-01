class Solution {
    /**
     * @param string $s
     * @return integer
     */
    function longestValidParentheses($s) {
        $n = strlen($s);
        $f = array_fill(0, $n + 1, 0);
        for ($i = 1; $i <= $n; $i++) {
            if ($s[$i - 1] == ')') {
                if ($i > 1 && $s[$i - 2] == '(') {
                    $f[$i] = $f[$i - 2] + 2;
                } else {
                    $j = $i - $f[$i - 1] - 1;
                    if ($j && $s[$j - 1] == '(') {
                        $f[$i] = $f[$i - 1] + 2 + $f[$j - 1];
                    }
                }
            }
        }
        return max($f);
    }
}
