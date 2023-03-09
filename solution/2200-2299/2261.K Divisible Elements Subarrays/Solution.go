func countDistinct(nums []int, k int, p int) int {
	s := map[string]struct{}{}
	for i := range nums {
		cnt, t := 0, ""
		for _, x := range nums[i:] {
			if x%p == 0 {
				cnt++
				if cnt > k {
					break
				}
			}
			t += string(x) + ","
			s[t] = struct{}{}
		}
	}
	return len(s)
}