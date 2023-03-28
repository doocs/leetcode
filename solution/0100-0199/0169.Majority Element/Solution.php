class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function majorityElement($nums) {
        $major = 0;
        $count = 0;
        for ($i = 0; $i < count($nums); $i++) {
            if ($count == 0) $major = $nums[$i];
            if ($major == $nums[$i]) $count++;
            else $count--;
        }
        return $major;
    }
}