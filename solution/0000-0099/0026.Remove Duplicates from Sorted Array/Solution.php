class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function removeDuplicates(&$nums) {
        $fast = $slow = 0;
        while ($fast < count($nums)) {
            if ($nums[$fast] != $nums[$slow]) {
                $nums[++$slow] = $nums[$fast];
            }
            $fast++;
        }
        return $slow + 1;
    }
}