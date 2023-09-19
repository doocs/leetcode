func seePeople(heights [][]int) (ans [][]int) {
	f := func(nums []int) []int {
		n := len(nums)
		ans := make([]int, n)
		stk := []int{}
		for i := n - 1; i >= 0; i-- {
			for len(stk) > 0 && stk[len(stk)-1] < nums[i] {
				ans[i]++
				stk = stk[:len(stk)-1]
			}
			if len(stk) > 0 {
				ans[i]++
			}
			for len(stk) > 0 && stk[len(stk)-1] == nums[i] {
				stk = stk[:len(stk)-1]
			}
			stk = append(stk, nums[i])
		}
		return ans
	}
	for _, row := range heights {
		ans = append(ans, f(row))
	}
	n := len(heights[0])
	for j := 0; j < n; j++ {
		col := make([]int, len(heights))
		for i := range heights {
			col[i] = heights[i][j]
		}
		for i, v := range f(col) {
			ans[i][j] += v
		}
	}
	return
}