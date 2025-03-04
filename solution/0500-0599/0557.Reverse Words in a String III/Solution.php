class Solution {
    /**
     * @param String $s
     * @return String
     */
    function reverseWords($s) {
        $words = explode(' ', $s);
        foreach ($words as $i => $word) {
            $words[$i] = strrev($word);
        }
        return implode(' ', $words);
    }
}
