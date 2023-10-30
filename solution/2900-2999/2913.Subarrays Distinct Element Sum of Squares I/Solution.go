func sumCounts(nums []int) (ans int) {
	for i := range nums {
		s := [101]int{}
		cnt := 0
		for _, x := range nums[i:] {
			s[x]++
			if s[x] == 1 {
				cnt++
			}
			ans += cnt * cnt
		}
	}
	return
}