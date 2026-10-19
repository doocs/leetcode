func singleNumber(nums []int) (ans int) {
	cnt := [32]int{}
	for _, x := range nums {
		for i := range cnt {
			cnt[i] += x & 1
			x >>= 1
		}
	}
	for i := range cnt {
		if cnt[i]%3 == 1 {
			if i == 31 {
				ans -= 1 << i
			} else {
				ans |= 1 << i
			}
		}
	}
	return
}
