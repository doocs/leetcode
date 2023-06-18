class Solution {
    /**
     * @param String $text
     * @return String
     */
    function arrangeWords($text) {
        $text = lcfirst($text);
        $arr = explode(' ', $text);
        for ($i = 0; $i < count($arr); $i++) {
            $hashtable[$i] = strlen($arr[$i]);
        }
        asort($hashtable);
        $key = array_keys($hashtable);
        $rs = [];
        for ($j = 0; $j < count($key); $j++) {
            array_push($rs, $arr[$key[$j]]);
        }
        return ucfirst(implode(' ', $rs));
    }
}