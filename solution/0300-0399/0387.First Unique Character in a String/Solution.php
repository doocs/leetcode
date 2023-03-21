class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function firstUniqChar($s) {
        for ($i = 0; $i < strlen($s); $i++) {
            $hashmap[$s[$i]] += 1;
        }
        for ($i = 0; $i < strlen($s); $i++) {
            if ($hashmap[$s[$i]] == 1) {
                return $i;
            }
        }
        return -1;
    }
}