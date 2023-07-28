class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Integer
     */
    function minimumDifference($nums, $k) {
        sort($nums);
        $rs = 10 ** 5;
        for ($i = 0; $i < count($nums) - $k + 1; $i++) {
            $rs = min($rs, $nums[$i + $k - 1] - $nums[$i]);
        }
        return $rs;
    }
}