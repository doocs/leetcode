type frac struct{ x, y, i, j int }
type hp []frac

func (a hp) Len() int            { return len(a) }
func (a hp) Swap(i, j int)       { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool  { return a[i].x*a[j].y < a[j].x*a[i].y }
func (a *hp) Push(x interface{}) { *a = append(*a, x.(frac)) }
func (a *hp) Pop() interface{}   { l := len(*a); tmp := (*a)[l-1]; *a = (*a)[:l-1]; return tmp }

func kthSmallestPrimeFraction(arr []int, k int) []int {
	n := len(arr)
	h := make(hp, 0, n-1)
	for i := 1; i < n; i++ {
		h = append(h, frac{1, arr[i], 0, i})
	}
	heap.Init(&h)
	for i := 1; i < k; i++ {
		f := heap.Pop(&h).(frac)
		if f.i+1 < f.j {
			heap.Push(&h, frac{arr[f.i+1], arr[f.j], f.i + 1, f.j})
		}
	}
	return []int{h[0].x, h[0].y}
}
