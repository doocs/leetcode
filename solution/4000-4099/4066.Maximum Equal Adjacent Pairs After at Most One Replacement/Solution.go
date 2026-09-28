func maxEqualAdjacentPairs(nums []int) int {
	cnt := map[int64]int{}
	ans, mx := 0, 0

	for i := 0; i+1 < len(nums); i++ {
		x, y := nums[i], nums[i+1]
		if x == y {
			ans++
		} else {
			if x > y {
				x, y = y, x
			}
			key := int64(x)<<30 | int64(y)
			cnt[key]++
			if cnt[key] > mx {
				mx = cnt[key]
			}
		}
	}
	ans += mx
	return ans
}
