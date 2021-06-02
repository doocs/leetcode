func kthLargest(root *TreeNode, k int) int {
	ch := make(chan int)
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	go inOrder(ctx, root, ch)
	for ; k > 1; k-- {
		<-ch
	}
	return <-ch
}

func inOrder(ctx context.Context, cur *TreeNode, ch chan<- int) {
	if cur != nil {
		inOrder(ctx, cur.Right, ch)
		select {
		case ch <- cur.Val:
		case <-ctx.Done():
			return
		}
		inOrder(ctx, cur.Left, ch)
	}
}
