class Solution {
    /**
     * @param String $s
     * @return String
     */
    function reverseWords($s) {
        $sArr = explode(' ', $s);
        for ($i = 0; $i < count($sArr); $i++) {
            $sArr[$i] = strrev($sArr[$i]);
        }
        return implode(' ', $sArr);
    }
}