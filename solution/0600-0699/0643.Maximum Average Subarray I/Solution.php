class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Float
     */
    function findMaxAverage($nums, $k) {
        $s = 0;
        for ($i = 0; $i < $k; $i++) {
            $s += $nums[$i];
        }
        $ans = $s;
        for ($j = $k; $j < count($nums); $j++) {
            $s = $s - $nums[$j - $k] + $nums[$j];
            $ans = max($ans, $s);
        }
        return $ans / $k;
    }
}
