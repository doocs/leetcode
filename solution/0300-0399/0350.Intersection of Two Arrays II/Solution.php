class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersect($nums1, $nums2) {
        $rs = [];
        for ($i = 0; $i < count($nums1); $i++) {
            $hashtable[$nums1[$i]] += 1;
        }
        for ($j = 0; $j < count($nums2); $j++) {
            if (isset($hashtable[$nums2[$j]]) && $hashtable[$nums2[$j]] > 0) {
                array_push($rs, $nums2[$j]);
                $hashtable[$nums2[$j]] -= 1;
            }
        }
        return $rs;
    }
}