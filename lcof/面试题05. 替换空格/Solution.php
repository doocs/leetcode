class Solution {
    /**
     * @param String $s
     * @return String
     */
    function replaceSpace($s) {
        $rs = '';
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] === ' ') {
                $rs = $rs . '%20';
            } else {
                $rs = $rs . $s[$i];
            }
        }
        return $rs;
    }
}
