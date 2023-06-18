class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function numIdenticalPairs($nums) {
        $arr = array_values(array_unique($nums));
        for ($i = 0; $i < count($nums); $i++) {
            $v[$nums[$i]] += 1;
        }
        $rs = 0;
        for ($j = 0; $j < count($arr); $j++) {
            $rs += ($v[$arr[$j]] * ($v[$arr[$j]] - 1)) / 2;
        }
        return $rs;
    }
}