func minimumDifference(nums []int) int64 {
	m := len(nums)
	n := m / 3
	s := 0
	pre := make([]int, m+1)
	q1 := hp{}
	for i := 1; i <= n*2; i++ {
		x := nums[i-1]
		s += x
		heap.Push(&q1, -x)
		if q1.Len() > n {
			s -= -heap.Pop(&q1).(int)
		}
		pre[i] = s
	}
	s = 0
	suf := make([]int, m+1)
	q2 := hp{}
	for i := m; i > n; i-- {
		x := nums[i-1]
		s += x
		heap.Push(&q2, x)
		if q2.Len() > n {
			s -= heap.Pop(&q2).(int)
		}
		suf[i] = s
	}
	ans := int64(1e18)
	for i := n; i <= n*2; i++ {
		ans = min(ans, int64(pre[i]-suf[i+1]))
	}
	return ans
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}