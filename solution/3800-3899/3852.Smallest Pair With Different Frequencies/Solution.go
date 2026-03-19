func minDistinctFreqPair(nums []int) []int {
	const inf = math.MaxInt
	cnt := make(map[int]int)

	for _, v := range nums {
		cnt[v]++
	}

	x := slices.Min(nums)

	minY := inf
	for y := range cnt {
		if y < minY && cnt[x] != cnt[y] {
			minY = y
		}
	}

	if minY == inf {
		return []int{-1, -1}
	}
	return []int{x, minY}
}
