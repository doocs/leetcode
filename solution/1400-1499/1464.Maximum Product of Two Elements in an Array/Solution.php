class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function maxProduct($nums) {
        $max = 0;
        $submax = 0;
        for ($i = 0; $i < count($nums); $i++) {
            if ($nums[$i] > $max) {
                $submax = $max;
                $max = $nums[$i];
            } elseif ($nums[$i] > $submax) {
                $submax = $nums[$i];
            }
        }
        return ($max - 1) * ($submax - 1);
    }
}