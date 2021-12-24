# [1188. 设计有限阻塞队列](https://leetcode-cn.com/problems/design-bounded-blocking-queue)

[English Version](/solution/1100-1199/1188.Design%20Bounded%20Blocking%20Queue/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现一个拥有如下方法的线程安全有限阻塞队列：</p>

<ul>
	<li><code>BoundedBlockingQueue(int capacity)</code>&nbsp;构造方法初始化队列，其中<code>capacity</code>代表队列长度上限。</li>
	<li><code>void enqueue(int element)</code>&nbsp;在队首增加一个<code>element</code>. 如果队列满，调用线程被阻塞直到队列非满。</li>
	<li><code>int dequeue()</code>&nbsp;返回队尾元素并从队列中将其删除. 如果队列为空，调用线程被阻塞直到队列非空。</li>
	<li><code>int size()</code>&nbsp;返回当前队列元素个数。</li>
</ul>

<p>你的实现将会被多线程同时访问进行测试。每一个线程要么是一个只调用<code>enqueue</code>方法的生产者线程，要么是一个只调用<code>dequeue</code>方法的消费者线程。<code>size</code>方法将会在每一个测试用例之后进行调用。</p>

<p>请不要使用内置的有限阻塞队列实现，否则面试将不会通过。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>
1
1
[&quot;BoundedBlockingQueue&quot;,&quot;enqueue&quot;,&quot;dequeue&quot;,&quot;dequeue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;dequeue&quot;]
[[2],[1],[],[],[0],[2],[3],[4],[]]

<strong>输出:</strong>
[1,0,2,2]

<strong>解释:
</strong>生产者线程数目 = 1
消费者线程数目 = 1

BoundedBlockingQueue queue = new BoundedBlockingQueue(2);   // 使用capacity = 2初始化队列。

queue.enqueue(1);   // 生产者线程将1插入队列。
queue.dequeue();    // 消费者线程调用dequeue并返回1。
queue.dequeue();    // 由于队列为空，消费者线程被阻塞。
queue.enqueue(0);   // 生产者线程将0插入队列。消费者线程被解除阻塞同时将0弹出队列并返回。
queue.enqueue(2);   // 生产者线程将2插入队列。
queue.enqueue(3);   // 生产者线程将3插入队列。
queue.enqueue(4);   // 生产者线程由于队列长度已达到上限2而被阻塞。
queue.dequeue();    // 消费者线程将2从队列弹出并返回。生产者线程解除阻塞同时将4插入队列。
queue.size();       // 队列中还有2个元素。size()方法在每组测试用例最后调用。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>
3
4
[&quot;BoundedBlockingQueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;dequeue&quot;,&quot;dequeue&quot;,&quot;dequeue&quot;,&quot;enqueue&quot;]
[[3],[1],[0],[2],[],[],[],[3]]

<strong>输出:</strong>
[1,0,2,1]

<strong>解释:
</strong>生产者线程数目 = 3
消费者线程数目 = 4

BoundedBlockingQueue queue = new BoundedBlockingQueue(3);   // 使用capacity = 3初始化队列。

queue.enqueue(1);   // 生产者线程P1将1插入队列。
queue.enqueue(0);   // 生产者线程P2将0插入队列。
queue.enqueue(2);   // 生产者线程P3将2插入队列。
queue.dequeue();    // 消费者线程C1调用dequeue。
queue.dequeue();    // 消费者线程C2调用dequeue。
queue.dequeue();    // 消费者线程C3调用dequeue。
queue.enqueue(3);   // 其中一个生产者线程将3插入队列。
queue.size();       // 队列中还有1个元素。

由于生产者/消费者线程的数目可能大于1，我们并不知道线程如何被操作系统调度，即使输入看上去隐含了顺序。因此任意一种输出[1,0,2]或[1,2,0]或[0,1,2]或[0,2,1]或[2,0,1]或[2,1,0]都可被接受。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
