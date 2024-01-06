func judgePoint24(cards []int) bool {
	ops := [4]rune{'+', '-', '*', '/'}
	nums := make([]float64, len(cards))
	for i, num := range cards {
		nums[i] = float64(num)
	}
	var dfs func([]float64) bool
	dfs = func(nums []float64) bool {
		n := len(nums)
		if n == 1 {
			return math.Abs(nums[0]-24) < 1e-6
		}
		ok := false
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if i != j {
					var nxt []float64
					for k := 0; k < n; k++ {
						if k != i && k != j {
							nxt = append(nxt, nums[k])
						}
					}
					for _, op := range ops {
						switch op {
						case '/':
							if nums[j] == 0 {
								continue
							}
							nxt = append(nxt, nums[i]/nums[j])
						case '*':
							nxt = append(nxt, nums[i]*nums[j])
						case '+':
							nxt = append(nxt, nums[i]+nums[j])
						case '-':
							nxt = append(nxt, nums[i]-nums[j])
						}
						ok = ok || dfs(nxt)
						if ok {
							return true
						}
						nxt = nxt[:len(nxt)-1]
					}
				}
			}
		}
		return ok
	}

	return dfs(nums)
}