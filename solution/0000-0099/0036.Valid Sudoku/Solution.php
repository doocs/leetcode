class Solution {
    /**
     * @param String[][] $board
     * @return Boolean
     */
    function isValidSudoku($board) {
        $row = array_fill(0, 9, array_fill(0, 9, false));
        $col = array_fill(0, 9, array_fill(0, 9, false));
        $sub = array_fill(0, 9, array_fill(0, 9, false));

        for ($i = 0; $i < 9; $i++) {
            for ($j = 0; $j < 9; $j++) {
                $c = $board[$i][$j];
                if ($c === '.') {
                    continue;
                }
                $num = intval($c) - 1;
                $k = intdiv($i, 3) * 3 + intdiv($j, 3);
                if ($row[$i][$num] || $col[$j][$num] || $sub[$k][$num]) {
                    return false;
                }
                $row[$i][$num] = true;
                $col[$j][$num] = true;
                $sub[$k][$num] = true;
            }
        }
        return true;
    }
}
