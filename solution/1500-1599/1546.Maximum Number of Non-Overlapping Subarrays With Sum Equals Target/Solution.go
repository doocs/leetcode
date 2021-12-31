func maxNonOverlapping(nums []int, target int) int {
	i, n, ans := 0, len(nums), 0
	for i < n {
		s := 0
		seen := map[int]bool{0: true}
		for i < n {
			s += nums[i]
			if seen[s-target] {
				ans++
				break
			}
			seen[s] = true
			i++
		}
		i++
	}
	return ans
}