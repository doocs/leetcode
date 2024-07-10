class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findMaxConsecutiveOnes($nums) {
        $ans = $cnt = 0;

        foreach ($nums as $x) {
            if ($x == 1) {
                $cnt += 1;
                $ans = max($ans, $cnt);
            } else {
                $cnt = 0;
            }
        }

        return $ans;
    }
}