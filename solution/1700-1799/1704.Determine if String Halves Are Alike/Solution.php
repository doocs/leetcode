class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function halvesAreAlike($s) {
        $n = strlen($s) / 2;
        $vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];
        $cnt = 0;

        for ($i = 0; $i < $n; $i++) {
            if (in_array($s[$i], $vowels)) {
                $cnt++;
            }
            if (in_array($s[$i + $n], $vowels)) {
                $cnt--;
            }
        }

        return $cnt == 0;
    }
}