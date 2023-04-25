class Solution {
    /**
     * @param String[] $names
     * @param Integer[] $heights
     * @return String[]
     */
    function sortPeople($names, $heights) {
        for ($i = 0; $i < count($heights); $i++) {
            $hashtable[$heights[$i]] = $names[$i];
        }
        rsort($heights);
        for ($j = 0; $j < count($heights); $j++) {
            $rs[$j] = $hashtable[$heights[$j]];
        }
        return $rs;
    }
}