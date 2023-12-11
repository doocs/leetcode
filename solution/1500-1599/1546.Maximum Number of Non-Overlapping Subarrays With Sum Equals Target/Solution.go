func maxNonOverlapping(nums []int, target int) (ans int) {
	n := len(nums)
	for i := 0; i < n; i++ {
		s := 0
		vis := map[int]bool{0: true}
		for ; i < n; i++ {
			s += nums[i]
			if vis[s-target] {
				ans++
				break
			}
			vis[s] = true
		}
	}
	return
}