class Solution {
    /**
     * @param Integer[] $coins
     * @return Integer
     */
    function minCount($coins) {
        $ans = 0;
        foreach ($coins as $x) {
            $ans += $x + 1 >> 1;
        }
        return $ans;
    }
}