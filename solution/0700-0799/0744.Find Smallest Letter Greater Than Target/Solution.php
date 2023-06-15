class Solution {
    /**
     * @param String[] $letters
     * @param String $target
     * @return String
     */
    function nextGreatestLetter($letters, $target) {
        $left = 0;
        $right = count($letters);
        while ($left <= $right) {
            $mid = floor($left + ($right - $left) / 2);
            if ($letters[$mid] > $target) {
                $right = $mid - 1;
            } else {
                $left = $mid + 1;
            }
        }
        if ($left >= count($letters)) {
            return $letters[0];
        } else {
            return $letters[$left];
        }
    }
}