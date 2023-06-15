class Solution {
    /**
     * @param Integer[] $gain
     * @return Integer
     */
    function largestAltitude($gain) {
        $max = 0;
        for ($i = 0; $i < count($gain); $i++) {
            $tmp += $gain[$i];
            if ($tmp > $max) {
                $max = $tmp;
            }
        }
        return $max;
    }
}