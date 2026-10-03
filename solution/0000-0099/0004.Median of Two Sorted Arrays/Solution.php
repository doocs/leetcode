class Solution {
    /**
     * @param int[] $nums1
     * @param int[] $nums2
     * @return float
     */

    function findMedianSortedArrays($nums1, $nums2) {
        $m = count($nums1);
        $n = count($nums2);
        $a = $this->f($nums1, $nums2, $m, $n, 0, 0, intdiv($m + $n + 1, 2));
        $b = $this->f($nums1, $nums2, $m, $n, 0, 0, intdiv($m + $n + 2, 2));
        return ($a + $b) / 2;
    }

    private function f($nums1, $nums2, $m, $n, $i, $j, $k) {
        if ($i >= $m) {
            return $nums2[$j + $k - 1];
        }
        if ($j >= $n) {
            return $nums1[$i + $k - 1];
        }
        if ($k == 1) {
            return min($nums1[$i], $nums2[$j]);
        }
        $p = intdiv($k, 2);
        $x = $i + $p - 1 < $m ? $nums1[$i + $p - 1] : 1 << 30;
        $y = $j + $p - 1 < $n ? $nums2[$j + $p - 1] : 1 << 30;
        return $x < $y
            ? $this->f($nums1, $nums2, $m, $n, $i + $p, $j, $k - $p)
            : $this->f($nums1, $nums2, $m, $n, $i, $j + $p, $k - $p);
    }
}
