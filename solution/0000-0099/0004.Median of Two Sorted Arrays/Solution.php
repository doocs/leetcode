class Solution
{

    /**
     * @param int[] $nums1
     * @param int[] $nums2
     * @return float
     */

    function findMedianSortedArrays($nums1, $nums2)
    {

        $arr = array_merge($nums1, $nums2);
        sort($arr);
        $cnt_arr = count($arr);

        if ($cnt_arr % 2) {
            return $arr[$cnt_arr / 2];
        } else {
            return ($arr[intdiv($cnt_arr, 2) - 1] + $arr[intdiv($cnt_arr, 2)]) / 2;
        }
    }
}
