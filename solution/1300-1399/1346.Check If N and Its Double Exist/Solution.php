class Solution {
    /**
     * @param Integer[] $arr
     * @return Boolean
     */
    function checkIfExist($arr) {
        for ($i = 0; $i < count($arr); $i++) {
            $hashtable[$arr[$i] * 2] = $i;
        }
        for ($i = 0; $i < count($arr); $i++) {
            if (isset($hashtable[$arr[$i]]) && $hashtable[$arr[$i]] != $i) {
                return true;
            }
        }
        return false;
    }
}