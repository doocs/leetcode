class NumArray {
    /**
     * @param Integer[] $nums
     */
    function __construct($nums) {
        $this - > nums = $nums;
        $this - > sum = [0];
        for ($i = 0; $i < count($nums); $i++) {
            $rs = $this - > sum[$i] + $this - > nums[$i];
            array_push($this - > sum, $rs);
        }
    }

    /**
     * @param Integer $left
     * @param Integer $right
     * @return Integer
     */
    function sumRange($left, $right) {
        return $this - > sum[$right + 1] - $this - > sum[$left];
    }
}
