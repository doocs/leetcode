class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function lengthOfLongestSubstring($s) {
        $ans = 0;
        $ss = [];
        for ($i = 0, $j = 0; $j < strlen($s); ++$j) {
            while (in_array($s[$j], $ss)) {
                unset($ss[array_search($s[$i++], $ss)]);
            }
            $ss[] = $s[$j];
            $ans = max($ans, $j - $i + 1);
        }
        return $ans;
    }
}