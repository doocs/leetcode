class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function twoSum($nums, $target) {
        $m = [];
        foreach ($nums as $i => $x) {
            $y = $target - $x;
            if (isset($m[$y])) {
                return [$m[$y], $i];
            }
            $m[$x] = $i;
        }
    }
}