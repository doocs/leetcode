class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function jump($nums) {
        $ans = 0;
        $mx = 0;
        $last = 0;

        for ($i = 0; $i < count($nums) - 1; $i++) {
            $mx = max($mx, $i + $nums[$i]);
            if ($last == $i) {
                $ans++;
                $last = $mx;
            }
        }

        return $ans;
    }
}
