type Node struct {
	b int
	i int
}

type MaxHeap []Node

func (h MaxHeap) Len() int { return len(h) }
func (h MaxHeap) Less(i, j int) bool {
	if h[i].b != h[j].b {
		return h[i].b > h[j].b
	}
	return h[i].i > h[j].i
}
func (h MaxHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x interface{}) {
	*h = append(*h, x.(Node))
}
func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

func maxCapacity(costs []int, capacity []int, budget int) int {
	arr := make([][2]int, 0)
	for k := 0; k < len(costs); k++ {
		a, b := costs[k], capacity[k]
		if a < budget {
			arr = append(arr, [2]int{a, b})
		}
	}
	if len(arr) == 0 {
		return 0
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] != arr[j][0] {
			return arr[i][0] < arr[j][0]
		}
		return arr[i][1] < arr[j][1]
	})
	alive := make([]bool, len(arr))
	h := &MaxHeap{}
	for i := 0; i < len(arr); i++ {
		alive[i] = true
		heap.Push(h, Node{arr[i][1], i})
	}
	i, j := 0, len(arr)-1
	for h.Len() > 0 && !alive[(*h)[0].i] {
		heap.Pop(h)
	}
	ans := (*h)[0].b
	for i < j {
		alive[i] = false
		for i < j && arr[i][0]+arr[j][0] >= budget {
			alive[j] = false
			j--
		}
		for h.Len() > 0 && !alive[(*h)[0].i] {
			heap.Pop(h)
		}
		if h.Len() > 0 {
			if arr[i][1]+(*h)[0].b > ans {
				ans = arr[i][1] + (*h)[0].b
			}
		}
		i++
	}
	return ans
}
