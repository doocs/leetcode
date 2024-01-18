class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findLengthOfLCIS($nums) {
        $ans = 1;
        $n = count($nums);
        $i = 0;
        while ($i < $n) {
            $j = $i + 1;
            while ($j < $n && $nums[$j - 1] < $nums[$j]) {
                $j++;
            }
            $ans = max($ans, $j - $i);
            $i = $j;
        }
        return $ans;
    }
}
