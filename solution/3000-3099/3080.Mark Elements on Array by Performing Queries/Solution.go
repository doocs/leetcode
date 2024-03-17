func unmarkedSumArray(nums []int, queries [][]int) []int64 {
	n := len(nums)
	var s int64
	for _, x := range nums {
		s += int64(x)
	}
	mark := make([]bool, n)
	arr := make([][2]int, 0, n)
	for i, x := range nums {
		arr = append(arr, [2]int{x, i})
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] == arr[j][0] {
			return arr[i][1] < arr[j][1]
		}
		return arr[i][0] < arr[j][0]
	})
	ans := make([]int64, len(queries))
	j := 0
	for i, q := range queries {
		index, k := q[0], q[1]
		if !mark[index] {
			mark[index] = true
			s -= int64(nums[index])
		}
		for ; k > 0 && j < n; j++ {
			if !mark[arr[j][1]] {
				mark[arr[j][1]] = true
				s -= int64(arr[j][0])
				k--
			}
		}
		ans[i] = s
	}
	return ans
}