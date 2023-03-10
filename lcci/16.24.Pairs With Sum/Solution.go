func pairSums(nums []int, target int) (ans [][]int) {
	cnt := map[int]int{}
	for _, x := range nums {
		y := target - x
		if cnt[y] > 0 {
			cnt[y]--
			ans = append(ans, []int{x, y})
		} else {
			cnt[x]++
		}
	}
	return
}