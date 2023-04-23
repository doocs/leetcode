class Solution {
    /**
     * @param String $s
     * @return String
     */
    function repeatedCharacter($s) {
        for ($i = 0;; $i++) {
            $hashtable[$s[$i]] += 1;
            if ($hashtable[$s[$i]] == 2) return $s[$i];
        }
    }
}