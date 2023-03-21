func waysToPartition(nums []int, k int) (ans int) {
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	right := map[int]int{}
	for i := range nums[:n-1] {
		right[s[i]]++
		s[i+1] = s[i] + nums[i+1]
	}
	if s[n-1]%2 == 0 {
		ans = right[s[n-1]/2]
	}
	left := map[int]int{}
	for i, x := range nums {
		d := k - x
		if (s[n-1]+d)%2 == 0 {
			t := left[(s[n-1]+d)/2] + right[(s[n-1]-d)/2]
			if ans < t {
				ans = t
			}
		}
		left[s[i]]++
		right[s[i]]--
	}
	return
}