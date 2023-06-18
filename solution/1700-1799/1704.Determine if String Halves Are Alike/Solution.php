class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function halvesAreAlike($s) {
        $cnt = 0;
        for ($i = 0; $i < strlen($s) / 2; $i++) {
            if (strpos('aeiouAEIOU', $s[$i]) !== false) {
                $cnt++;
            }
            if (strpos('aeiouAEIOU', $s[strlen($s) / 2 + $i]) !== false) {
                $cnt--;
            }
        }
        return $cnt == 0;
    }
}