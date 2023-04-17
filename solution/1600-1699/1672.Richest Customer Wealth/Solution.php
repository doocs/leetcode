class Solution {
    /**
     * @param Integer[][] $accounts
     * @return Integer
     */
    function maximumWealth($accounts) {
        $rs = 0;
        for ($i = 0; $i < count($accounts); $i++) {
            $sum = 0;
            for ($j = 0; $j < count($accounts[$i]); $j++)
                $sum += $accounts[$i][$j];
            if ($sum > $rs) $rs = $sum;
        }
        return $rs;
    }
}