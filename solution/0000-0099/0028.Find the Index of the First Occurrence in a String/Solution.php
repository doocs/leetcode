class Solution {
    /**
     * @param String $haystack
     * @param String $needle
     * @return Integer
     */
    function strStr($haystack, $needle) {
        $strNew = str_replace($needle, "+", $haystack);
        $cnt = substr_count($strNew, "+");
        if ($cnt > 0) {
            for ($i = 0; $i < strlen($strNew); $i++) {
                if ($strNew[$i] == "+") {
                    return $i;
                }
            }
        } else {
            return -1;
        }
    }
}