class Solution {
    /**
     * @param Integer $numBottles
     * @param Integer $numExchange
     * @return Integer
     */
    function numWaterBottles($numBottles, $numExchange) {
        $sum = $numBottles;
        while ($numBottles >= $numExchange) {
            $numBottles = $numBottles - $numExchange + 1;
            $sum++;
        }
        return $sum;
    }
}
