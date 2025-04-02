class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function tribonacci($n) {
        if ($n === 0) {
            return 0;
        }
        if ($n < 3) {
            return 1;
        }

        $a = [[1, 1, 0], [1, 0, 1], [1, 0, 0]];

        $res = $this->pow($a, $n - 3);
        return array_sum($res[0]);
    }

    private function mul($a, $b) {
        $m = count($a);
        $n = count($b[0]);
        $p = count($b);

        $c = array_fill(0, $m, array_fill(0, $n, 0));

        for ($i = 0; $i < $m; ++$i) {
            for ($j = 0; $j < $n; ++$j) {
                for ($k = 0; $k < $p; ++$k) {
                    $c[$i][$j] += $a[$i][$k] * $b[$k][$j];
                }
            }
        }

        return $c;
    }

    private function pow($a, $n) {
        $res = [[1, 1, 0]];
        while ($n > 0) {
            if ($n & 1) {
                $res = $this->mul($res, $a);
            }
            $a = $this->mul($a, $a);
            $n >>= 1;
        }
        return $res;
    }
}