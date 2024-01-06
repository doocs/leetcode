class Solution {
    /**
     * @param String[] $words
     * @param String $chars
     * @return Integer
     */
    function countCharacters($words, $chars) {
        $sum = 0;
        for ($i = 0; $i < strlen($chars); $i++) {
            $hashtable[$chars[$i]] += 1;
        }
        for ($j = 0; $j < count($words); $j++) {
            $tmp = $hashtable;
            $sum += strlen($words[$j]);
            for ($k = 0; $k < strlen($words[$j]); $k++) {
                if (!isset($tmp[$words[$j][$k]]) || $tmp[$words[$j][$k]] === 0) {
                    $sum -= strlen($words[$j]);
                    break;
                }
                $tmp[$words[$j][$k]] -= 1;
            }
        }
        return $sum;
    }
}