class Solution {
    /**
     * @param String $s
     * @param String[] $words
     * @return Integer[]
     */
    function findSubstring($s, $words) {
        $cnt = [];
        foreach ($words as $w) {
            if (!isset($cnt[$w])) {
                $cnt[$w] = 1;
            } else {
                $cnt[$w]++;
            }
        }
        $m = strlen($s);
        $n = count($words);
        $k = strlen($words[0]);
        $ans = [];
        for ($i = 0; $i < $k; ++$i) {
            $cnt1 = [];
            $l = $i;
            $r = $i;
            $t = 0;
            while ($r + $k <= $m) {
                $w = substr($s, $r, $k);
                $r += $k;
                if (!array_key_exists($w, $cnt)) {
                    $cnt1 = [];
                    $l = $r;
                    $t = 0;
                    continue;
                }
                if (!isset($cnt1[$w])) {
                    $cnt1[$w] = 1;
                } else {
                    $cnt1[$w]++;
                }
                ++$t;
                while ($cnt1[$w] > $cnt[$w]) {
                    $remove = substr($s, $l, $k);
                    $l += $k;
                    $cnt1[$remove]--;
                    $t--;
                }
                if ($t == $n) {
                    $ans[] = $l;
                }
            }
        }
        return $ans;
    }
}
