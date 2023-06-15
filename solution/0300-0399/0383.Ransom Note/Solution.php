class Solution {
    /**
     * @param String $ransomNote
     * @param String $magazine
     * @return Boolean
     */
    function canConstruct($ransomNote, $magazine) {
        $arrM = str_split($magazine);
        for ($i = 0; $i < strlen($magazine); $i++) {
            $hashtable[$arrM[$i]] += 1;
        }
        for ($j = 0; $j < strlen($ransomNote); $j++) {
            if (
                !isset($hashtable[$ransomNote[$j]]) ||
                $hashtable[$ransomNote[$j]] == 0
            ) {
                return false;
            } else {
                $hashtable[$ransomNote[$j]] -= 1;
            }
        }
        return true;
    }
}