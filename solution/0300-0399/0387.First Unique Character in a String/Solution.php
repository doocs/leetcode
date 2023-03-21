class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function firstUniqChar($s) {
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