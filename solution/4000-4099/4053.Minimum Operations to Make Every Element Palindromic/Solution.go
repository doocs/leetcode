var ps [2][]int64

func init() {
	for i := int64(1); i <= 100000; i++ {
		s := strconv.FormatInt(i, 10)
		t1 := reverse(s)
		t2 := reverse(s[:len(s)-1])
		x, _ := strconv.ParseInt(s+t1, 10, 64)
		ps[x&1] = append(ps[x&1], x)
		y, _ := strconv.ParseInt(s+t2, 10, 64)
		ps[y&1] = append(ps[y&1], y)
	}
	sort.Slice(ps[0], func(a, b int) bool { return ps[0][a] < ps[0][b] })
	sort.Slice(ps[1], func(a, b int) bool { return ps[1][a] < ps[1][b] })
}

func reverse(s string) string {
	b := []byte(s)
	for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
	return string(b)
}

func minOperations(nums []int) int64 {
	var ans int64
	for _, x := range nums {
		p := ps[x&1]
		i := sort.Search(len(p), func(i int) bool { return p[i] >= int64(x) })
		t := int64(1 << 62)
		if i < len(p) {
			t = p[i] - int64(x)
		}
		if i > 0 {
			t = min(t, int64(x)-p[i-1])
		}
		ans += t / 2
	}
	return ans
}
