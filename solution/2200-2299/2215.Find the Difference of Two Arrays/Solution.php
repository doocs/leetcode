class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[][]
     */
    function findDifference($nums1, $nums2) {
        $rs = [[], []];
        $hashtable1 = array_flip(array_unique($nums1));
        $hashtable2 = array_flip(array_unique($nums2));
        for ($m = 0; $m < count($nums1); $m++) {
            if (!isset($hashtable2[$nums1[$m]])) {
                $rs[0][$m] = $nums1[$m];
                $hashtable2[$nums1[$m]] = 1;
            }
        }
        for ($n = 0; $n < count($nums2); $n++) {
            if (!isset($hashtable1[$nums2[$n]])) {
                $rs[1][$n] = $nums2[$n];
                $hashtable1[$nums2[$n]] = 1;
            }
        }
        return $rs;
    }
}