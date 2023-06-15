class Solution {
    /**
     * @param Integer[] $arr1
     * @param Integer[] $arr2
     * @param Integer[] $arr3
     * @return Integer[]
     */
    function arraysIntersection($arr1, $arr2, $arr3) {
        $rs = [];
        $arr = array_merge($arr1, $arr2, $arr3);
        for ($i = 0; $i < count($arr); $i++) {
            $hashtable[$arr[$i]] += 1;
            if ($hashtable[$arr[$i]] === 3) {
                array_push($rs, $arr[$i]);
            }
        }
        return $rs;
    }
}