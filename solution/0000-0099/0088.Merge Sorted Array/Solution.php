class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer $m
     * @param Integer[] $nums2
     * @param Integer $n
     * @return NULL
     */
    function merge(&$nums1, $m, $nums2, $n) {
        for ($i = $m - 1, $j = $n - 1, $k = $m + $n - 1; $j >= 0; --$k) {
            $nums1[$k] = $i >= 0 && $nums1[$i] > $nums2[$j] ? $nums1[$i--] : $nums2[$j--];
        }
    }
}
