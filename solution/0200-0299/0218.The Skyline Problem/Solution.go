type Matrix struct{ left, right, height int }
type Queue []Matrix

func (q Queue) Len() int            { return len(q) }
func (q Queue) Top() Matrix         { return q[0] }
func (q Queue) Swap(i, j int)       { q[i], q[j] = q[j], q[i] }
func (q Queue) Less(i, j int) bool  { return q[i].height > q[j].height }
func (q *Queue) Push(x interface{}) { *q = append(*q, x.(Matrix)) }
func (q *Queue) Pop() interface{} {
	old, x := *q, (*q)[len(*q)-1]
	*q = old[:len(old)-1]
	return x
}

func getSkyline(buildings [][]int) [][]int {
	skys, lines, pq := make([][]int, 0), make([]int, 0), &Queue{}
	heap.Init(pq)
	for _, v := range buildings {
		lines = append(lines, v[0], v[1])
	}
	sort.Ints(lines)
	city, n := 0, len(buildings)
	for _, line := range lines {
		// 将所有符合条件的矩形加入队列
		for ; city < n && buildings[city][0] <= line && buildings[city][1] > line; city++ {
			v := Matrix{left: buildings[city][0], right: buildings[city][1], height: buildings[city][2]}
			heap.Push(pq, v)
		}
		// 从队列移除不符合条件的矩形
		for pq.Len() > 0 && pq.Top().right <= line {
			heap.Pop(pq)
		}
		high := 0
		// 队列为空说明是最右侧建筑物的终点，其轮廓点为 (line, 0)
		if pq.Len() != 0 {
			high = pq.Top().height
		}
		// 如果该点高度和前一个轮廓点一样的话，直接忽略
		if len(skys) > 0 && skys[len(skys)-1][1] == high {
			continue
		}
		skys = append(skys, []int{line, high})
	}
	return skys
}