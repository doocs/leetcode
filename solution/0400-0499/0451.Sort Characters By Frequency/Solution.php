class Solution {
    /**
     * @param String $s
     * @return String
     */
    function frequencySort($s) {
        $cnt = array_count_values(str_split($s));
        $cs = array_keys($cnt);
        usort($cs, function ($a, $b) use ($cnt) {
            return $cnt[$b] <=> $cnt[$a];
        });
        $ans = '';
        foreach ($cs as $c) {
            $ans .= str_repeat($c, $cnt[$c]);
        }
        return $ans;
    }
}
