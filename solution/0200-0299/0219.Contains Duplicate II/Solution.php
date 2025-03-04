class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Boolean
     */
    function containsNearbyDuplicate($nums, $k) {
        $d = [];
        for ($i = 0; $i < count($nums); ++$i) {
            if (array_key_exists($nums[$i], $d) && $i - $d[$nums[$i]] <= $k) {
                return true;
            }
            $d[$nums[$i]] = $i;
        }
        return false;
    }
}