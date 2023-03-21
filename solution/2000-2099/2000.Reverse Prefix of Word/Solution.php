class Solution {
    /**
     * @param String $word
     * @param String $ch
     * @return String
     */
    function reversePrefix($word, $ch) {
        $len = strlen($word);
        $rs = '';
        for ($i = 0; $i < $len; $i++) {
            $rs = $rs.$word[$i];
            if ($word[$i] == $ch) {
                break;
            }
        }
        if (strlen($rs) == $len && $rs[$len - 1] != $ch) {
            return $word;
        }
        $rs = strrev($rs);
        $rs = $rs.substr($word, strlen($rs));
        return $rs;
    }
}