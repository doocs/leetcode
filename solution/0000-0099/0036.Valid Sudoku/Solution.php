class Solution {
    /**
     * @param string[][] $board
     * @return boolean
     */

    function isValidSudoku($board) {
        $rows = [];
        $columns = [];
        $boxes = [];

        for ($i = 0; $i < 9; $i++) {
            $rows[$i] = [];
            $columns[$i] = [];
            $boxes[$i] = [];
        }

        for ($row = 0; $row < 9; $row++) {
            for ($column = 0; $column < 9; $column++) {
                $cell = $board[$row][$column];

                if ($cell != '.') {
                    if (in_array($cell, $rows[$row]) || in_array($cell, $columns[$column]) || in_array($cell, $boxes[floor($row / 3) * 3 + floor($column / 3)])) {
                        return false;
                    }

                    $rows[$row][] = $cell;
                    $columns[$column][] = $cell;
                    $boxes[floor($row / 3) * 3 + floor($column / 3)][] = $cell;
                }
            }
        }
        return true;
    }
}
