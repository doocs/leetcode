class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Float
     */
    function findMaxAverage($nums, $k) {
        $sum = 0;
        for ($i = 0; $i < $k; $i++) {
            $sum += $nums[$i];
        }
        $max = $sum;
        for ($j = $k; $j < count($nums); $j++) {
            $sum = $sum - $nums[$j - $k] + $nums[$j];
            $max = max($max, $sum);
        }
        return $max / $k;
    }
}