class Solution {
    /**
     * @param Integer[] $flowerbed
     * @param Integer $n
     * @return Boolean
     */
    function canPlaceFlowers($flowerbed, $n) {
        array_push($flowerbed, 0);
        array_unshift($flowerbed, 0);
        for ($i = 1; $i < count($flowerbed) - 1; $i++) {
            if ($flowerbed[$i] === 0) {
                if ($flowerbed[$i - 1] === 0 && $flowerbed[$i + 1] === 0) {
                    $flowerbed[$i] = 1;
                    $n--;
                }
            }
        }
        return $n <= 0;
    }
}