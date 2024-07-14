class Solution {
    /**
     * @param Integer[] $height
     * @return Integer
     */
    function maxArea($height) {
        $i = 0;
        $j = count($height) - 1;
        $ans = 0;
        while ($i < $j) {
            $t = min($height[$i], $height[$j]) * ($j - $i);
            $ans = max($ans, $t);
            if ($height[$i] < $height[$j]) {
                ++$i;
            } else {
                --$j;
            }
        }
        return $ans;
    }
}