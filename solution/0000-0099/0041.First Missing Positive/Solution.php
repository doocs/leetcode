class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function firstMissingPositive($nums) {
        $n = count($nums);
        for ($i = 0; $i < $n; $i++) {
            while ($nums[$i] >= 1 && $nums[$i] <= $n && $nums[$i] != $nums[$nums[$i] - 1]) {
                $j = $nums[$i] - 1;
                $t = $nums[$i];
                $nums[$i] = $nums[$j];
                $nums[$j] = $t;
            }
        }
        for ($i = 0; $i < $n; $i++) {
            if ($nums[$i] != $i + 1) {
                return $i + 1;
            }
        }
        return $n + 1;
    }
}
