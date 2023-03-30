class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersection($nums1, $nums2) {
        $rs = [];
        $set1 = array_values(array_unique($nums1));
        $set2 = array_values(array_unique($nums2));
        for ($i = 0; $i < count($set1); $i++) {
            $hashmap[$set1[$i]] = 1;
        }
        for ($j = 0; $j < count($set2); $j++) {
            if ($hashmap[$set2[$j]]) array_push($rs, $set2[$j]);
        }
        return $rs;
    }
}