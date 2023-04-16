const inf = 1 << 29

type Graph struct {
	g [][]int
}

func Constructor(n int, edges [][]int) Graph {
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, e := range edges {
		f, t, c := e[0], e[1], e[2]
		g[f][t] = c
	}
	return Graph{g}
}

func (this *Graph) AddEdge(edge []int) {
	f, t, c := edge[0], edge[1], edge[2]
	this.g[f][t] = c
}

func (this *Graph) ShortestPath(node1 int, node2 int) int {
	n := len(this.g)
	dist := make([]int, n)
	for i := range dist {
		dist[i] = inf
	}
	vis := make([]bool, n)
	dist[node1] = 0
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], dist[t]+this.g[t][j])
		}
	}
	if dist[node2] >= inf {
		return -1
	}
	return dist[node2]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your Graph object will be instantiated and called as such:
 * obj := Constructor(n, edges);
 * obj.AddEdge(edge);
 * param_2 := obj.ShortestPath(node1,node2);
 */