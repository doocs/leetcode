class Solution {
    /**
     * @param int[] $nums
     * @return int[][];
     */

    function threeSum($nums) {
        $result = [];
        $n = count($nums);

        sort($nums);
        for ($i = 0; $i < $n - 2; $i++) {

            if ($i > 0 && $nums[$i] === $nums[$i - 1]) {
                continue;
            }

            $left = $i + 1;
            $right = $n - 1;

            while ($left < $right) {
                $sum = $nums[$i] + $nums[$left] + $nums[$right];

                if ($sum === 0) {
                    $triplet = array($nums[$i], $nums[$left], $nums[$right]);
                    $result[] = $triplet;

                    while ($left < $right && $nums[$left] === $nums[$left + 1]) {
                        $left++;
                    }

                    while ($left < $right && $nums[$right] === $nums[$right - 1]) {
                        $right--;
                    }
                    
                    $left++;
                    $right--;
                } elseif ($sum < 0) {
                    $left++;
                } else {
                    $right--;
                }
            }
        }

        return $result;
    }
}
