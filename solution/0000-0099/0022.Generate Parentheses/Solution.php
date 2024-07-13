class Solution {

/**
 * @param Integer $n
 * @return String[]
 */
function generateParenthesis($n) {
    $ans = [];

    $dfs = function($l, $r, $t) use ($n, &$ans, &$dfs) {
        if ($l > $n || $r > $n || $l < $r) return;
        if ($l == $n && $r == $n) {
            $ans[] = $t;
            return;
        }
        $dfs($l + 1, $r, $t . "(");
        $dfs($l, $r + 1, $t . ")");
    };

    $dfs(0, 0, "");
    return $ans;
}
}
