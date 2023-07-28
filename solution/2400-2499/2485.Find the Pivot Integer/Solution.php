class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function pivotInteger($n) {
        $sum = ($n * ($n + 1)) / 2;
        $pre = 0;
        for ($i = 1; $i <= $n; $i++) {
            if ($pre + $i === $sum - $pre) {
                return $i;
            }
            $pre += $i;
        }
        return -1;
    }
}