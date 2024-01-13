func subsetXORSum(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < 1<<n; i++ {
		s := 0
		for j, x := range nums {
			if i>>j&1 == 1 {
				s ^= x
			}
		}
		ans += s
	}
	return
}