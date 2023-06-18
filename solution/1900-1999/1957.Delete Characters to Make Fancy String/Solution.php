class Solution {
    /**
     * @param String $s
     * @return String
     */
    function makeFancyString($s) {
        $rs = '';
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] == $s[$i + 1] && $s[$i] == $s[$i + 2]) {
                continue;
            } else {
                $rs .= $s[$i];
            }
        }
        return $rs;
    }
}