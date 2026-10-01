class Solution {
    /**
     * @param string $s
     * @return integer
     */

    function longestValidParentheses($s) {
        $stack = [];
        $maxLength = 0;

        array_push($stack, -1);
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] === '(') {
                array_push($stack, $i);
            } else {
                array_pop($stack);

                if (empty($stack)) {
                    array_push($stack, $i);
                } else {
                    $length = $i - end($stack);
                    $maxLength = max($maxLength, $length);
                }
            }
        }
        return $maxLength;
    }
}
