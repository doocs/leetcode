# [716. 最大栈](https://leetcode.cn/problems/max-stack)

[English Version](/solution/0700-0799/0716.Max%20Stack/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个最大栈数据结构，既支持栈操作，又支持查找栈中最大元素。</p>

<p>实现 <code>MaxStack</code> 类：</p>

<ul>
	<li><code>MaxStack()</code> 初始化栈对象</li>
	<li><code>void push(int x)</code> 将元素 x 压入栈中。</li>
	<li><code>int pop()</code> 移除栈顶元素并返回这个元素。</li>
	<li><code>int top()</code> 返回栈顶元素，无需移除。</li>
	<li><code>int peekMax()</code> 检索并返回栈中最大元素，无需移除。</li>
	<li><code>int popMax()</code> 检索并返回栈中最大元素，并将其移除。如果有多个最大元素，只要移除 <strong>最靠近栈顶</strong> 的那个。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
<strong>输出</strong>
[null, null, null, null, 5, 5, 1, 5, 1, 5]

<strong>解释</strong>
MaxStack stk = new MaxStack();
stk.push(5);   // [<strong>5</strong>] - 5 既是栈顶元素，也是最大元素
stk.push(1);   // [<strong>5</strong>, <strong>1</strong>] - 栈顶元素是 1，最大元素是 5
stk.push(5);   // [5, 1, <strong>5</strong>] - 5 既是栈顶元素，也是最大元素
stk.top();     // 返回 5，[5, 1, <strong>5</strong>] - 栈没有改变
stk.popMax();  // 返回 5，[<strong>5</strong>, <strong>1</strong>] - 栈发生改变，栈顶元素不再是最大元素
stk.top();     // 返回 1，[<strong>5</strong>, <strong>1</strong>] - 栈没有改变
stk.peekMax(); // 返回 5，[<strong>5</strong>, <strong>1</strong>] - 栈没有改变
stk.pop();     // 返回 1，[<strong>5</strong>] - 此操作后，5 既是栈顶元素，也是最大元素
stk.top();     // 返回 5，[<strong>5</strong>] - 栈没有改变
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>7</sup> <= x <= 10<sup>7</sup></code></li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>push</code>、<code>pop</code>、<code>top</code>、<code>peekMax</code> 和 <code>popMax</code></li>
	<li>调用 <code>pop</code>、<code>top</code>、<code>peekMax</code> 或 <code>popMax</code> 时，栈中 <strong>至少存在一个元素</strong></li>
</ul>

<p> </p>

<p><b>进阶：</b> </p>

<ul>
	<li>试着设计解决方案：调用 <code>top</code> 方法的时间复杂度为 <code>O(1)</code> ，调用其他方法的时间复杂度为 <code>O(logn)</code> 。 </li>
</ul>

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

### **...**

```

```

<!-- tabs:end -->
