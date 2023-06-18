class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findLengthOfLCIS($nums) {
        $tmp = $max = 1;
        for ($i = 0; $i < count($nums) - 1; $i++) {
            if ($nums[$i] < $nums[$i + 1]) {
                $tmp++;
                $max = max($max, $tmp);
            } else {
                $tmp = 1;
            }
        }
        return $max;
    }
}