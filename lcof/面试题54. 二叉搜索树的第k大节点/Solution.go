/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 func kthLargest(root *TreeNode, k int) int {
	ch := make(chan int)
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	go inorder(ctx, root, ch)
	for ; k > 1; k-- {
		<-ch
	}
	return <-ch
}

func inorder(ctx context.Context, cur *TreeNode, ch chan<- int) {
	if cur != nil {
		inorder(ctx, cur.Right, ch)
		select {
		case ch <- cur.Val:
		case <-ctx.Done():
			return
		}
		inorder(ctx, cur.Left, ch)
	}
}
