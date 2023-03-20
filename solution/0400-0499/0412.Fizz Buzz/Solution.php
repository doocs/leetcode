class Solution {
    /**
     * @param Integer $n
     * @return String[]
     */
    function fizzBuzz($n) {
        $rs = [];
        for ($i = 1; $i <= $n; $i++) {
            if ($i % 3 != 0 && $i % 5 != 0) {
                array_push($rs, strval($i));
            } else if ($i % 3 == 0 && $i % 5 != 0) {
                array_push($rs, "Fizz");
            } else if ($i % 3 != 0 && $i % 5 == 0) {
                array_push($rs, "Buzz");
            } else {
                array_push($rs, "FizzBuzz");
            }
        }
        return $rs;
    }
}