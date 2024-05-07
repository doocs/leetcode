func maxStrength(nums []int) int64 {
	ans := int64(-1e14)
	for i := 1; i < 1<<len(nums); i++ {
		var t int64 = 1
		for j, x := range nums {
			if i>>j&1 == 1 {
				t *= int64(x)
			}
		}
		ans = max(ans, t)
	}
	return ans
}