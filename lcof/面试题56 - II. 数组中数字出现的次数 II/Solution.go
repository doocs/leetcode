func singleNumber(nums []int) (ans int) {
	cnt := [32]int{}
	for _, x := range nums {
		for i := range cnt {
			cnt[i] += x & 1
			x >>= 1
		}
	}
	for i, v := range cnt {
		if v%3 == 1 {
			ans |= 1 << i
		}
	}
	return
}