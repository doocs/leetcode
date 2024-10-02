class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countGoodSubstrings($s) {
        $ans = 0;
        $n = strlen($s);
        $l = 0;
        $r = 0;
        $mask = 0;

        while ($r < $n) {
            $x = ord($s[$r]) - ord('a');
            while (($mask >> $x) & 1) {
                $y = ord($s[$l++]) - ord('a');
                $mask ^= 1 << $y;
            }
            $mask |= 1 << $x;
            if ($r - $l + 1 >= 3) {
                $ans++;
            }
            $r++;
        }

        return $ans;
    }
}
