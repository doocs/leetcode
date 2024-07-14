class Solution {
    /**
     * @param integer $n
     * @return string
     */

    function countAndSay($n) {
        if ($n <= 0) {
            return "";
        }

        $result = "1";
        for ($i = 2; $i <= $n; $i++) {
            $count = 1;
            $say = "";
            for ($j = 1; $j < strlen($result); $j++) {
                if ($result[$j] == $result[$j - 1]) {
                    $count++;
                } else {
                    $say .= $count . $result[$j - 1];
                    $count = 1;
                }
            }
            $say .= $count . $result[strlen($result) - 1];
            $result = $say;
        }
        return $result;
    }
}
