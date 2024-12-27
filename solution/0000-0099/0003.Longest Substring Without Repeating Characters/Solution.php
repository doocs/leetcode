class Solution {
    function lengthOfLongestSubstring($s) {
        $n = strlen($s);
        $ans = 0;
        $cnt = array_fill(0, 128, 0);
        $l = 0;
        for ($r = 0; $r < $n; ++$r) {
            $cnt[ord($s[$r])]++;
            while ($cnt[ord($s[$r])] > 1) {
                $cnt[ord($s[$l])]--;
                $l++;
            }
            $ans = max($ans, $r - $l + 1);
        }
        return $ans;
    }
}
