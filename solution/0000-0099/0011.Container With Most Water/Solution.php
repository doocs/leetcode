class Solution {
    /**
     * @param Integer[] $height
     * @return Integer
     */
    function maxArea($height) {
        $l = 0;
        $r = count($height) - 1;
        $ans = 0;
        while ($l < $r) {
            $t = min($height[$l], $height[$r]) * ($r - $l);
            $ans = max($ans, $t);
            if ($height[$l] < $height[$r]) {
                ++$l;
            } else {
                --$r;
            }
        }
        return $ans;
    }
}
