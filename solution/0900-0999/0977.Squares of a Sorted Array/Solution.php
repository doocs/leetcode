class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function sortedSquares($nums) {
        $i = 0;
        $j = $k = count($nums) - 1;
        $rs = array_fill(0, count($nums), -1);
        while ($i <= $j) {
            $max1 = $nums[$i] * $nums[$i];
            $max2 = $nums[$j] * $nums[$j];
            if ($max1 > $max2) {
                $rs[$k] = $max1;
                $i++;
            } else {
                $rs[$k] = $max2;
                $j--;
            }
            $k--;
        }
        return $rs;
    }
}