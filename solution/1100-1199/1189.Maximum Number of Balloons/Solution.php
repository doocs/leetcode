class Solution {
    /**
     * @param String $text
     * @return Integer
     */
    function maxNumberOfBalloons($text) {
        $cnt1 = $cnt2 = $cnt3 = $cnt4 = $cnt5 = 0;
        for ($i = 0; $i < strlen($text); $i++) {
            if ($text[$i] == "b") $cnt1 += 1;
            else if ($text[$i] == "a") $cnt2 += 1;
            else if ($text[$i] == "l") $cnt3 += 1;
            else if ($text[$i] == "o") $cnt4 += 1;
            else if ($text[$i] == "n") $cnt5 += 1;
        }
        $cnt3 = floor($cnt3 / 2);
        $cnt4 = floor($cnt4 / 2);
        return min($cnt1, $cnt2, $cnt3, $cnt4, $cnt5);
    }
}