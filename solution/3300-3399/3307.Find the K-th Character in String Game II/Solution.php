class Solution {
    /**
     * @param Integer $k
     * @param Integer[] $operations
     * @return String
     */
    function kthCharacter($k, $operations) {
        $n = 1;
        $i = 0;
        while ($n < $k) {
            $n *= 2;
            ++$i;
        }
        $d = 0;
        while ($n > 1) {
            if ($k > $n / 2) {
                $k -= $n / 2;
                $d += $operations[$i - 1];
            }
            $n /= 2;
            --$i;
        }
        return chr(ord('a') + ($d % 26));
    }
}