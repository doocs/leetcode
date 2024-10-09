class Solution {
    /**
     * @param String $s
     * @return String
     */
    function removeStars($s) {
        $ans = [];
        $n = strlen($s);
        for ($i = 0; $i < $n; $i++) {
            $c = $s[$i];
            if ($c === '*') {
                array_pop($ans);
            } else {
                $ans[] = $c;
            }
        }
        return implode('', $ans);
    }
}
