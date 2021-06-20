func minDifference(nums []int, queries [][]int) []int {
	m, n := len(nums), len(queries)
	preSum := make([][101]int, m+1)
	for i := 1; i <= m; i++ {
		for j := 1; j <= 100; j++ {
			t := 0
			if nums[i-1] == j {
				t = 1
			}
			preSum[i][j] = preSum[i-1][j] + t
		}
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		left, right := queries[i][0], queries[i][1]+1
		t, last := 101, -1
		for j := 1; j <= 100; j++ {
			if preSum[right][j]-preSum[left][j] > 0 {
				if last != -1 {
					if t > j-last {
						t = j - last
					}
				}
				last = j
			}
		}
		if t == 101 {
			t = -1
		}
		ans[i] = t
	}
	return ans
}