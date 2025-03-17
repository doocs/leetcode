class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function scoreOfString($s) {
        $ans = 0;
        $n = strlen($s);
        for ($i = 1; $i < $n; ++$i) {
            $ans += abs(ord($s[$i]) - ord($s[$i - 1]));
        }
        return $ans;
    }
}
