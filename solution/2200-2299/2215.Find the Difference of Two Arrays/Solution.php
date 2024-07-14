class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[][]
     */
    function findDifference($nums1, $nums2) {
        $s1 = array_flip($nums1);
        $s2 = array_flip($nums2);

        $diff1 = array_diff_key($s1, $s2);
        $diff2 = array_diff_key($s2, $s1);

        return [array_keys($diff1), array_keys($diff2)];
    }
}