func maxTargetNodes(edges1 [][]int, edges2 [][]int) []int {
    g1 := build(edges1)
    g2 := build(edges2)
    n, m := len(g1), len(g2)
    c1 := make([]int, n)
    c2 := make([]int, m)
    cnt1 := make([]int, 2)
    cnt2 := make([]int, 2)

    dfs(g2, 0, -1, c2, 0, cnt2)
    dfs(g1, 0, -1, c1, 0, cnt1)

    t := max(cnt2[0], cnt2[1])
    ans := make([]int, n)
    for i := 0; i < n; i++ {
        ans[i] = t + cnt1[c1[i]]
    }
    return ans
}

func build(edges [][]int) [][]int {
    n := len(edges) + 1
    g := make([][]int, n)
    for _, e := range edges {
        a, b := e[0], e[1]
        g[a] = append(g[a], b)
        g[b] = append(g[b], a)
    }
    return g
}

func dfs(g [][]int, a, fa int, c []int, d int, cnt []int) {
    c[a] = d
    cnt[d]++
    for _, b := range g[a] {
        if b != fa {
            dfs(g, b, a, c, d^1, cnt)
        }
    }
}
