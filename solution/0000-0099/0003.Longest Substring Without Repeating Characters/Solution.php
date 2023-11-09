class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function lengthOfLongestSubstring($s) {
        $max = 0;
        for ($i = 0; $i < strlen($s); $i++) {
            $chars = [];
            $sub = '';
            for ($j = $i; $j < strlen($s); $j++) {
                if (in_array($s[$j], $chars)) {
                    break;
                }
                $sub .= $s[$j];
                $chars[] = $s[$j];
            }
            if (strlen($sub) > $max) {
                $max = strlen($sub);
            }
        }
        return $max;
    }
}
