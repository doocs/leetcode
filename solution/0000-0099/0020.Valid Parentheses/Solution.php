class Solution {
    /**
     * @param string $s
     * @return boolean
     */

    function isValid($s) {
        $stack = [];
        $brackets = [
            ')' => '(',
            '}' => '{',
            ']' => '[',
        ];

        for ($i = 0; $i < strlen($s); $i++) {
            $char = $s[$i];
            if (array_key_exists($char, $brackets)) {
                if (empty($stack) || $stack[count($stack) - 1] !== $brackets[$char]) {
                    return false;
                }
                array_pop($stack);
            } else {
                array_push($stack, $char);
            }
        }
        return empty($stack);
    }
}
