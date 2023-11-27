func maximizeSquareHoleArea(n int, m int, hBars []int, vBars []int) int {
	f := func(nums []int) int {
		sort.Ints(nums)
		ans, cnt := 1, 1
		for i, x := range nums[1:] {
			if x == nums[i]+1 {
				cnt++
				ans = max(ans, cnt)
			} else {
				cnt = 1
			}
		}
		return ans + 1
	}
	x := min(f(hBars), f(vBars))
	return x * x
}