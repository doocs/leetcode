---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.06.Hanota/README_EN.md
---

# [08.06. Hanota](https://leetcode.cn/problems/hanota-lcci)

[中文文档](/lcci/08.06.Hanota/README.md)

## Description

<p>In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e., each disk sits on top of an even larger one). You have the following constraints:</p>
<p>(1) Only one disk can be moved at a time.<br />
(2) A disk is slid off the top of one tower onto another tower.<br />
(3) A disk cannot be placed on top of a smaller disk.</p>
<p>Write a program to move the disks from the first tower to the last using stacks.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: A = [2, 1, 0], B = [], C = []

<strong> Output</strong>: C = [2, 1, 0]

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: A = [1, 0], B = [], C = []

<strong> Output</strong>: C = [1, 0]

</pre>
<p><strong>Note:</strong></p>
<ol>
	<li><code>A.length &lt;= 14</code></li>
</ol>

## Solutions

### Solution 1: Recursion

We design a function $dfs(n, a, b, c)$, which represents moving $n$ disks from $a$ to $c$, with $b$ as the auxiliary rod.

First, we move $n - 1$ disks from $a$ to $b$, then move the $n$-th disk from $a$ to $c$, and finally move $n - 1$ disks from $b$ to $c$.

The time complexity is $O(2^n)$, and the space complexity is $O(n)$. Here, $n$ is the number of disks.

<!-- tabs:start -->

```python
class Solution:
    def hanota(self, A: List[int], B: List[int], C: List[int]) -> None:
        def dfs(n, a, b, c):
            if n == 1:
                c.append(a.pop())
                return
            dfs(n - 1, a, c, b)
            c.append(a.pop())
            dfs(n - 1, b, a, c)

        dfs(len(A), A, B, C)
```

```java
class Solution {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        dfs(A.size(), A, B, C);
    }

    private void dfs(int n, List<Integer> a, List<Integer> b, List<Integer> c) {
        if (n == 1) {
            c.add(a.remove(a.size() - 1));
            return;
        }
        dfs(n - 1, a, c, b);
        c.add(a.remove(a.size() - 1));
        dfs(n - 1, b, a, c);
    }
}
```

```cpp
class Solution {
public:
    void hanota(vector<int>& A, vector<int>& B, vector<int>& C) {
        function<void(int, vector<int>&, vector<int>&, vector<int>&)> dfs = [&](int n, vector<int>& a, vector<int>& b, vector<int>& c) {
            if (n == 1) {
                c.push_back(a.back());
                a.pop_back();
                return;
            }
            dfs(n - 1, a, c, b);
            c.push_back(a.back());
            a.pop_back();
            dfs(n - 1, b, a, c);
        };
        dfs(A.size(), A, B, C);
    }
};
```

```go
func hanota(A []int, B []int, C []int) []int {
	var dfs func(n int, a, b, c *[]int)
	dfs = func(n int, a, b, c *[]int) {
		if n == 1 {
			*c = append(*c, (*a)[len(*a)-1])
			*a = (*a)[:len(*a)-1]
			return
		}
		dfs(n-1, a, c, b)
		*c = append(*c, (*a)[len(*a)-1])
		*a = (*a)[:len(*a)-1]
		dfs(n-1, b, a, c)
	}
	dfs(len(A), &A, &B, &C)
	return C
}
```

```ts
/**
 Do not return anything, modify C in-place instead.
 */
function hanota(A: number[], B: number[], C: number[]): void {
    const dfs = (n: number, a: number[], b: number[], c: number[]) => {
        if (n === 1) {
            c.push(a.pop()!);
            return;
        }
        dfs(n - 1, a, c, b);
        c.push(a.pop()!);
        dfs(n - 1, b, a, c);
    };
    dfs(A.length, A, B, C);
}
```

```swift
class Solution {
    func hanota(_ A: inout [Int], _ B: inout [Int], _ C: inout [Int]) {
        dfs(n: A.count, a: &A, b: &B, c: &C)
    }

    private func dfs(n: Int, a: inout [Int], b: inout [Int], c: inout [Int]) {
        if n == 1 {
            c.append(a.removeLast())
            return
        }
        dfs(n: n - 1, a: &a, b: &c, c: &b)
        c.append(a.removeLast())
        dfs(n: n - 1, a: &b, b: &a, c: &c)
    }
}
```

<!-- tabs:end -->

### Solution 2: Iteration (Stack)

We can use a stack to simulate the recursive process.

We define a struct $Task$, which represents a task, where $n$ represents the number of disks, and $a$, $b$, $c$ represent the three rods.

We push the initial task $Task(len(A), A, B, C)$ into the stack, and then continuously process the task at the top of the stack until the stack is empty.

If $n = 1$, then we directly move the disk from $a$ to $c$.

Otherwise, we push three subtasks into the stack, which are:

1. Move $n - 1$ disks from $b$ to $c$ with the help of $a$;
2. Move the $n$-th disk from $a$ to $c$;
3. Move $n - 1$ disks from $a$ to $b$ with the help of $c$.

The time complexity is $O(2^n)$, and the space complexity is $O(n)$. Here, $n$ is the number of disks.

<!-- tabs:start -->

```python
class Solution:
    def hanota(self, A: List[int], B: List[int], C: List[int]) -> None:
        stk = [(len(A), A, B, C)]
        while stk:
            n, a, b, c = stk.pop()
            if n == 1:
                c.append(a.pop())
            else:
                stk.append((n - 1, b, a, c))
                stk.append((1, a, b, c))
                stk.append((n - 1, a, c, b))
```

```java
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
```

```cpp
struct Task {
    int n;
    vector<int>* a;
    vector<int>* b;
    vector<int>* c;
};

class Solution {
public:
    void hanota(vector<int>& A, vector<int>& B, vector<int>& C) {
        stack<Task> stk;
        stk.push({(int) A.size(), &A, &B, &C});
        while (!stk.empty()) {
            Task task = stk.top();
            stk.pop();
            if (task.n == 1) {
                task.c->push_back(task.a->back());
                task.a->pop_back();
            } else {
                stk.push({task.n - 1, task.b, task.a, task.c});
                stk.push({1, task.a, task.b, task.c});
                stk.push({task.n - 1, task.a, task.c, task.b});
            }
        }
    }
};
```

```go
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
```

```ts
/**
 Do not return anything, modify C in-place instead.
 */
function hanota(A: number[], B: number[], C: number[]): void {
    const stk: any[] = [[A.length, A, B, C]];
    while (stk.length) {
        const [n, a, b, c] = stk.pop()!;
        if (n === 1) {
            c.push(a.pop());
        } else {
            stk.push([n - 1, b, a, c]);
            stk.push([1, a, b, c]);
            stk.push([n - 1, a, c, b]);
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
