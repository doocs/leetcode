class Solution {
    /**
     * @param Integer $x
     * @return Boolean
     */
    function isPalindrome($x) {
        if ($x < 0 || ($x && $x % 10 == 0)) {
            return false;
        }
        $y = 0;
        while ($x > $y) {
            $y = $y * 10 + ($x % 10);
            $x = (int) ($x / 10);
        }
        return $x == $y || $x == (int) ($y / 10);
    }
}
