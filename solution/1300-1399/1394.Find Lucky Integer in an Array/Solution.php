class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function findLucky($arr) {
        $max = -1;
        for ($i = 0; $i < count($arr); $i++) {
            $hashtable[$arr[$i]] += 1;
        }
        $keys = array_keys($hashtable);
        for ($j = 0; $j < count($keys); $j++) {
            if ($hashtable[$keys[$j]] == $keys[$j]) {
                $max = max($max, $keys[$j]);
            }
        }
        return $max;
    }
}