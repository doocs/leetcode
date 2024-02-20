class Solution
{
    /**
     * @param int[] $height
     * @return int
     */

    function maxArea($height)
    {
        $left = 0;
        $right = count($height) - 1;
        $maxArea = 0;

        while ($left < $right) {

            $area = min($height[$left], $height[$right]) * ($right - $left);

            $maxArea = max($maxArea, $area);

            if ($height[$left] < $height[$right]) {
                $left++;
            } else {
                $right--;
            }
        }

        return $maxArea;
    }
}
