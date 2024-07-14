class Solution {
    /**
     * @param string[][] $board
     * @return bool
     */

    public function solveSudoku(&$board) {
        if (isSolved($board)) {
            return true;
        }

        $emptyCell = findEmptyCell($board);
        $row = $emptyCell[0];
        $col = $emptyCell[1];

        for ($num = 1; $num <= 9; $num++) {
            if (isValid($board, $row, $col, $num)) {
                $board[$row][$col] = (string) $num;
                if ($this->solveSudoku($board)) {
                    return true;
                }
                $board[$row][$col] = '.';
            }
        }
        return false;
    }
}

function isSolved($board) {
    foreach ($board as $row) {
        if (in_array('.', $row)) {
            return false;
        }
    }
    return true;
}

function findEmptyCell($board) {
    for ($row = 0; $row < 9; $row++) {
        for ($col = 0; $col < 9; $col++) {
            if ($board[$row][$col] === '.') {
                return [$row, $col];
            }
        }
    }

    return null;
}

function isValid($board, $row, $col, $num) {
    for ($i = 0; $i < 9; $i++) {
        if ($board[$row][$i] == $num) {
            return false;
        }
    }

    for ($i = 0; $i < 9; $i++) {
        if ($board[$i][$col] == $num) {
            return false;
        }
    }

    $startRow = floor($row / 3) * 3;
    $endCol = floor($col / 3) * 3;

    for ($i = 0; $i < 3; $i++) {
        for ($j = 0; $j < 3; $j++) {
            if ($board[$startRow + $i][$endCol + $j] == $num) {
                return false;
            }
        }
    }

    return true;
}
