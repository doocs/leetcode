func minBitwiseArray(nums []int) (ans []int) {
	for _, x := range nums {
		if x == 2 {
			ans = append(ans, -1)
		} else {
			for i := 1; i < 32; i++ {
				if x>>i&1 == 0 {
					ans = append(ans, x^1<<(i-1))
					break
				}
			}
		}
	}
	return
}
