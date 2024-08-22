class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function twoSum($nums, $target) {
        $d = [];
        foreach ($nums as $i => $x) {
            $y = $target - $x;
            if (isset($d[$y])) {
                return [$d[$y], $i];
            }
            $d[$x] = $i;
        }
    }
}
