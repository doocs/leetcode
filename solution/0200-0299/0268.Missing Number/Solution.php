class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function missingNumber($nums) {
        $n = count($nums);
        $sumN = ($n + 1) * $n / 2;
        for ($i = 0; $i < $n; $i++) {
            $sumN -= $nums[$i];
        }
        return $sumN;
    }
}