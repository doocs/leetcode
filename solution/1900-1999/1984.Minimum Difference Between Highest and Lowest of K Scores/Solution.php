class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Integer
     */
    function minimumDifference($nums, $k) {
        sort($nums);
        $ans = 10 ** 5;
        for ($i = 0; $i < count($nums) - $k + 1; $i++) {
            $ans = min($ans, $nums[$i + $k - 1] - $nums[$i]);
        }
        return $ans;
    }
}