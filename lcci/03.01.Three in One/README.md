---
comment: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/03.01.Three%20in%20One/README.md
---

# [面试题 03.01. 三合一](https://leetcode.cn/problems/three-in-one-lcci)

[English Version](/lcci/03.01.Three%20in%20One/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>三合一。描述如何只用一个数组来实现三个栈。</p>

<p>你应该实现<code>push(stackNum, value)</code>、<code>pop(stackNum)</code>、<code>isEmpty(stackNum)</code>、<code>peek(stackNum)</code>方法。<code>stackNum</code>表示栈下标，<code>value</code>表示压入的值。</p>

<p>构造函数会传入一个<code>stackSize</code>参数，代表每个栈的大小。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：
[&quot;TripleInOne&quot;, &quot;push&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;isEmpty&quot;]
[[1], [0, 1], [0, 2], [0], [0], [0], [0]]
<strong> 输出</strong>：
[null, null, null, 1, -1, -1, true]
<strong>说明</strong>：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：
[&quot;TripleInOne&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;peek&quot;]
[[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
<strong> 输出</strong>：
[null, null, null, null, 2, 1, -1, -1]
</pre>

## 解法

### 方法一：数组模拟

我们使用一个变量 $cap$ 来表示每个栈的大小，使用一个长度为 $3 \times \text{cap} + 3$ 的数组 $stk$ 来模拟三个栈，数组的前 $3 \times \text{cap}$ 个元素用来存储栈的元素，数组的后三个元素用来存储每个栈的元素个数。

对于 `push` 操作，我们首先判断栈是否已满，如果未满，则将元素压入栈中，并更新栈的元素个数。

对于 `pop` 操作，我们首先判断栈是否为空，如果不为空，则更新栈的元素个数，并返回栈顶元素。

对于 `peek` 操作，我们首先判断栈是否为空，如果不为空，则返回栈顶元素。

对于 `isEmpty` 操作，我们直接判断栈是否为空即可。对于栈 $i$，我们只需要判断 $stk[\text{cap} \times 3 + i]$ 是否为 $0$ 即可。

时间复杂度上，每个操作的时间复杂度均为 $O(1)$。空间复杂度为 $O(\text{cap})$，其中 $\text{cap}$ 为栈的大小。

<!-- tabs:start -->

```python
class TripleInOne:

    def __init__(self, stackSize: int):
        self.cap = stackSize
        self.stk = [0] * (self.cap * 3 + 3)

    def push(self, stackNum: int, value: int) -> None:
        if self.stk[self.cap * 3 + stackNum] < self.cap:
            self.stk[self.cap * stackNum + self.stk[self.cap * 3 + stackNum]] = value
            self.stk[self.cap * 3 + stackNum] += 1

    def pop(self, stackNum: int) -> int:
        if self.isEmpty(stackNum):
            return -1
        self.stk[self.cap * 3 + stackNum] -= 1
        return self.stk[self.cap * stackNum + self.stk[self.cap * 3 + stackNum]]

    def peek(self, stackNum: int) -> int:
        if self.isEmpty(stackNum):
            return -1
        return self.stk[self.cap * stackNum + self.stk[self.cap * 3 + stackNum] - 1]

    def isEmpty(self, stackNum: int) -> bool:
        return self.stk[self.cap * 3 + stackNum] == 0


# Your TripleInOne object will be instantiated and called as such:
# obj = TripleInOne(stackSize)
# obj.push(stackNum,value)
# param_2 = obj.pop(stackNum)
# param_3 = obj.peek(stackNum)
# param_4 = obj.isEmpty(stackNum)
```

```java
class TripleInOne {
    private int cap;
    private int[] stk;

    public TripleInOne(int stackSize) {
        cap = stackSize;
        stk = new int[cap * 3 + 3];
    }

    public void push(int stackNum, int value) {
        if (stk[cap * 3 + stackNum] < cap) {
            stk[cap * stackNum + stk[cap * 3 + stackNum]] = value;
            ++stk[cap * 3 + stackNum];
        }
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        --stk[cap * 3 + stackNum];
        return stk[cap * stackNum + stk[cap * 3 + stackNum]];
    }

    public int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : stk[cap * stackNum + stk[cap * 3 + stackNum] - 1];
    }

    public boolean isEmpty(int stackNum) {
        return stk[cap * 3 + stackNum] == 0;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */
```

```cpp
class TripleInOne {
public:
    TripleInOne(int stackSize) {
        cap = stackSize;
        stk.resize(cap * 3 + 3);
    }

    void push(int stackNum, int value) {
        if (stk[cap * 3 + stackNum] < cap) {
            stk[cap * stackNum + stk[cap * 3 + stackNum]] = value;
            ++stk[cap * 3 + stackNum];
        }
    }

    int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        --stk[cap * 3 + stackNum];
        return stk[cap * stackNum + stk[cap * 3 + stackNum]];
    }

    int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : stk[cap * stackNum + stk[cap * 3 + stackNum] - 1];
    }

    bool isEmpty(int stackNum) {
        return stk[cap * 3 + stackNum] == 0;
    }

private:
    int cap;
    vector<int> stk;
};

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne* obj = new TripleInOne(stackSize);
 * obj->push(stackNum,value);
 * int param_2 = obj->pop(stackNum);
 * int param_3 = obj->peek(stackNum);
 * bool param_4 = obj->isEmpty(stackNum);
 */
```

```go
type TripleInOne struct {
	cap int
	stk []int
}

func Constructor(stackSize int) TripleInOne {
	return TripleInOne{stackSize, make([]int, stackSize*3+3)}
}

func (this *TripleInOne) Push(stackNum int, value int) {
	if this.stk[this.cap*3+stackNum] < this.cap {
		this.stk[this.cap*stackNum+this.stk[this.cap*3+stackNum]] = value
		this.stk[this.cap*3+stackNum]++
	}
}

func (this *TripleInOne) Pop(stackNum int) int {
	if this.IsEmpty(stackNum) {
		return -1
	}
	this.stk[this.cap*3+stackNum]--
	return this.stk[this.cap*stackNum+this.stk[this.cap*3+stackNum]]
}

func (this *TripleInOne) Peek(stackNum int) int {
	if this.IsEmpty(stackNum) {
		return -1
	}
	return this.stk[this.cap*stackNum+this.stk[this.cap*3+stackNum]-1]
}

func (this *TripleInOne) IsEmpty(stackNum int) bool {
	return this.stk[this.cap*3+stackNum] == 0
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * obj := Constructor(stackSize);
 * obj.Push(stackNum,value);
 * param_2 := obj.Pop(stackNum);
 * param_3 := obj.Peek(stackNum);
 * param_4 := obj.IsEmpty(stackNum);
 */
```

```ts
class TripleInOne {
    private cap: number;
    private stk: number[];

    constructor(stackSize: number) {
        this.cap = stackSize;
        this.stk = Array<number>(stackSize * 3 + 3).fill(0);
    }

    push(stackNum: number, value: number): void {
        if (this.stk[this.cap * 3 + stackNum] < this.cap) {
            this.stk[this.cap * stackNum + this.stk[this.cap * 3 + stackNum]] = value;
            this.stk[this.cap * 3 + stackNum]++;
        }
    }

    pop(stackNum: number): number {
        if (this.isEmpty(stackNum)) {
            return -1;
        }
        this.stk[this.cap * 3 + stackNum]--;
        return this.stk[this.cap * stackNum + this.stk[this.cap * 3 + stackNum]];
    }

    peek(stackNum: number): number {
        if (this.isEmpty(stackNum)) {
            return -1;
        }
        return this.stk[this.cap * stackNum + this.stk[this.cap * 3 + stackNum] - 1];
    }

    isEmpty(stackNum: number): boolean {
        return this.stk[this.cap * 3 + stackNum] === 0;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * var obj = new TripleInOne(stackSize)
 * obj.push(stackNum,value)
 * var param_2 = obj.pop(stackNum)
 * var param_3 = obj.peek(stackNum)
 * var param_4 = obj.isEmpty(stackNum)
 */
```

```swift
class TripleInOne {
    private var cap: Int
    private var stk: [Int]

    init(_ stackSize: Int) {
        self.cap = stackSize
        self.stk = [Int](repeating: 0, count: cap * 3 + 3)
    }

    func push(_ stackNum: Int, _ value: Int) {
        if stk[cap * 3 + stackNum] < cap {
            stk[cap * stackNum + stk[cap * 3 + stackNum]] = value
            stk[cap * 3 + stackNum] += 1
        }
    }

    func pop(_ stackNum: Int) -> Int {
        if isEmpty(stackNum) {
            return -1
        }
        stk[cap * 3 + stackNum] -= 1
        return stk[cap * stackNum + stk[cap * 3 + stackNum]]
    }

    func peek(_ stackNum: Int) -> Int {
        if isEmpty(stackNum) {
            return -1
        }
        return stk[cap * stackNum + stk[cap * 3 + stackNum] - 1]
    }

    func isEmpty(_ stackNum: Int) -> Bool {
        return stk[cap * 3 + stackNum] == 0
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * let obj = TripleInOne(stackSize)
 * obj.push(stackNum,value)
 * let param_2 = obj.pop(stackNum)
 * let param_3 = obj.peek(stackNum)
 * let param_4 = obj.isEmpty(stackNum)
 */
```

<!-- tabs:end -->

<!-- end -->
