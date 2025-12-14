type UnionFind struct {
    p    []int
    size []int
}

func NewUnionFind(n int) *UnionFind {
    uf := &UnionFind{
        p:    make([]int, n),
        size: make([]int, n),
    }
    for i := 0; i < n; i++ {
        uf.p[i] = i
        uf.size[i] = 1
    }
    return uf
}

func (uf *UnionFind) find(x int) int {
    if uf.p[x] != x {
        uf.p[x] = uf.find(uf.p[x])
    }
    return uf.p[x]
}

func (uf *UnionFind) union(a, b int) bool {
    pa := uf.find(a)
    pb := uf.find(b)
    if pa == pb {
        return false
    }

    if uf.size[pa] > uf.size[pb] {
        uf.p[pb] = pa
        uf.size[pa] += uf.size[pb]
    } else {
        uf.p[pa] = pb
        uf.size[pb] += uf.size[pa]
    }
    return true
}

func minTime(n int, edges [][]int, k int) int {
    sort.Slice(edges, func(i, j int) bool {
        return edges[i][2] < edges[j][2]
    })

    uf := NewUnionFind(n)
    cnt := n

    for i := len(edges) - 1; i >= 0; i-- {
        u := edges[i][0]
        v := edges[i][1]
        t := edges[i][2]

        if uf.union(u, v) {
            cnt--
            if cnt < k {
                return t
            }
        }
    }
    return 0
}
