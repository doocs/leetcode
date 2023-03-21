class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function firstUniqChar($s) {
        for ($i = 0; $i < strlen($s); $i++) {
            $hashtable[$s[$i]] += 1;
        }
        for ($i = 0; $i < strlen($s); $i++) {
            if ($hashtable[$s[$i]] == 1) {
        $hashmap = [];
        for ($i = 0; $i < strlen($s); $i++) {
            $word = $s[$i];
            $hasmap[$word] += 1;
        }
        for ($i = 0; $i < strlen($s); $i++) {
            $word = $s[$i];
            if ($hasmap[$word] == 1) {
                return $i;
            }
        }
        return -1;
    }
}