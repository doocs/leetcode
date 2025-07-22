class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer[]
     */
    function finalPrices($prices) {
        $stk = [];
        $n = count($prices);

        for ($i = $n - 1; $i >= 0; $i--) {
            $x = $prices[$i];
            while (!empty($stk) && $x < end($stk)) {
                array_pop($stk);
            }
            if (!empty($stk)) {
                $prices[$i] -= end($stk);
            }
            $stk[] = $x;
        }

        return $prices;
    }
}
