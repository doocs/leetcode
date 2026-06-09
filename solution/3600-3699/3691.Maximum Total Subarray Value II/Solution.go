type SparseTableRMQ struct {
	n      int
	maxLog int
	fMax   [][]int
	fMin   [][]int
	lg     []int
}

func NewSparseTableRMQ(data []int) *SparseTableRMQ {
	n := len(data)
	maxLog := bits.Len(uint(n)) + 1
	fMax := make([][]int, n)
	fMin := make([][]int, n)
	for i := range fMax {
		fMax[i] = make([]int, maxLog)
		fMin[i] = make([]int, maxLog)
	}
	lg := make([]int, n+1)

	for i := 2; i <= n; i++ {
		lg[i] = lg[i>>1] + 1
	}

	for i := 0; i < n; i++ {
		fMax[i][0] = data[i]
		fMin[i][0] = data[i]
	}

	for j := 1; j < maxLog; j++ {
		for i := 0; i <= n-(1<<j); i++ {
			fMax[i][j] = max(fMax[i][j-1], fMax[i+(1<<(j-1))][j-1])
			fMin[i][j] = min(fMin[i][j-1], fMin[i+(1<<(j-1))][j-1])
		}
	}

	return &SparseTableRMQ{n: n, maxLog: maxLog, fMax: fMax, fMin: fMin, lg: lg}
}

func (st *SparseTableRMQ) queryMax(l, r int) int {
	k := st.lg[r-l+1]
	return max(st.fMax[l][k], st.fMax[r-(1<<k)+1][k])
}

func (st *SparseTableRMQ) queryMin(l, r int) int {
	k := st.lg[r-l+1]
	return min(st.fMin[l][k], st.fMin[r-(1<<k)+1][k])
}

type Item struct {
	val  int64
	l, r int
}
type PriorityQueue []*Item

func (pq PriorityQueue) Len() int           { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool { return pq[i].val > pq[j].val }
func (pq PriorityQueue) Swap(i, j int)      { pq[i], pq[j] = pq[j], pq[i] }
func (pq *PriorityQueue) Push(x any)        { *pq = append(*pq, x.(*Item)) }
func (pq *PriorityQueue) Pop() any {
	old := *pq
	n := len(old)
	item := old[n-1]
	*pq = old[0 : n-1]
	return item
}

func maxTotalValue(nums []int, k int) int64 {
	n := len(nums)
	st := NewSparseTableRMQ(nums)
	pq := &PriorityQueue{}
	heap.Init(pq)

	for l := 0; l < n; l++ {
		val := int64(st.queryMax(l, n-1) - st.queryMin(l, n-1))
		heap.Push(pq, &Item{val: val, l: l, r: n - 1})
	}

	var ans int64 = 0
	for i := 0; i < k; i++ {
		curr := heap.Pop(pq).(*Item)
		ans += curr.val
		if curr.r > curr.l {
			nextVal := int64(st.queryMax(curr.l, curr.r-1) - st.queryMin(curr.l, curr.r-1))
			heap.Push(pq, &Item{val: nextVal, l: curr.l, r: curr.r - 1})
		}
	}
	return ans
}