class Solution {
    /**
     * @param String $s
     * @return String
     */
    function makeFancyString($s) {
        $ans = '';
        for ($i = 0; $i < strlen($s); $i++) {
            $c = $s[$i];
            if ($i < 2 || $c !== $s[$i - 1] || $c !== $s[$i - 2]) {
                $ans .= $c;
            }
        }
        return $ans;
    }
}