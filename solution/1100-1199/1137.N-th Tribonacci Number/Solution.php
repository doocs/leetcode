class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function tribonacci($n) {
        $a = 0;
        $b = 1;
        $c = 1;
        
        while ($n--) {
            $d = $a + $b + $c;
            $a = $b;
            $b = $c;
            $c = $d;
        }
        
        return $a;
    }
}