import "container/heap"

func minTime(n int, edges [][]int) int {
    graph := make([][][3]int, n)
    for _, edge := range edges {
        u, v, start, end := edge[0], edge[1], edge[2], edge[3]
        graph[u] = append(graph[u], [3]int{v, start, end})
    }

    dist := make([]int, n)
    for i := range dist {
        dist[i] = -1
    }
    dist[0] = 0

    pq := &PriorityQueue{}
    heap.Init(pq)
    heap.Push(pq, &Item{value: 0, priority: 0})

    for pq.Len() > 0 {
        item := heap.Pop(pq).(*Item)
        u := item.value
        d := item.priority

        if d > dist[u] && dist[u] != -1{
            continue
        }


        if u == n-1{
            continue
        }


        for _, edge := range graph[u] {
            v, start, end := edge[0], edge[1], edge[2]

            wait := 0
            if d < start {
                wait = start - d
            }

            if d + wait <= end {
                newDist := d + wait + 1
                if dist[v] == -1 || newDist < dist[v] {
                    dist[v] = newDist
                    heap.Push(pq, &Item{value: v, priority: newDist})
                }
            }
        }
    }

    return dist[n-1]
}

type Item struct {
	value    int // The value of the item; arbitrary.
	priority int // The priority of the item in the queue.
	// The index is needed to update during heap operations. It is
	// maintained by the heap.Interface methods.
	index int // The index of the item in the heap.
}

// A PriorityQueue implements heap.Interface and holds Items.
type PriorityQueue []*Item

func (pq PriorityQueue) Len() int { return len(pq) }

func (pq PriorityQueue) Less(i, j int) bool {
	// We want Pop to give us the lowest, not highest, priority so we use less than here.
	return pq[i].priority < pq[j].priority
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}

func (pq *PriorityQueue) Push(x any) {
	n := len(*pq)
	item := x.(*Item)
	item.index = n
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() any {
	old := *pq
	n := len(old)
	item := old[n-1]
	old[n-1] = nil  // avoid memory leak
	item.index = -1 // for safety
	*pq = old[0 : n-1]
	return item
}
