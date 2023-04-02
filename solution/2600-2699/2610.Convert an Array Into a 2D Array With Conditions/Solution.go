func findMatrix(nums []int) (ans [][]int) {
	n := len(nums)
	cnt := make([]int, n+1)
	for _, x := range nums {
		cnt[x]++
	}
	for x, v := range cnt {
		for j := 0; j < v; j++ {
			if len(ans) <= j {
				ans = append(ans, []int{})
			}
			ans[j] = append(ans[j], x)
		}
	}
	return
}