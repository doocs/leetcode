class Solution {

    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function majorityElement($nums) {
        $m = 0;
        $cnt = 0;
        foreach ($nums as $x) {
            if ($cnt == 0) {
                $m = $x;
            }
            if ($m == $x) {
                $cnt++;
            } else {
                $cnt--;
            }
        }
        return $m;
    }
}