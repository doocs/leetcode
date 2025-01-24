class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function areOccurrencesEqual($s) {
        $cnt = array_fill(0, 26, 0);
        for ($i = 0; $i < strlen($s); $i++) {
            $cnt[ord($s[$i]) - ord('a')]++;
        }
        $v = 0;
        foreach ($cnt as $x) {
            if ($x == 0) {
                continue;
            }
            if ($v && $v != $x) {
                return false;
            }
            $v = $x;
        }
        return true;
    }
}
