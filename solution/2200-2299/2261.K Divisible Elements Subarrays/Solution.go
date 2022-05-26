func countDistinct(nums []int, k int, p int) int {
	s := map[string]bool{}
	for i, n := 0, len(nums); i < n; i++ {
		cnt := 0
		t := ""
		for j := i; j < n; j++ {
			if nums[j]%p == 0 {
				cnt++
			}
			if cnt > k {
				break
			}
			t += string(nums[j]) + ","
			s[t] = true
		}
	}
	return len(s)
}