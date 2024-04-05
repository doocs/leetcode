class Solution {
    /**
     * @param integer[] $candidates
     * @param integer $target
     * @return integer[][]
     */

    function combinationSum($candidates, $target) {
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
            $currentCombination[] = $num;

            $this->findCombinations($candidates, $target - $num, $i, $currentCombination, $result);
            array_pop($currentCombination);
        }
    }
}
