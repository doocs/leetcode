---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/03.03.Stack%20of%20Plates/README_EN.md
---

<!-- problem:start -->

# [03.03. Stack of Plates](https://leetcode.cn/problems/stack-of-plates-lcci)

[中文文档](/lcci/03.03.Stack%20of%20Plates/README.md)

## Description

<!-- description:start -->

<p>Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure <code>SetOfStacks</code> that mimics this.&nbsp;<code>SetOfStacks</code> should be composed of several stacks and should create a new stack once the previous one exceeds capacity. <code>SetOfStacks.push()</code> and <code>SetOfStacks.pop()</code> should behave identically to a single stack (that is, <code>pop()</code> should return the same values as it would if there were just a single stack). Follow Up: Implement a function <code>popAt(int index)</code> which performs a pop operation on a specific sub-stack.</p>
<p>You should delete the sub-stack when it becomes empty. <code>pop</code>, <code>popAt</code> should return -1 when there&#39;s no element to pop.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>:

[&quot;StackOfPlates&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAt&quot;, &quot;pop&quot;, &quot;pop&quot;]

[[1], [1], [2], [1], [], []]

<strong> Output</strong>:

[null, null, null, 2, 1, -1]

<strong> Explanation</strong>:

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>:

[&quot;StackOfPlates&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAt&quot;, &quot;popAt&quot;, &quot;popAt&quot;]

[[2], [1], [2], [3], [0], [0], [0]]

<strong> Output</strong>:

[null, null, null, null, 2, 1, 3]

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can use a list of stacks $stk$ to simulate this process, initially $stk$ is empty.

-   When the `push` method is called, if $cap$ is 0, return directly. Otherwise, if $stk$ is empty or the length of the last stack in $stk$ is greater than or equal to $cap$, then create a new stack. Then add the element $val$ to the last stack in $stk$. The time complexity is $O(1)$.
-   When the `pop` method is called, return the result of `popAt(|stk| - 1)`. The time complexity is $O(1)$.
-   When the `popAt` method is called, if $index$ is not in the range $[0, |stk|)$, return -1. Otherwise, return the top element of $stk[index]$ and pop it out. If $stk[index]$ is empty after popping, remove it from $stk$. The time complexity is $O(1)$.

The space complexity is $O(n)$, where $n$ is the number of elements.

<!-- tabs:start -->

```python
class StackOfPlates:
    def __init__(self, cap: int):
        self.cap = cap
        self.stk = []

    def push(self, val: int) -> None:
        if self.cap == 0:
            return
        if not self.stk or len(self.stk[-1]) >= self.cap:
            self.stk.append([])
        self.stk[-1].append(val)

    def pop(self) -> int:
        return self.popAt(len(self.stk) - 1)

    def popAt(self, index: int) -> int:
        ans = -1
        if 0 <= index < len(self.stk):
            ans = self.stk[index].pop()
            if not self.stk[index]:
                self.stk.pop(index)
        return ans


# Your StackOfPlates object will be instantiated and called as such:
# obj = StackOfPlates(cap)
# obj.push(val)
# param_2 = obj.pop()
# param_3 = obj.popAt(index)
```

```java
class StackOfPlates {
    private List<Deque<Integer>> stk = new ArrayList<>();
    private int cap;

    public StackOfPlates(int cap) {
        this.cap = cap;
    }

    public void push(int val) {
        if (cap == 0) {
            return;
        }
        if (stk.isEmpty() || stk.get(stk.size() - 1).size() >= cap) {
            stk.add(new ArrayDeque<>());
        }
        stk.get(stk.size() - 1).push(val);
    }

    public int pop() {
        return popAt(stk.size() - 1);
    }

    public int popAt(int index) {
        int ans = -1;
        if (index >= 0 && index < stk.size()) {
            ans = stk.get(index).pop();
            if (stk.get(index).isEmpty()) {
                stk.remove(index);
            }
        }
        return ans;
    }
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates obj = new StackOfPlates(cap);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAt(index);
 */
```

```cpp
class StackOfPlates {
public:
    StackOfPlates(int cap) {
        this->cap = cap;
    }

    void push(int val) {
        if (!cap) {
            return;
        }
        if (stk.empty() || stk.back().size() >= cap) {
            stk.emplace_back(stack<int>());
        }
        stk.back().push(val);
    }

    int pop() {
        return popAt(stk.size() - 1);
    }

    int popAt(int index) {
        int ans = -1;
        if (index >= 0 && index < stk.size()) {
            ans = stk[index].top();
            stk[index].pop();
            if (stk[index].empty()) {
                stk.erase(stk.begin() + index);
            }
        }
        return ans;
    }

private:
    int cap;
    vector<stack<int>> stk;
};

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates* obj = new StackOfPlates(cap);
 * obj->push(val);
 * int param_2 = obj->pop();
 * int param_3 = obj->popAt(index);
 */
```

```go
type StackOfPlates struct {
	stk [][]int
	cap int
}

func Constructor(cap int) StackOfPlates {
	return StackOfPlates{[][]int{}, cap}
}

func (this *StackOfPlates) Push(val int) {
	if this.cap == 0 {
		return
	}
	if len(this.stk) == 0 || len(this.stk[len(this.stk)-1]) >= this.cap {
		this.stk = append(this.stk, []int{})
	}
	this.stk[len(this.stk)-1] = append(this.stk[len(this.stk)-1], val)
}

func (this *StackOfPlates) Pop() int {
	return this.PopAt(len(this.stk) - 1)
}

func (this *StackOfPlates) PopAt(index int) int {
	ans := -1
	if index >= 0 && index < len(this.stk) {
		t := this.stk[index]
		ans = t[len(t)-1]
		this.stk[index] = this.stk[index][:len(t)-1]
		if len(this.stk[index]) == 0 {
			this.stk = append(this.stk[:index], this.stk[index+1:]...)
		}
	}
	return ans
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * obj := Constructor(cap);
 * obj.Push(val);
 * param_2 := obj.Pop();
 * param_3 := obj.PopAt(index);
 */
```

```ts
class StackOfPlates {
    private cap: number;
    private stacks: number[][];
    constructor(cap: number) {
        this.cap = cap;
        this.stacks = [];
    }
    push(val: number): void {
        if (this.cap === 0) {
            return;
        }
        const n = this.stacks.length;
        const stack = this.stacks[n - 1];
        if (stack == null || stack.length === this.cap) {
            this.stacks.push([val]);
        } else {
            stack.push(val);
        }
    }
    pop(): number {
        const n = this.stacks.length;
        if (n === 0) {
            return -1;
        }
        const stack = this.stacks[n - 1];
        const res = stack.pop();
        if (stack.length === 0) {
            this.stacks.pop();
        }
        return res;
    }
    popAt(index: number): number {
        if (index >= this.stacks.length) {
            return -1;
        }
        const stack = this.stacks[index];
        const res = stack.pop();
        if (stack.length === 0) {
            this.stacks.splice(index, 1);
        }
        return res;
    }
}
/**
 * Your StackOfPlates object will be instantiated and called as such:
 * var obj = new StackOfPlates(cap)
 * obj.push(val)
 * var param_2 = obj.pop()
 * var param_3 = obj.popAt(index)
 */
```

```swift
class StackOfPlates {
    private var stacks: [[Int]]
    private var cap: Int

    init(_ cap: Int) {
        self.cap = cap
        self.stacks = []
    }

    func push(_ val: Int) {
        if cap == 0 {
            return
        }
        if stacks.isEmpty || stacks.last!.count >= cap {
            stacks.append([])
        }
        stacks[stacks.count - 1].append(val)
    }

    func pop() -> Int {
        return popAt(stacks.count - 1)
    }

    func popAt(_ index: Int) -> Int {
        guard index >= 0, index < stacks.count, !stacks[index].isEmpty else {
            return -1
        }
        let value = stacks[index].removeLast()
        if stacks[index].isEmpty {
            stacks.remove(at: index)
        }
        return value
    }
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * let obj = new StackOfPlates(cap);
 * obj.push(val);
 * let param_2 = obj.pop();
 * let param_3 = obj.popAt(index);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
