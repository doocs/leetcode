class NumArray {
    /**
     * @param Integer[] $nums
     */
    function __construct($nums) {
        $this->s = [0];
        foreach ($nums as $x) {
            $this->s[] = $this->s[count($this->s) - 1] + $x;
        }
    }

    /**
     * @param Integer $left
     * @param Integer $right
     * @return Integer
     */
    function sumRange($left, $right) {
        return $this->s[$right + 1] - $this->s[$left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * $obj = NumArray($nums);
 * $ret_1 = $obj->sumRange($left, $right);
 */