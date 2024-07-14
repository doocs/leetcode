<?php
class Solution {
    /**
     * @param integer[] $nums
     * @return integer
     */

    function jump($nums) {
        $maxReach = 0;
        $steps = 0;
        $lastJump = 0;
        for ($i = 0; $i <= count($nums) - 2; $i++) {
            $maxReach = max($maxReach, $i + $nums[$i]);
            if ($i == $lastJump) {
                $lastJump = $maxReach;
                $steps++;
            }
        }

        return $steps;
    }
}
