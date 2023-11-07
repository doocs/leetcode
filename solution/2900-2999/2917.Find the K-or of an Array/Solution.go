func findKOr(nums []int, k int) (ans int) {
	for i := 0; i < 32; i++ {
		cnt := 0
		for _, x := range nums {
			cnt += (x >> i & 1)
		}
		if cnt >= k {
			ans |= 1 << i
		}
	}
	return
}