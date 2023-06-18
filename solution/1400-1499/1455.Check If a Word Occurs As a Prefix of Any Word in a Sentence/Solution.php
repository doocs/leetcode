class Solution {
    /**
     * @param String $sentence
     * @param String $searchWord
     * @return Integer
     */
    function isPrefixOfWord($sentence, $searchWord) {
        $arr = explode(' ', $sentence);
        for ($i = 0; $i < count($arr); $i++) {
            if (strpos($arr[$i], $searchWord) === 0) {
                return $i + 1;
            }
        }
        return -1;
    }
}