class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function pivotIndex($nums) {
        $sum = 0;
        $pre = 0;
        for ($i = 0; $i < count($nums); $i++) {
            $sum += $nums[$i];
        }
        for ($i = 0; $i < count($nums); $i++) {
            if ($pre === $sum - $pre - $nums[$i]) {
                return $i;
            }
            $pre += $nums[$i];
        }
        return -1;
    }
}