class Solution {
    /**
     * @param String $s
     * @param Integer $k
     * @return Integer
     */
    function getLucky($s, $k) {
        $rs = "";
        for ($i = 0; $i < strlen($s); $i++) {
            $num = ord($s[$i]) - 96;
            $rs = $rs.strval($num);
        }
        while ($k != 0) {
            $sum = 0;
            for ($j = 0; $j < strlen($rs); $j++) {
                $sum += intval($rs[$j]);
            }
            $rs = strval($sum);
            $k--;
        }
        return intval($rs);
    }
}