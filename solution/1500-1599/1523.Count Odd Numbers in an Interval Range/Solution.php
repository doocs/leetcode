class Solution {

    /**
     * @param Integer $low
     * @param Integer $high
     * @return Integer
     */
    function countOdds($low, $high) {
        return (($high + 1) >> 1) - ($low >> 1);
    }
}