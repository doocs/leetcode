func levelOrder(root *TreeNode) [][]int {
    if root == nil {
        return nil
    }
    res := [][]int{}
    queue := []*TreeNode{}
    queue = append(queue,root)
    level := 0
    for len(queue) != 0 {
        size := len(queue)
        ans := []int{}
        //size记录每层大小,level记录层数
        for size > 0 {
            cur := queue[0]
            if level & 1 == 0 {
                ans = append(ans, cur.Val)
            } else {
                ans = append([]int{cur.Val},ans...)
            }
            
            queue = queue[1:]
            size--
            if cur.Left != nil {
                queue = append(queue, cur.Left)
            }
            if cur.Right != nil {
                queue = append(queue, cur.Right)
            }
        }
        level++
        res = append(res, ans)
    }
    return res
}