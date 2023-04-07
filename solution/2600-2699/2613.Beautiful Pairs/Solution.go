func beautifulPair(nums1 []int, nums2 []int) []int {
	n := len(nums1)
	pl := map[[2]int][]int{}
	for i := 0; i < n; i++ {
		k := [2]int{nums1[i], nums2[i]}
		pl[k] = append(pl[k], i)
	}
	points := [][3]int{}
	for i := 0; i < n; i++ {
		k := [2]int{nums2[i], nums1[i]}
		if len(pl[k]) > 1 {
			return []int{pl[k][0], pl[k][1]}
		}
		points = append(points, [3]int{nums1[i], nums2[i], i})
	}
	sort.Slice(points, func(i, j int) bool { return points[i][0] < points[j][0] })

	var dfs func(l, r int) [3]int
	dfs = func(l, r int) [3]int {
		if l >= r {
			return [3]int{1 << 30, -1, -1}
		}
		m := (l + r) >> 1
		x := points[m][0]
		t1 := dfs(l, m)
		t2 := dfs(m+1, r)
		if t1[0] > t2[0] || (t1[0] == t2[0] && (t1[1] > t2[1] || (t1[1] == t2[1] && t1[2] > t2[2]))) {
			t1 = t2
		}
		t := [][3]int{}
		for i := l; i <= r; i++ {
			if abs(points[i][0]-x) <= t1[0] {
				t = append(t, points[i])
			}
		}
		sort.Slice(t, func(i, j int) bool { return t[i][1] < t[j][1] })
		for i := 0; i < len(t); i++ {
			for j := i + 1; j < len(t); j++ {
				if t[j][1]-t[i][1] > t1[0] {
					break
				}
				pi := min(t[i][2], t[j][2])
				pj := max(t[i][2], t[j][2])
				d := dist(t[i][0], t[i][1], t[j][0], t[j][1])
				if d < t1[0] || (d == t1[0] && (pi < t1[1] || (pi == t1[1] && pj < t1[2]))) {
					t1 = [3]int{d, pi, pj}
				}
			}
		}
		return t1
	}
	ans := dfs(0, n-1)
	return []int{ans[1], ans[2]}
}

func dist(x1, y1, x2, y2 int) int {
	return abs(x1-x2) + abs(y1-y2)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}