class Solution {
    /**
     * @param integer[] $nums
     * @param integer $target
     * @return integer
     */

    function search($nums, $target) {
        $foundKey = -1;
        foreach ($nums as $key => $value) {
            if ($value === $target) {
                $foundKey = $key;
            }
        }
        return $foundKey;
    }
}
