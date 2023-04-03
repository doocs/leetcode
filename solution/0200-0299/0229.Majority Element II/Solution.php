class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function majorityElement($nums) {
        $rs = [];
        $n = count($nums);
        for ($i = 0; $i < $n; $i++) {
            $hashmap[$nums[$i]] += 1;
            if ($hashmap[$nums[$i]] > $n / 3)
                array_push($rs, $nums[$i]);
        }
        return array_unique($rs);
    }
}