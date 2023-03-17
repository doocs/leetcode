func longestNiceSubarray(nums []int) (ans int) {
	mask, j := 0, 0
	for i, x := range nums {
		for ; mask&x != 0; j++ {
			mask ^= nums[j]
		}
		if k := i - j + 1; ans < k {
			ans = k
		}
		mask |= x
	}
	return
}