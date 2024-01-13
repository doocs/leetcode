func hanota(A []int, B []int, C []int) []int {
	stk := []Task{{len(A), &A, &B, &C}}
	for len(stk) > 0 {
		task := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if task.n == 1 {
			*task.c = append(*task.c, (*task.a)[len(*task.a)-1])
			*task.a = (*task.a)[:len(*task.a)-1]
		} else {
			stk = append(stk, Task{task.n - 1, task.b, task.a, task.c})
			stk = append(stk, Task{1, task.a, task.b, task.c})
			stk = append(stk, Task{task.n - 1, task.a, task.c, task.b})
		}
	}
	return C
}

type Task struct {
	n       int
	a, b, c *[]int
}