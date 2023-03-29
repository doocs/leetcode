func canDistribute(nums []int, quantity []int) bool {
	m := len(quantity)
	s := make([]int, 1<<m)
	for i := 1; i < 1<<m; i++ {
		for j := 0; j < m; j++ {
			if i>>j&1 == 1 {
				s[i] = s[i^(1<<j)] + quantity[j]
				break
			}
		}
	}
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	n := len(cnt)
	arr := make([]int, 0, n)
	for _, x := range cnt {
		arr = append(arr, x)
	}
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, 1<<m)
		f[i][0] = true
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 1<<m; j++ {
			if i > 0 && f[i-1][j] {
				f[i][j] = true
				continue
			}
			for k := j; k > 0; k = (k - 1) & j {
				ok1 := (i == 0 && j == k) || (i > 0 && f[i-1][j-k])
				ok2 := s[k] <= arr[i]
				if ok1 && ok2 {
					f[i][j] = true
					break
				}
			}
		}
	}
	return f[n-1][(1<<m)-1]
}