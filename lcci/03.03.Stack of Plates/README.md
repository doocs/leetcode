# [面试题 03.03. 堆盘子](https://leetcode.cn/problems/stack-of-plates-lcci)

[English Version](/lcci/03.03.Stack%20of%20Plates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构<code>SetOfStacks</code>，模拟这种行为。<code>SetOfStacks</code>应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，<code>SetOfStacks.push()</code>和<code>SetOfStacks.pop()</code>应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个<code>popAt(int index)</code>方法，根据指定的子栈，执行pop操作。</p>
<p>当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，<code>pop</code>，<code>popAt</code>&nbsp;应返回 -1.</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>：
[&quot;StackOfPlates&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAt&quot;, &quot;pop&quot;, &quot;pop&quot;]
[[1], [1], [2], [1], [], []]
<strong> 输出</strong>：
[null, null, null, 2, 1, -1]
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：
[&quot;StackOfPlates&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAt&quot;, &quot;popAt&quot;, &quot;popAt&quot;]
[[2], [1], [2], [3], [0], [0], [0]]
<strong> 输出</strong>：
[null, null, null, null, 2, 1, 3]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

用列表模拟栈的集合，每个栈的容量为 `cap`，当栈满时，新建一个栈。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class StackOfPlates {
public:
    StackOfPlates(int cap) {
        this->cap = cap;
    }

    void push(int val) {
        if (!cap) return;
        if (stk.empty() || stk[stk.size() - 1].size() >= cap) stk.emplace_back(stack<int>());
        stk[stk.size() - 1].push(val);
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
    vector<stack<int>> stk;
    int cap;
};

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates* obj = new StackOfPlates(cap);
 * obj->push(val);
 * int param_2 = obj->pop();
 * int param_3 = obj->popAt(index);
 */
```

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
