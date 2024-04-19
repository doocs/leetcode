# [03.01. Three in One](https://leetcode.cn/problems/three-in-one-lcci)

[中文文档](/lcci/03.01.Three%20in%20One/README.md)

## Description

<p>Describe how you could use a single array to implement three stacks.</p>

<p>Yout should implement&nbsp;<code>push(stackNum, value)</code>、<code>pop(stackNum)</code>、<code>isEmpty(stackNum)</code>、<code>peek(stackNum)</code>&nbsp;methods.&nbsp;<code>stackNum<font face="sans-serif, Arial, Verdana, Trebuchet MS">&nbsp;</font></code><font face="sans-serif, Arial, Verdana, Trebuchet MS">is the index of the stack.&nbsp;</font><code>value</code>&nbsp;is the value that pushed to the stack.</p>

<p>The constructor requires a&nbsp;<code>stackSize</code>&nbsp;parameter, which represents the size of each stack.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>:

[&quot;TripleInOne&quot;, &quot;push&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;isEmpty&quot;]

[[1], [0, 1], [0, 2], [0], [0], [0], [0]]

<strong> Output</strong>:

[null, null, null, 1, -1, -1, true]

<b>Explanation</b>: When the stack is empty, `pop, peek` return -1. When the stack is full, `push` does nothing.

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>:

[&quot;TripleInOne&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;peek&quot;]

[[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]

<strong> Output</strong>:

[null, null, null, null, 2, 1, -1, -1]

</pre>

## Solutions

### Solution 1: Array Simulation

We use a variable $cap$ to represent the size of each stack, and use an array $stk$ of length $3 \times \text{cap} + 3$ to simulate three stacks. The first $3 \times \text{cap}$ elements of the array are used to store the elements of the stack, and the last three elements are used to store the number of elements in each stack.

For the `push` operation, we first check whether the stack is full. If it is not full, we push the element into the stack and update the number of elements in the stack.

For the `pop` operation, we first check whether the stack is empty. If it is not empty, we update the number of elements in the stack and return the top element of the stack.

For the `peek` operation, we first check whether the stack is empty. If it is not empty, we return the top element of the stack.

For the `isEmpty` operation, we directly check whether the stack is empty. For stack $i$, we only need to check whether $stk[\text{cap} \times 3 + i]$ is $0$.

In terms of time complexity, the time complexity of each operation is $O(1)$. The space complexity is $O(\text{cap})$, where $\text{cap}$ is the size of the stack.

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
 * var obj = TripleInOne(stackSize)
 * obj.push(stackNum,value)
 * var param_2 = obj.pop(stackNum)
 * var param_3 = obj.peek(stackNum)
 * var param_4 = obj.isEmpty(stackNum)
 */
```

<!-- tabs:end -->

<!-- end -->
