class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer
     */
    function maxProfit($prices) {
        $win = 0;
        $minPrice = $prices[0];
        $len = count($prices);
        for ($i = 1; $i < $len; $i++) {
            $minPrice = min($minPrice, $prices[$i]);
            $win = max($win, $prices[$i] - $minPrice);
        }
        return $win;
    }
}