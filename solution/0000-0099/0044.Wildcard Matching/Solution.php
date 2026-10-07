class Solution {
    /**
     * @param string $s
     * @param string $p
     * @return boolean
     */
    private $f;
    private $s;
    private $p;
    private $m;
    private $n;

    function isMatch($s, $p) {
        $this->s = $s;
        $this->p = $p;
        $this->m = strlen($s);
        $this->n = strlen($p);
        $this->f = [];
        for ($i = 0; $i < $this->m; $i++) {
            $this->f[$i] = array_fill(0, $this->n, null);
        }
        return $this->dfs(0, 0);
    }

    function dfs($i, $j) {
        if ($i >= $this->m) {
            return $j >= $this->n || ($this->p[$j] == '*' && $this->dfs($i, $j + 1));
        }
        if ($j >= $this->n) {
            return false;
        }
        if ($this->f[$i][$j] !== null) {
            return $this->f[$i][$j];
        }
        if ($this->p[$j] == '*') {
            $this->f[$i][$j] =
                $this->dfs($i + 1, $j) || $this->dfs($i + 1, $j + 1) || $this->dfs($i, $j + 1);
        } else {
            $this->f[$i][$j] =
                ($this->p[$j] == '?' || $this->s[$i] == $this->p[$j]) && $this->dfs($i + 1, $j + 1);
        }
        return $this->f[$i][$j];
    }
}
