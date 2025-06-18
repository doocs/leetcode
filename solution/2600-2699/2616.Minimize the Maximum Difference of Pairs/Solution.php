class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $p
     * @return Integer
     */
    function minimizeMax($nums, $p) {
        sort($nums);
        $n = count($nums);
        $l = 0;
        $r = $nums[$n - 1] - $nums[0] + 1;

        $check = function ($diff) use ($nums, $n, $p) {
            $cnt = 0;
            for ($i = 0; $i < $n - 1; ++$i) {
                if ($nums[$i + 1] - $nums[$i] <= $diff) {
                    ++$cnt;
                    ++$i;
                }
            }
            return $cnt >= $p;
        };

        while ($l < $r) {
            $mid = intdiv($l + $r, 2);
            if ($check($mid)) {
                $r = $mid;
            } else {
                $l = $mid + 1;
            }
        }

        return $l;
    }
}