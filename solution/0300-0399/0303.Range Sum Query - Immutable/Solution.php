class NumArray {
    /**
     * @param Integer[] $nums
     */
    function __construct($nums) {
            $this - > nums = $nums;
        }
        /**
         * @param Integer $left
         * @param Integer $right
         * @return Integer
         */
    function sumRange($left, $right) {
        $rs = $this - > nums[$left];
        while ($left - $right != 0) {
            $rs += $this - > nums[$left + 1];
            $left++;
        }
        return $rs;
    }
}