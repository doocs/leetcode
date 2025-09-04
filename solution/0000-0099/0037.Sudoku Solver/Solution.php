class Solution {
    /**
     * @param String[][] $board
     * @return NULL
     */
    function solveSudoku(&$board) {
        $row = array_fill(0, 9, array_fill(0, 9, false));
        $col = array_fill(0, 9, array_fill(0, 9, false));
        $block = array_fill(0, 3, array_fill(0, 3, array_fill(0, 9, false)));
        $ok = false;
        $t = [];

        for ($i = 0; $i < 9; ++$i) {
            for ($j = 0; $j < 9; ++$j) {
                if ($board[$i][$j] === '.') {
                    $t[] = [$i, $j];
                } else {
                    $v = ord($board[$i][$j]) - ord('1');
                    $row[$i][$v] = true;
                    $col[$j][$v] = true;
                    $block[intval($i / 3)][intval($j / 3)][$v] = true;
                }
            }
        }

        $dfs = function ($k) use (&$dfs, &$board, &$row, &$col, &$block, &$ok, &$t) {
            if ($k === count($t)) {
                $ok = true;
                return;
            }
            [$i, $j] = $t[$k];
            for ($v = 0; $v < 9; ++$v) {
                if (!$row[$i][$v] && !$col[$j][$v] && !$block[intval($i / 3)][intval($j / 3)][$v]) {
                    $row[$i][$v] = $col[$j][$v] = $block[intval($i / 3)][intval($j / 3)][$v] = true;
                    $board[$i][$j] = chr($v + ord('1'));
                    $dfs($k + 1);
                    if ($ok) {
                        return;
                    }
                    $row[$i][$v] = $col[$j][$v] = $block[intval($i / 3)][intval($j / 3)][
                        $v
                    ] = false;
                }
            }
        };

        $dfs(0);
    }
}
