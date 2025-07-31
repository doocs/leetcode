class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function longestSubarray($nums) {
        $mx = max($nums);
        $ans = 0;
        $cnt = 0;

        foreach ($nums as $x) {
            if ($x == $mx) {
                $ans = max($ans, ++$cnt);
            } else {
                $cnt = 0;
            }
        }

        return $ans;
    }
}