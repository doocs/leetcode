class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer
     */
    function maxProfit($prices) {
        $ans = 0;
        $mi = $prices[0];
        foreach ($prices as $v) {
            $ans = max($ans, $v - $mi);
            $mi = min($mi, $v);
        }
        return $ans;
    }
}