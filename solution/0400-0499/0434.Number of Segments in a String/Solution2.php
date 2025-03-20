class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countSegments($s) {
        $ans = 0;
        $n = strlen($s);
        for ($i = 0; $i < $n; $i++) {
            $c = $s[$i];
            if ($c !== ' ' && ($i === 0 || $s[$i - 1] === ' ')) {
                $ans++;
            }
        }
        return $ans;
    }
}