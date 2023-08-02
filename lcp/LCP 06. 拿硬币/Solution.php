class Solution {
    /**
     * @param Integer[] $coins
     * @return Integer
     */
    function minCount($coins) {
        $cnt = 0;
        for ($i = 0; $i < count($coins); $i++) {
            $cnt += floor($coins[$i] / 2) + ($coins[$i] % 2);
        }
        return $cnt;
    }
}