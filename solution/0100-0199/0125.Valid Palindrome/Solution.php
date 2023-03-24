class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function isPalindrome($s) {
        $regex = "/[a-z0-9]/";
        $s = strtolower($s);
        preg_match_all($regex, $s, $matches);
        if ($matches[0] == Null) return true;
        $len = floor(count($matches[0]) / 2);
        for ($i = 0; $i < $len; $i++) {
            if ($matches[0][$i] != $matches[0][count($matches[0]) - 1 - $i]) {
                return false;
            }
        }
        return true;
    }
}