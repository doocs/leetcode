func maximumWeight(intervals [][]int) []int {
	n := len(intervals)
	arr := make([][4]int, n)
	for i, e := range intervals {
		arr[i] = [4]int{e[0], e[1], e[2], i}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] != arr[j][0] {
			return arr[i][0] < arr[j][0]
		}
		return arr[i][1] < arr[j][1]
	})
	nxt := make([]int, n)
	for i := 0; i < n; i++ {
		l, r := i+1, n
		for l < r {
			mid := (l + r) >> 1
			if arr[mid][0] > arr[i][1] {
				r = mid
			} else {
				l = mid + 1
			}
		}
		nxt[i] = l
	}
	f := make([][5]int64, n+1)
	g := make([][5][]int, n+1)
	for i := n - 1; i >= 0; i-- {
		for k := 1; k < 5; k++ {
			s1, a1 := f[i+1][k], g[i+1][k]
			a2 := append([]int(nil), g[nxt[i]][k-1]...)
			x := arr[i][3]
			j := sort.SearchInts(a2, x)
			a2 = append(a2, 0)
			copy(a2[j+1:], a2[j:])
			a2[j] = x
			s2 := f[nxt[i]][k-1] + int64(arr[i][2])
			if s2 > s1 || (s2 == s1 && lessInts(a2, a1)) {
				f[i][k] = s2
				g[i][k] = a2
			} else {
				f[i][k] = s1
				g[i][k] = a1
			}
		}
	}
	return g[0][4]
}

func lessInts(a, b []int) bool {
	m := min(len(a), len(b))
	for i := 0; i < m; i++ {
		if a[i] != b[i] {
			return a[i] < b[i]
		}
	}
	return len(a) < len(b)
}
