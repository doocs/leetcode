func levelOrder(root *TreeNode) [][]int {
    if root == nil {
        return nil
    }
    res := [][]int{}
    queue := []*TreeNode{}
    queue = append(queue,root)
    for len(queue) != 0 {
        size := len(queue)
        ans := []int{}
        //利用一个变量记录每层大小
        for size > 0 {
            cur := queue[0]
            ans = append(ans, cur.Val)
            queue = queue[1:]
            size--
            if cur.Left != nil {
                queue = append(queue, cur.Left)
            }
            if cur.Right != nil {
                queue = append(queue, cur.Right)
            }
        }
        res = append(res, ans)
    }
    return res
}