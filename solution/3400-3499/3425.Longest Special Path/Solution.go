func longestSpecialPath(edges [][]int, nums []int) []int {
    n := len(nums)

    type Edge struct {
        to, weight int
    }

    type Frame struct {
        node, parent, left, depth, weight, old int
        exit bool
    }

    graph := make([][]Edge, n)

    for _, e := range edges {
        u, v, w := e[0], e[1], e[2]
        graph[u] = append(graph[u], Edge{v, w})
        graph[v] = append(graph[v], Edge{u, w})
    }

    last := make(map[int]int)
    prefix := make([]int64, n+1)

    stack := []Frame{
        {node: 0, parent: -1},
    }

    var bestLength int64
    bestNodes := 1

    for len(stack) > 0 {
        f := stack[len(stack)-1]
        stack = stack[:len(stack)-1]

        u := f.node

        if f.exit {
            if f.old == 0 {
                delete(last, nums[u])
            } else {
                last[nums[u]] = f.old
            }
            continue
        }

        prefix[f.depth+1] = prefix[f.depth] + int64(f.weight)

        old := last[nums[u]]
        left := f.left

        if old > left {
            left = old
        }

        last[nums[u]] = f.depth + 1

        length := prefix[f.depth+1] - prefix[left+1]
        nodes := f.depth + 1 - left

        if length > bestLength ||
            (length == bestLength && nodes < bestNodes) {
            bestLength = length
            bestNodes = nodes
        }

        stack = append(stack, Frame{
            node: u,
            parent: f.parent,
            old: old,
            exit: true,
        })

        for i := len(graph[u]) - 1; i >= 0; i-- {
            e := graph[u][i]

            if e.to != f.parent {
                stack = append(stack, Frame{
                    node: e.to,
                    parent: u,
                    left: left,
                    depth: f.depth + 1,
                    weight: e.weight,
                })
            }
        }
    }

    return []int{int(bestLength), bestNodes}
}
