class Solution {
    /**
     * @param Integer $numBottles
     * @param Integer $numExchange
     * @return Integer
     */
    function maxBottlesDrunk($numBottles, $numExchange) {
        $ans = $numBottles;
        while ($numBottles >= $numExchange) {
            $numBottles -= $numExchange;
            $numExchange++;
            $ans++;
            $numBottles++;
        }
        return $ans;
    }
}
