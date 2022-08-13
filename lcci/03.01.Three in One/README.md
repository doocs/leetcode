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

<!-- 这里可写通用的实现逻辑 -->

二维数组解决；也可以使用一维数组，以下标 `0,3,6,..`、`1,4,7,..`、`2,5,8,..` 区分，一维数组最后三个元素记录每个栈的元素个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class TripleInOne:
    def __init__(self, stackSize: int):
        self._capacity = stackSize
        self._s = [[], [], []]

    def push(self, stackNum: int, value: int) -> None:
        if len(self._s[stackNum]) < self._capacity:
            self._s[stackNum].append(value)

    def pop(self, stackNum: int) -> int:
        return -1 if self.isEmpty(stackNum) else self._s[stackNum].pop()

    def peek(self, stackNum: int) -> int:
        return -1 if self.isEmpty(stackNum) else self._s[stackNum][-1]

    def isEmpty(self, stackNum: int) -> bool:
        return len(self._s[stackNum]) == 0


# Your TripleInOne object will be instantiated and called as such:
# obj = TripleInOne(stackSize)
# obj.push(stackNum,value)
# param_2 = obj.pop(stackNum)
# param_3 = obj.peek(stackNum)
# param_4 = obj.isEmpty(stackNum)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class TripleInOne {
    private int[] s;
    private int capacity;

    public TripleInOne(int stackSize) {
        s = new int[stackSize * 3 + 3];
        capacity = stackSize;
    }

    public void push(int stackNum, int value) {
        if (s[stackNum + 3 * capacity] < capacity) {
            s[s[stackNum + 3 * capacity] * 3 + stackNum] = value;
            ++s[stackNum + 3 * capacity];
        }
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        --s[stackNum + 3 * capacity];
        return s[s[stackNum + 3 * capacity] * 3 + stackNum];
    }

    public int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : s[(s[stackNum + 3 * capacity] - 1) * 3 + stackNum];
    }

    public boolean isEmpty(int stackNum) {
        return s[stackNum + 3 * capacity] == 0;
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

### **Go**

```go
type TripleInOne struct {
	data      []int
	offset    [3]int
	stackSize int
}

func Constructor(stackSize int) TripleInOne {
	total := stackSize * 3
	data := make([]int, total)
	offset := [3]int{}
	for i := 0; i < 3; i++ {
		offset[i] = i * stackSize
	}
	return TripleInOne{
		data:      data,
		offset:    offset,
		stackSize: stackSize,
	}
}

func (this *TripleInOne) Push(stackNum int, value int) {
	i := this.offset[stackNum]
	if i < (stackNum+1)*this.stackSize {
		this.data[i] = value
		this.offset[stackNum]++
	}
}

func (this *TripleInOne) Pop(stackNum int) int {
	i := this.offset[stackNum]
	if i == stackNum*this.stackSize {
		return -1
	}
	this.offset[stackNum]--
	return this.data[i-1]
}

func (this *TripleInOne) Peek(stackNum int) int {
	i := this.offset[stackNum]
	if i == stackNum*this.stackSize {
		return -1
	}
	return this.data[i-1]
}

func (this *TripleInOne) IsEmpty(stackNum int) bool {
	return this.offset[stackNum] == stackNum*this.stackSize
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

### **...**

```

```

<!-- tabs:end -->
