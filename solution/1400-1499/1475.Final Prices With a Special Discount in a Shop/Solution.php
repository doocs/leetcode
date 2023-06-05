class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer[]
     */
    function finalPrices($prices) {
        for ($i = 0; $i < count($prices); $i++) {
            for ($j = $i + 1; $j < count($prices); $j++) {
                if ($prices[$i] >= $prices[$j]) {
                    $prices[$i] -= $prices[$j];
                    break;
                }
            }
        }
        return $prices;
    }
}