class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function areOccurrencesEqual($s) {
        for ($i = 0; $i < strlen($s); $i++) {
            $hashtable[$s[$i]] += 1;
        }
        $rs = array_unique($hashtable);
        return count($rs) === 1;
    }
}