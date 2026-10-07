class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function searchRange($nums, $target) {
        $search = function ($x) use ($nums) {
            $left = 0;
            $right = count($nums);
            while ($left < $right) {
                $mid = intdiv($left + $right, 2);
                if ($nums[$mid] >= $x) {
                    $right = $mid;
                } else {
                    $left = $mid + 1;
                }
            }
            return $left;
        };

        $l = $search($target);
        $r = $search($target + 1);
        return $l === $r ? [-1, -1] : [$l, $r - 1];
    }
}
