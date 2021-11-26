type pairHeap [][]int

func (a pairHeap) Len() int            { return len(a) }
func (a pairHeap) Swap(i, j int)       { a[i], a[j] = a[j], a[i] }
func (a pairHeap) Less(i, j int) bool  { return a[i][0]+a[i][1] > a[j][0]+a[j][1] }
func (a *pairHeap) Push(x interface{}) { *a = append(*a, x.([]int)) }
func (a *pairHeap) Pop() interface{}   { l := len(*a); tmp := (*a)[l-1]; *a = (*a)[:l-1]; return tmp }

func kSmallestPairs(nums1 []int, nums2 []int, k int) [][]int {
	var hp pairHeap
	for _, x := range nums1[:min(k, len(nums1))] {
		for _, y := range nums2[:min(k, len(nums2))] {
			heap.Push(&hp, []int{x, y})
			if len(hp) > k {
				heap.Pop(&hp)
			}
		}
	}
	return hp
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
