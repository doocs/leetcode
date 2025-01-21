class Solution {
    function mostFrequent($nums, $key) {
        $cnt = array_fill(0, max($nums) + 1, 0);
        $ans = 0;
        $mx = 0;
        for ($i = 0; $i < count($nums) - 1; ++$i) {
            if ($nums[$i] === $key) {
                $cnt[$nums[$i + 1]]++;
                if ($mx < $cnt[$nums[$i + 1]]) {
                    $mx = $cnt[$nums[$i + 1]];
                    $ans = $nums[$i + 1];
                }
            }
        }
        return $ans;
    }
}
