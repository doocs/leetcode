class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[][]
     */
    function threeSum($nums) {
        sort($nums);
        $ans = [];
        $n = count($nums);
        for ($i = 0; $i < $n - 2 && $nums[$i] <= 0; ++$i) {
            if ($i > 0 && $nums[$i] == $nums[$i - 1]) {
                continue;
            }
            $j = $i + 1;
            $k = $n - 1;
            while ($j < $k) {
                $x = $nums[$i] + $nums[$j] + $nums[$k];
                if ($x < 0) {
                    ++$j;
                } elseif ($x > 0) {
                    --$k;
                } else {
                    $ans[] = [$nums[$i], $nums[$j++], $nums[$k--]];
                    while ($j < $k && $nums[$j] == $nums[$j - 1]) {
                        ++$j;
                    }
                    while ($j < $k && $nums[$k] == $nums[$k + 1]) {
                        --$k;
                    }
                }
            }
        }
        return $ans;
    }
}