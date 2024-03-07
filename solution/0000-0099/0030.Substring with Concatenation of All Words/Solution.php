class Solution {
    /**
     * @param string $s
     * @param string[] $words
     * @return integer[]
     */

    function findSubstring($s, $words) {
        $wordLength = strlen($words[0]);
        $totalLength = count($words) * $wordLength;
        $wordFreq = array_count_values($words);
        $result = [];
        for ($i = 0; $i <= strlen($s) - $totalLength; $i++) {
            $seen = [];
            $j = 0;
            while ($j < $totalLength) {
                $word = substr($s, $i + $j, $wordLength);
                if (isset($wordFreq[$word])) {
                    $seen[$word] = isset($seen[$word]) ? $seen[$word] + 1 : 1;
                    if ($seen[$word] > $wordFreq[$word]) {
                        break;
                    }
                    $j += $wordLength;
                } else {
                    break;
                }
            }
            if ($j === $totalLength) {
                $result[] = $i;
            }
        }
        return $result;
    }
}
