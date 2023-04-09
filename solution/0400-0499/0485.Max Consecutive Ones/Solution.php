class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findMaxConsecutiveOnes($nums) {
        $tmp = $max = 0;
        for ($i = 0; $i < count($nums); $i++) {
            if ($nums[$i] == 1) $tmp++;
            else {
                $max = max($tmp, $max);
                $tmp = 0;
            }
        }
        return max($tmp, $max);
    }
}