class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function numIdenticalPairs($nums) {
        $ans = 0;
        $cnt = array_fill(0, 101, 0);
        foreach ($nums as $x) {
            $ans += $cnt[$x]++;
        }
        return $ans;
    }
}
