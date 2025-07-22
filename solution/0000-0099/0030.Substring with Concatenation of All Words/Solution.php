class Solution {

    /**
     * @param String $s
     * @param String[] $words
     * @return Integer[]
     */
    function findSubstring($s, $words) {
        $cnt = [];
        foreach ($words as $w) {
            if (isset($cnt[$w])) {
                $cnt[$w]++;
            } else {
                $cnt[$w] = 1;
            }
        }

        $ans = [];
        $m = strlen($s);
        $n = count($words);
        $k = strlen($words[0]);

        for ($i = 0; $i < $k; $i++) {
            $l = $i;
            $r = $i;
            $cnt1 = [];
            while ($r + $k <= $m) {
                $t = substr($s, $r, $k);
                $r += $k;

                if (!isset($cnt[$t])) {
                    $cnt1 = [];
                    $l = $r;
                    continue;
                }

                if (isset($cnt1[$t])) {
                    $cnt1[$t]++;
                } else {
                    $cnt1[$t] = 1;
                }

                while ($cnt1[$t] > $cnt[$t]) {
                    $w = substr($s, $l, $k);
                    $cnt1[$w]--;
                    if ($cnt1[$w] == 0) {
                        unset($cnt1[$w]);
                    }
                    $l += $k;
                }

                if ($r - $l == $n * $k) {
                    $ans[] = $l;
                }
            }
        }

        return $ans;
    }
}
