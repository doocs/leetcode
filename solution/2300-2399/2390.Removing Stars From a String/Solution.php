class Solution {
    /**
     * @param String $s
     * @return String
     */
    function removeStars($s) {
        $rs = [];
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] == '*') {
                array_pop($rs);
            } else {
                array_push($rs, $s[$i]);
            }
        }
        return join($rs);
    }
}