class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersect($nums1, $nums2) {
        $cnt = [];
        foreach ($nums1 as $x) {
            if (isset($cnt[$x])) {
                $cnt[$x]++;
            } else {
                $cnt[$x] = 1;
            }
        }

        $ans = [];
        foreach ($nums2 as $x) {
            if (isset($cnt[$x]) && $cnt[$x] > 0) {
                $ans[] = $x;
                $cnt[$x]--;
            }
        }

        return $ans;
    }
}