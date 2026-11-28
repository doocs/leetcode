class Solution {
    function maximumSaleItems($items, $budget) {
        $n = count($items);
        $freq = array_fill(0, $n + 1, 0);
        $minPrice = PHP_INT_MAX;

        foreach ($items as $item) {
            $freq[$item[0]]++;
            if ($item[1] < $minPrice) {
                $minPrice = $item[1];
            }
        }

        $divCount = array_fill(0, $n + 1, 0);
        for ($f = 1; $f <= $n; $f++) {
            if ($freq[$f] === 0) {
                continue;
            }
            for ($m = $f; $m <= $n; $m += $f) {
                $divCount[$f] += $freq[$m];
            }
        }

        $sortedItems = [];
        foreach ($items as $item) {
            $sortedItems[] = [$item[1], $divCount[$item[0]] - 1];
        }

        usort($sortedItems, fn($a, $b) => $a[0] <=> $b[0]);

        $rem = $budget;
        $ans = 0;
        $limit = 2 * $minPrice;

        foreach ($sortedItems as [$price, $gain]) {
            if ($price >= $limit) {
                break;
            }
            if ($gain <= 0) {
                continue;
            }

            $canBuy = min($gain, intdiv($rem, $price));
            $ans += $canBuy * 2;
            $rem -= $canBuy * $price;

            if ($canBuy < $gain) {
                break;
            }
        }

        $ans += intdiv($rem, $minPrice);
        return $ans;
    }
}