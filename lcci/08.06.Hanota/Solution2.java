class Solution {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        Deque<Task> stk = new ArrayDeque<>();
        stk.push(new Task(A.size(), A, B, C));
        while (stk.size() > 0) {
            Task task = stk.pop();
            int n = task.n;
            List<Integer> a = task.a;
            List<Integer> b = task.b;
            List<Integer> c = task.c;
            if (n == 1) {
                c.add(a.remove(a.size() - 1));
            } else {
                stk.push(new Task(n - 1, b, a, c));
                stk.push(new Task(1, a, b, c));
                stk.push(new Task(n - 1, a, c, b));
            }
        }
    }
}

class Task {
    int n;
    List<Integer> a;
    List<Integer> b;
    List<Integer> c;

    public Task(int n, List<Integer> a, List<Integer> b, List<Integer> c) {
        this.n = n;
        this.a = a;
        this.b = b;
        this.c = c;
    }
}