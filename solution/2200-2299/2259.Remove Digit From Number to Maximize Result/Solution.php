class Solution {
    /**
     * @param String $number
     * @param String $digit
     * @return String
     */
    function removeDigit($number, $digit) {
        $max = 0;
        for ($i = 0; $i < strlen($number); $i++) {
            if ($number[$i] == $digit) {
                $tmp = substr($number, 0, $i) . substr($number, $i + 1);
                if ($tmp > $max) {
                    $max = $tmp;
                }
            }
        }
        return $max;
    }
}