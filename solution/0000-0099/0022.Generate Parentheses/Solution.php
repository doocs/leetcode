class Solution {

    private $ans = [];
    private $n = 0;

    /**
     * @param Integer $n
     * @return String[]
     */
    function generateParenthesis($n) {
        $this->n = $n;
        $this->ans = [];
        $this->dfs(0, 0, "");
        return $this->ans;
    }

    private function dfs($l, $r, $t) {
        if ($l > $this->n || $r > $this->n || $l < $r) {
            return;
        }
        if ($l == $this->n && $r == $this->n) {
            $this->ans[] = $t;
            return;
        }
        $this->dfs($l + 1, $r, $t . "(");
        $this->dfs($l, $r + 1, $t . ")");
    }
}
