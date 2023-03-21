func subarraysWithKDistinct(nums []int, k int) (ans int) {
	f := func(k int) []int {
		n := len(nums)
		pos := make([]int, n)
		cnt := make([]int, n+1)
		s, j := 0, 0
		for i, x := range nums {
			cnt[x]++
			if cnt[x] == 1 {
				s++
			}
			for ; s > k; j++ {
				cnt[nums[j]]--
				if cnt[nums[j]] == 0 {
					s--
				}
			}
			pos[i] = j
		}
		return pos
	}
	left, right := f(k), f(k-1)
	for i := range left {
		ans += right[i] - left[i]
	}
	return
}