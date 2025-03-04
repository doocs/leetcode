---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.06.Hanota/README.md
---

<!-- problem:start -->

# [面试题 08.06. 汉诺塔问题](https://leetcode.cn/problems/hanota-lcci)

[English Version](/lcci/08.06.Hanota/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:<br>
(1) 每次只能移动一个盘子;<br>
(2) 盘子只能从柱子顶端滑出移到下一根柱子;<br>
(3) 盘子只能叠在比它大的盘子上。</p>
<p>请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。</p>
<p>你需要原地修改栈。</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>：A = [2, 1, 0], B = [], C = []
<strong> 输出</strong>：C = [2, 1, 0]
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：A = [1, 0], B = [], C = []
<strong> 输出</strong>：C = [1, 0]
</pre>
<p><strong>提示:</strong></p>
<ol>
	<li>A中盘子的数目不大于14个。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们设计一个函数 $dfs(n, a, b, c)$，表示将 $n$ 个盘子从 $a$ 移动到 $c$，其中 $b$ 为辅助柱子。

我们先将 $n - 1$ 个盘子从 $a$ 移动到 $b$，然后将第 $n$ 个盘子从 $a$ 移动到 $c$，最后将 $n - 1$ 个盘子从 $b$ 移动到 $c$。

时间复杂度 $O(2^n)$，空间复杂度 $O(n)$。其中 $n$ 是盘子的数目。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    void hanota(vector<int>& A, vector<int>& B, vector<int>& C) {
        auto dfs = [&](this auto&& dfs, int n, vector<int>& a, vector<int>& b, vector<int>& c) {
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

#### Go

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

#### TypeScript

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

#### Swift

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

<!-- solution:end -->

<!-- solution:start-->

### 方法二：迭代（栈）

我们可以用栈来模拟递归的过程。

我们定义一个结构体 $Task$，表示一个任务，其中 $n$ 表示盘子的数目，而 $a$, $b$, $c$ 表示三根柱子。

我们将初始任务 $Task(len(A), A, B, C)$ 压入栈中，然后不断取出栈顶任务进行处理，直到栈为空。

如果 $n = 1$，那么我们直接将盘子从 $a$ 移动到 $c$。

否则，我们将三个子任务压入栈中，分别是：

1. 将 $n - 1$ 个盘子从 $b$ 借助 $a$ 移动到 $c$；
2. 将第 $n$ 个盘子从 $a$ 移动到 $c$；
3. 将 $n - 1$ 个盘子从 $a$ 借助 $c$ 移动到 $b$。

时间复杂度 $O(2^n)$，空间复杂度 $O(n)$。其中 $n$ 是盘子的数目。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
