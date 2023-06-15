class Solution {
    /**
     * @param String $secret
     * @param String $guess
     * @return String
     */
    function getHint($secret, $guess) {
        $cntA = 0;
        $cntB = 0;
        $len = strlen($secret);
        for ($i = 0; $i < $len; $i++) {
            if ($secret[$i] == $guess[$i]) {
                $cntA++;
            } else {
                $hashtable[$secret[$i]] += 1;
            }
        }
        for ($i = 0; $i < $len; $i++) {
            if ($secret[$i] != $guess[$i] && $hashtable[$guess[$i]] > 0) {
                $cntB++;
                $hashtable[$guess[$i]] -= 1;
            }
        }
        return $cntA . 'A' . $cntB . 'B';
    }
}