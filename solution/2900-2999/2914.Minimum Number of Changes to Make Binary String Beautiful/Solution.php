class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function minChanges($s) {
        $ans = 0;
        $n = strlen($s);
        for ($i = 1; $i < $n; $i += 2) {
            if ($s[$i] != $s[$i - 1]) {
                ++$ans;
            }
        }
        return $ans;
    }
}
