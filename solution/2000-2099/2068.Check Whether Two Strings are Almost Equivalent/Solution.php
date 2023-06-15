class Solution {
    /**
     * @param String $word1
     * @param String $word2
     * @return Boolean
     */
    function checkAlmostEquivalent($word1, $word2) {
        for ($i = 0; $i < strlen($word1); $i++) {
            $hashtable[$word1[$i]] += 1;
            $hashtable[$word2[$i]] -= 1;
        }
        $keys = array_keys($hashtable);
        for ($j = 0; $j < count($keys); $j++) {
            if (abs($hashtable[$keys[$j]]) > 3) {
                return false;
            }
        }
        return true;
    }
}