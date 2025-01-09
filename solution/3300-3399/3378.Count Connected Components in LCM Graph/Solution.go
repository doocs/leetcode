type DSU struct {
	parent map[int]int
	rank   map[int]int
}

func NewDSU(n int) *DSU {
	dsu := &DSU{
		parent: make(map[int]int),
		rank:   make(map[int]int),
	}
	for i := 0; i <= n; i++ {
		dsu.parent[i] = i
		dsu.rank[i] = 0
	}
	return dsu
}

func (dsu *DSU) Find(x int) int {
	if dsu.parent[x] != x {
		dsu.parent[x] = dsu.Find(dsu.parent[x])
	}
	return dsu.parent[x]
}

func (dsu *DSU) Union(u, v int) {
	uRoot := dsu.Find(u)
	vRoot := dsu.Find(v)
	if uRoot != vRoot {
		if dsu.rank[uRoot] < dsu.rank[vRoot] {
			uRoot, vRoot = vRoot, uRoot
		}
		dsu.parent[vRoot] = uRoot
		if dsu.rank[uRoot] == dsu.rank[vRoot] {
			dsu.rank[uRoot]++
		}
	}
}

func countComponents(nums []int, threshold int) int {
	dsu := NewDSU(threshold)

	for _, num := range nums {
		for j := num; j <= threshold; j += num {
			dsu.Union(num, j)
		}
	}

	uniqueParents := make(map[int]struct{})
	for _, num := range nums {
		if num > threshold {
			uniqueParents[num] = struct{}{}
		} else {
			uniqueParents[dsu.Find(num)] = struct{}{}
		}
	}

	return len(uniqueParents)
}
