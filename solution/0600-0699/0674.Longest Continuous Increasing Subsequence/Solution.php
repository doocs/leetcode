class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findLengthOfLCIS($nums) {
        $ans = 1;
        $cnt = 1;
        for ($i = 1; $i < count($nums); ++$i) {
            if ($nums[$i - 1] < $nums[$i]) {
                $ans = max($ans, ++$cnt);
            } else {
                $cnt = 1;
            }
        }
        return $ans;
    }
}
