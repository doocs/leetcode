class Solution {
    /**
     * @param String $blocks
     * @param Integer $k
     * @return Integer
     */
    function minimumRecolors($blocks, $k) {
        $cnt = 0;
        for ($i = 0; $i < $k; $i++) {
            if ($blocks[$i] === 'W') {
                $cnt++;
            }
        }
        $min = $cnt;
        for ($i = $k; $i < strlen($blocks); $i++) {
            if ($blocks[$i] === 'W') {
                $cnt++;
            }
            if ($blocks[$i - $k] === 'W') {
                $cnt--;
            }
            $min = min($min, $cnt);
        }
        return $min;
    }
}