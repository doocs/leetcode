class Solution {
    /**
     * @param String $s
     * @return String
     */
    function makeFancyString($s) {
        $ans = [];
        $length = strlen($s);

        for ($i = 0; $i < $length; $i++) {
            $n = count($ans);
            if ($n < 2 || $s[$i] !== $ans[$n - 1] || $s[$i] !== $ans[$n - 2]) {
                $ans[] = $s[$i];
            }
        }

        return implode('', $ans);
    }
}
