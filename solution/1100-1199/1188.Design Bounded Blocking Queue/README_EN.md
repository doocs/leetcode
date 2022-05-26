# [1188. Design Bounded Blocking Queue](https://leetcode.com/problems/design-bounded-blocking-queue)

[中文文档](/solution/1100-1199/1188.Design%20Bounded%20Blocking%20Queue/README.md)

## Description

<p>Implement a thread-safe bounded blocking queue that has the following methods:</p>

<ul>
	<li><code>BoundedBlockingQueue(int capacity)</code> The constructor initializes the queue with a maximum <code>capacity</code>.</li>
	<li><code>void enqueue(int element)</code> Adds an <code>element</code> to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.</li>
	<li><code>int dequeue()</code> Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.</li>
	<li><code>int size()</code> Returns the number of elements currently in the queue.</li>
</ul>

<p>Your implementation will be tested using multiple threads at the same time. Each thread will either be a producer thread that only makes calls to the <code>enqueue</code> method or a consumer thread that only makes calls to the <code>dequeue</code> method. The <code>size</code> method will be called after every test case.</p>

<p>Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong>
1
1
[&quot;BoundedBlockingQueue&quot;,&quot;enqueue&quot;,&quot;dequeue&quot;,&quot;dequeue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;dequeue&quot;]
[[2],[1],[],[],[0],[2],[3],[4],[]]

<strong>Output:</strong>
[1,0,2,2]

<strong>Explanation:</strong>
Number of producer threads = 1
Number of consumer threads = 1

BoundedBlockingQueue queue = new BoundedBlockingQueue(2);   // initialize the queue with capacity = 2.

queue.enqueue(1);   // The producer thread enqueues 1 to the queue.
queue.dequeue();    // The consumer thread calls dequeue and returns 1 from the queue.
queue.dequeue();    // Since the queue is empty, the consumer thread is blocked.
queue.enqueue(0);   // The producer thread enqueues 0 to the queue. The consumer thread is unblocked and returns 0 from the queue.
queue.enqueue(2);   // The producer thread enqueues 2 to the queue.
queue.enqueue(3);   // The producer thread enqueues 3 to the queue.
queue.enqueue(4);   // The producer thread is blocked because the queue&#39;s capacity (2) is reached.
queue.dequeue();    // The consumer thread returns 2 from the queue. The producer thread is unblocked and enqueues 4 to the queue.
queue.size();       // 2 elements remaining in the queue. size() is always called at the end of each test case.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong>
3
4
[&quot;BoundedBlockingQueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;enqueue&quot;,&quot;dequeue&quot;,&quot;dequeue&quot;,&quot;dequeue&quot;,&quot;enqueue&quot;]
[[3],[1],[0],[2],[],[],[],[3]]
<strong>Output:</strong>
[1,0,2,1]

<strong>Explanation:</strong>
Number of producer threads = 3
Number of consumer threads = 4

BoundedBlockingQueue queue = new BoundedBlockingQueue(3);   // initialize the queue with capacity = 3.

queue.enqueue(1);   // Producer thread P1 enqueues 1 to the queue.
queue.enqueue(0);   // Producer thread P2 enqueues 0 to the queue.
queue.enqueue(2);   // Producer thread P3 enqueues 2 to the queue.
queue.dequeue();    // Consumer thread C1 calls dequeue.
queue.dequeue();    // Consumer thread C2 calls dequeue.
queue.dequeue();    // Consumer thread C3 calls dequeue.
queue.enqueue(3);   // One of the producer threads enqueues 3 to the queue.
queue.size();       // 1 element remaining in the queue.

Since the number of threads for producer/consumer is greater than 1, we do not know how the threads will be scheduled in the operating system, even though the input seems to imply the ordering. Therefore, any of the output [1,0,2] or [1,2,0] or [0,1,2] or [0,2,1] or [2,0,1] or [2,1,0] will be accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= Number of Prdoucers &lt;= 8</code></li>
	<li><code>1 &lt;= Number of Consumers &lt;= 8</code></li>
	<li><code>1 &lt;= size &lt;= 30</code></li>
	<li><code>0 &lt;= element &lt;= 20</code></li>
	<li>The number of calls to <code>enqueue</code> is <strong>greater than or equal to</strong> the number of calls to <code>dequeue</code>.</li>
	<li>At most <code>40</code> calls will be made to <code>enque</code>, <code>deque</code>, and <code>size</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
