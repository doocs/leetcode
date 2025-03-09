func findMaxSum(nums1 []int, nums2 []int, k int) []int64 {
	n := len(nums1)
	arr := make([][2]int, n)
	for i, x := range nums1 {
		arr[i] = [2]int{x, i}
	}
	ans := make([]int64, n)
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] })
	pq := hp{}
	var s int64
	j := 0
	for h, e := range arr {
		x, i := e[0], e[1]
		for j < h && arr[j][0] < x {
			y := nums2[arr[j][1]]
			heap.Push(&pq, y)
			s += int64(y)
			if pq.Len() > k {
				s -= int64(heap.Pop(&pq).(int))
			}
			j++
		}
		ans[i] = s
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
