class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function minSwaps($nums) {
        $n = count($nums);
        $arr = [];
        foreach ($nums as $x) {
            $arr[] = [$this->f($x), $x];
        }
        usort($arr, function ($a, $b) {
            if ($a[0] != $b[0]) {
                return $a[0] <=> $b[0];
            }
            return $a[1] <=> $b[1];
        });
        $d = [];
        for ($i = 0; $i < $n; $i++) {
            $d[$arr[$i][1]] = $i;
        }
        $vis = array_fill(0, $n, false);
        $ans = $n;
        for ($i = 0; $i < $n; $i++) {
            if (!$vis[$i]) {
                $ans--;
                $j = $i;
                while (!$vis[$j]) {
                    $vis[$j] = true;
                    $j = $d[$nums[$j]];
                }
            }
        }
        return $ans;
    }

    private function f($x) {
        $s = 0;
        while ($x != 0) {
            $s += $x % 10;
            $x = intdiv($x, 10);
        }
        return $s;
    }
}
