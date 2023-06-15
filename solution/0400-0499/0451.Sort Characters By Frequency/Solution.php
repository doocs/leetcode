class Solution {
    /**
     * @param String $s
     * @return String
     */
    function frequencySort($s) {
        for ($i = 0; $i < strlen($s); $i++) {
            $hashtable[$s[$i]] += 1;
        }
        arsort($hashtable);
        $keys = array_keys($hashtable);
        for ($j = 0; $j < count($keys); $j++) {
            $rs = $rs . str_repeat($keys[$j], $hashtable[$keys[$j]]);
        }
        return $rs;
    }
}