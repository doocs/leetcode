class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersection($nums1, $nums2) {
        $s1 = array_unique($nums1);
        $s2 = array_unique($nums2);
        $ans = array_intersect($s1, $s2);
        return array_values($ans);
    }
}