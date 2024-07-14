class Solution {
    /**
     * @param String $s
     * @param Integer $k
     * @return Integer
     */
    function numKLenSubstrNoRepeats($s, $k) {
        $n = strlen($s);
        if ($n < $k) {
            return 0;
        }
        $cnt = [];
        for ($i = 0; $i < $k; ++$i) {
            if (!isset($cnt[$s[$i]])) {
                $cnt[$s[$i]] = 1;
            } else {
                $cnt[$s[$i]]++;
            }
        }
        $ans = count($cnt) == $k ? 1 : 0;
        for ($i = $k; $i < $n; ++$i) {
            if (!isset($cnt[$s[$i]])) {
                $cnt[$s[$i]] = 1;
            } else {
                $cnt[$s[$i]]++;
            }
            if ($cnt[$s[$i - $k]] - 1 == 0) {
                unset($cnt[$s[$i - $k]]);
            } else {
                $cnt[$s[$i - $k]]--;
            }
            $ans += count($cnt) == $k ? 1 : 0;
        }
        return $ans;
    }
}
