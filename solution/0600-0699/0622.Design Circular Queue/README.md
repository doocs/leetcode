# [622. 设计循环队列](https://leetcode-cn.com/problems/design-circular-queue)

[English Version](/solution/0600-0699/0622.Design%20Circular%20Queue/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为&ldquo;环形缓冲器&rdquo;。</p>

<p>循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。</p>

<p>你的实现应该支持如下操作：</p>

<ul>
	<li><code>MyCircularQueue(k)</code>: 构造器，设置队列长度为 k 。</li>
	<li><code>Front</code>: 从队首获取元素。如果队列为空，返回 -1 。</li>
	<li><code>Rear</code>: 获取队尾元素。如果队列为空，返回 -1 。</li>
	<li><code>enQueue(value)</code>: 向循环队列插入一个元素。如果成功插入则返回真。</li>
	<li><code>deQueue()</code>: 从循环队列中删除一个元素。如果成功删除则返回真。</li>
	<li><code>isEmpty()</code>: 检查循环队列是否为空。</li>
	<li><code>isFull()</code>: 检查循环队列是否已满。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>MyCircularQueue circularQueue = new MycircularQueue(3); // 设置长度为 3

circularQueue.enQueue(1); &nbsp;// 返回 true

circularQueue.enQueue(2); &nbsp;// 返回 true

circularQueue.enQueue(3); &nbsp;// 返回 true

circularQueue.enQueue(4); &nbsp;// 返回 false，队列已满

circularQueue.Rear(); &nbsp;// 返回 3

circularQueue.isFull(); &nbsp;// 返回 true

circularQueue.deQueue(); &nbsp;// 返回 true

circularQueue.enQueue(4); &nbsp;// 返回 true

circularQueue.Rear(); &nbsp;// 返回 4
&nbsp;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>所有的值都在 0&nbsp;至 1000 的范围内；</li>
	<li>操作数将在 1 至 1000 的范围内；</li>
	<li>请不要使用内置的队列库。</li>
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