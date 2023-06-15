class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countGoodSubstrings($s) {
        $cnt = 0;
        for ($i = 0; $i < strlen($s) - 2; $i++) {
            if (
                $s[$i] != $s[$i + 1] &&
                $s[$i] != $s[$i + 2] &&
                $s[$i + 1] != $s[$i + 2]
            ) {
                $cnt++;
            }
        }
        return $cnt++;
    }
}