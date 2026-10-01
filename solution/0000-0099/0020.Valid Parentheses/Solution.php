class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function isValid($s) {
        $stk = [];
        $d = [
            '(' => ')',
            '[' => ']',
            '{' => '}',
        ];
        $n = strlen($s);
        for ($i = 0; $i < $n; $i++) {
            $c = $s[$i];
            if (isset($d[$c])) {
                $stk[] = $d[$c];
            } elseif (empty($stk) || array_pop($stk) !== $c) {
                return false;
            }
        }
        return empty($stk);
    }
}
