# [1670. 设计前中后队列](https://leetcode-cn.com/problems/design-front-middle-back-queue)

[English Version](/solution/1500-1599/1670.Design%20Front%20Middle%20Back%20Queue/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>请你设计一个队列，支持在前，中，后三个位置的 <code>push</code> 和 <code>pop</code> 操作。</p>

<p>请你完成 <code>FrontMiddleBack</code> 类：</p>

<ul>
	<li><code>FrontMiddleBack()</code> 初始化队列。</li>
	<li><code>void pushFront(int val)</code> 将 <code>val</code> 添加到队列的 <strong>最前面</strong> 。</li>
	<li><code>void pushMiddle(int val)</code> 将 <code>val</code> 添加到队列的 <strong>正中间</strong> 。</li>
	<li><code>void pushBack(int val)</code> 将 <code>val</code> 添加到队里的 <strong>最后面</strong> 。</li>
	<li><code>int popFront()</code> 将 <strong>最前面</strong> 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 <code>-1</code> 。</li>
	<li><code>int popMiddle()</code> 将 <b>正中间</b> 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 <code>-1</code> 。</li>
	<li><code>int popBack()</code> 将 <strong>最后面</strong> 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 <code>-1</code> 。</li>
</ul>

<p>请注意当有 <strong>两个</strong> 中间位置的时候，选择靠前面的位置进行操作。比方说：</p>

<ul>
	<li>将 <code>6</code> 添加到 <code>[1, 2, 3, 4, 5]</code> 的中间位置，结果数组为 <code>[1, 2, <strong>6</strong>, 3, 4, 5]</code> 。</li>
	<li>从 <code>[1, 2, <strong>3</strong>, 4, 5, 6]</code> 的中间位置弹出元素，返回 <code>3</code> ，数组变为 <code>[1, 2, 4, 5, 6]</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
[[], [1], [2], [3], [4], [], [], [], [], []]
<strong>输出：</strong>
[null, null, null, null, null, 1, 3, 4, 2, -1]

<strong>解释：</strong>
FrontMiddleBackQueue q = new FrontMiddleBackQueue();
q.pushFront(1);   // [<strong>1</strong>]
q.pushBack(2);    // [1, <strong>2</strong>]
q.pushMiddle(3);  // [1, <strong>3</strong>, 2]
q.pushMiddle(4);  // [1, <strong>4</strong>, 3, 2]
q.popFront();     // 返回 1 -> [4, 3, 2]
q.popMiddle();    // 返回 3 -> [4, 2]
q.popMiddle();    // 返回 4 -> [2]
q.popBack();      // 返回 2 -> []
q.popFront();     // 返回 -1 -> [] （队列为空）
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= val <= 10<sup>9</sup></code></li>
	<li>最多调用 <code>1000</code> 次 <code>pushFront</code>， <code>pushMiddle</code>， <code>pushBack</code>， <code>popFront</code>， <code>popMiddle</code> 和 <code>popBack</code> 。</li>
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
