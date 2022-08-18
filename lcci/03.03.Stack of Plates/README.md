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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python


```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java


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
