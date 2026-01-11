func maximumAND(nums []int, k int, m int) int {
	mx := bits.Len(uint(slices.Max(nums) + k))

	ans := 0
	cost := make([]int, len(nums))

	for bit := mx - 1; bit >= 0; bit-- {
		target := ans | (1 << bit)
		for i, x := range nums {
			j := bits.Len(uint(target & ^x))
			mask := (1 << j) - 1
			cost[i] = (target & mask) - (x & mask)
		}
		sort.Ints(cost)
		sum := 0
		for i := 0; i < m; i++ {
			sum += cost[i]
		}
		if sum <= k {
			ans = target
		}
	}

	return ans
}
