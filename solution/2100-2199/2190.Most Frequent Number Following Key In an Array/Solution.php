class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $key
     * @return Integer
     */
    function mostFrequent($nums, $key) {
        $max = $maxNum = 0;
        for ($i = 0; $i < count($nums) - 1; $i++) {
            if ($nums[$i] == $key) {
                $hashtable[$nums[$i + 1]] += 1;
                $tmp = $hashtable[$nums[$i + 1]];
                if ($tmp > $max) {
                    $max = $tmp;
                    $maxNum = $nums[$i + 1];
                }
            }
        }
        return $maxNum;
    }
}