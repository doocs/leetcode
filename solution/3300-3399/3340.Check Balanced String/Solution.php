class Solution {
    /**
     * @param String $num
     * @return Boolean
     */
    function isBalanced($num) {
        $f = [0, 0];
        foreach (str_split($num) as $i => $ch) {
            $f[$i & 1] += ord($ch) - 48;
        }
        return $f[0] == $f[1];
    }
}
