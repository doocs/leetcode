class Solution {
    /**
     * @param String[] $letters
     * @param String $target
     * @return String
     */
    function nextGreatestLetter($letters, $target) {
        $l = 0;
        $r = count($letters);
        while ($l < $r) {
            $mid = $l + $r >> 1;
            if ($letters[$mid] > $target) {
                $r = $mid;
            } else {
                $l = $mid + 1;
            }
        }
        return $letters[$l % count($letters)];
    }
}