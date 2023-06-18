class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function mostFrequentEven($nums) {
        $max = $rs = -1;
        for ($i = 0; $i < count($nums); $i++) {
            if ($nums[$i] % 2 == 0) {
                $hashtable[$nums[$i]] += 1;
                if (
                    $hashtable[$nums[$i]] > $max ||
                    ($hashtable[$nums[$i]] == $max && $rs > $nums[$i])
                ) {
                    $max = $hashtable[$nums[$i]];
                    $rs = $nums[$i];
                }
            }
        }
        return $rs;
    }
}