class Solution {
    /**
     * @param String $s
     * @param Integer $k
     * @return Integer
     */
    function numKLenSubstrNoRepeats($s, $k) {
        $sum = ($k * ($k + 1)) / 2 - $k;
        $cnt = $tmp = 0;
        for ($i = 0; $i < strlen($s) - $k + 1; $i++) {
            $str = substr($s, $i, $k);
            for ($j = 0; $j < $k; $j++) {
                $tmp += strpos($str, $str[$j]);
            }
            if ($tmp === $sum) {
                $cnt++;
            }
            $tmp = 0;
        }
        return $cnt;
    }
}