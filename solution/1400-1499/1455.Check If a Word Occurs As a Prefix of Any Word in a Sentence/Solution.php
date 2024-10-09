class Solution {
    /**
     * @param String $sentence
     * @param String $searchWord
     * @return Integer
     */
    function isPrefixOfWord($sentence, $searchWord) {
        $words = explode(' ', $sentence);
        for ($i = 0; $i < count($words); ++$i) {
            if (strpos($words[$i], $searchWord) === 0) {
                return $i + 1;
            }
        }
        return -1;
    }
}
