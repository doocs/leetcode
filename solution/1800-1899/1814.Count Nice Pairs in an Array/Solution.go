func countNicePairs(nums []int) (ans int) {
	const mod int = 1e9 + 7
	rev := func(x int) (y int) {
		for x > 0 {
			y = y*10 + x%10
			x /= 10
		}
		return
	}
	cnt := map[int]int{}
	for _, x := range nums {
		y := x - rev(x)
		cnt[y]++
	}
	for _, v := range cnt {
		ans = (ans + v*(v-1)/2) % mod
	}
	return
}