func getMaxMatrix(matrix [][]int) []int {
	m, n := len(matrix), len(matrix[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			s[i+1][j] = s[i][j] + matrix[i][j]
		}
	}
	mx := matrix[0][0]
	ans := make([]int, 4)
	for i1 := 0; i1 < m; i1++ {
		for i2 := i1; i2 < m; i2++ {
			var nums []int
			for j := 0; j < n; j++ {
				nums = append(nums, s[i2+1][j]-s[i1][j])
			}
			start := 0
			f := nums[0]
			for j := 1; j < n; j++ {
				if f > 0 {
					f += nums[j]
				} else {
					f = nums[j]
					start = j
				}
				if f > mx {
					mx = f
					ans = []int{i1, start, i2, j}
				}
			}
		}
	}
	return ans
}