class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function productExceptSelf($nums) {
        $n = count($nums);
        $ans = [];
        for ($i = 0, $left = 1; $i < $n; ++$i) {
            $ans[$i] = $left;
            $left *= $nums[$i];
        }
        for ($i = $n - 1, $right = 1; $i >= 0; --$i) {
            $ans[$i] *= $right;
            $right *= $nums[$i];
        }
        return $ans;
    }
}