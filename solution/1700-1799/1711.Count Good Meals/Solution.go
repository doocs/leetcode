const mod int = 1e9 + 7

func countPairs(deliciousness []int) int {
	limit := 0
	for _, d := range deliciousness {
		limit = max(limit, d)
	}
	limit *= 2
	pairs := 0
	freq := make(map[int]int)
	for _, d := range deliciousness {
		for sum := 1; sum <= limit; sum <<= 1 {
			pairs = (pairs + freq[sum-d]) % mod
		}
		freq[d]++
	}
	return pairs
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
