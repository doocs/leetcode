func countDominantElements(nums []int) int {
	ans := 0
	for i, x := range nums {
		s, cnt := 0, 0
		if i > 0 {
			s += nums[i-1]
			cnt++
		}
		if i < len(nums)-1 {
			s += nums[i+1]
			cnt++
		}
		if cnt == 0 || x*cnt >= s {
			ans++
		}
	}
	return ans
}
