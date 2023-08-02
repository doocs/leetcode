class Solution {
    /**
     * @param String $s
     * @param Integer $k
     * @return Integer
     */
    function isVowel($c) {
        return $c === 'a' ||
            $c === 'e' ||
            $c === 'i' ||
            $c === 'o' ||
            $c === 'u';
    }
    function maxVowels($s, $k) {
        $cnt = 0;
        $rs = 0;
        for ($i = 0; $i < $k; $i++) {
            if ($this->isVowel($s[$i])) {
                $cnt++;
            }
        }
        $rs = $cnt;
        for ($j = $k; $j < strlen($s); $j++) {
            if ($this->isVowel($s[$j - $k])) {
                $cnt--;
            }
            if ($this->isVowel($s[$j])) {
                $cnt++;
            }
            $rs = max($rs, $cnt);
        }
        return $rs;
    }
}