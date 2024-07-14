class Solution {
    /**
     * @param integer[] $candidates
     * @param integer $target
     * @return integer[][]
     */

    function combinationSum2($candidates, $target) {
        $result = [];
        $currentCombination = [];
        $startIndex = 0;

        sort($candidates);
        $this->findCombinations($candidates, $target, $startIndex, $currentCombination, $result);
        return $result;
    }

    function findCombinations($candidates, $target, $startIndex, $currentCombination, &$result) {
        if ($target === 0) {
            $result[] = $currentCombination;
            return;
        }

        for ($i = $startIndex; $i < count($candidates); $i++) {
            $num = $candidates[$i];
            if ($num > $target) {
                break;
            }

            if ($i > $startIndex && $candidates[$i] === $candidates[$i - 1]) {
                continue;
            }
            $currentCombination[] = $num;

            $this->findCombinations($candidates, $target - $num, $i + 1, $currentCombination, $result);
            array_pop($currentCombination);
        }
    }
}
