class Solution {
    /**
     * @param Integer $num
     * @return String
     */
    function intToRoman($num) {
        $cs = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I'];
        $vs = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
        $ans = '';

        foreach ($vs as $i => $v) {
            while ($num >= $v) {
                $num -= $v;
                $ans .= $cs[$i];
            }
        }

        return $ans;
    }
}