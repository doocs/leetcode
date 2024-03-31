func minimumSubarrayLength(nums []int, k int) int {
	n := len(nums)
	cnt := [32]int{}
	ans := n + 1
	s, i := 0, 0
	for j, x := range nums {
		s |= x
		for h := 0; h < 32; h++ {
			if x>>h&1 == 1 {
				cnt[h]++
			}
		}
		for ; s >= k && i <= j; i++ {
			ans = min(ans, j-i+1)
			for h := 0; h < 32; h++ {
				if nums[i]>>h&1 == 1 {
					cnt[h]--
					if cnt[h] == 0 {
						s ^= 1 << h
					}
				}
			}
		}
	}
	if ans == n+1 {
		return -1
	}
	return ans
}