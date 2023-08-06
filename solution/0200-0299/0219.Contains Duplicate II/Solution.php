class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Boolean
     */
    function containsNearbyDuplicate($nums, $k) {
        $hashtable = [];
        for ($i = 0; $i < count($nums); $i++) {
            $tmp = $nums[$i];
            if (
                array_key_exists($tmp, $hashtable) &&
                $k >= $i - $hashtable[$tmp]
            ) {
                return true;
            }
            $hashtable[$tmp] = $i;
        }
        return false;
    }
}