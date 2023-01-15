# [2534. 通过门的时间](https://leetcode.cn/problems/time-taken-to-cross-the-door)

[English Version](/solution/2500-2599/2534.Time%20Taken%20to%20Cross%20the%20Door/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>n</code> 个人，按从 <code>0</code> 到 <code>n - 1</code> 编号。现在有一扇门，每个人只能通过门进入或离开一次，耗时一秒。</p>

<p>给你一个 <strong>非递减顺序</strong> 排列的整数数组 <code>arrival</code> ，数组长度为 <code>n</code> ，其中 <code>arrival[i]</code> 是第 <code>i</code> 个人到达门前的时间。另给你一个长度为 <code>n</code> 的数组 <code>state</code> ，其中 <code>state[i]</code> 是 <code>0</code> 则表示第 <code>i</code> 个人希望进入这扇门，是 <code>1</code> 则表示 TA 想要离开这扇门。</p>

<p>如果 <strong>同时</strong> 有两个或更多人想要使用这扇门，则必须遵循以下规则：</p>

<ul>
	<li>如果前一秒 <strong>没有</strong> 使用门，那么想要 <strong>离开</strong> 的人会先离开。</li>
	<li>如果前一秒使用门 <strong>进入</strong> ，那么想要 <strong>进入</strong> 的人会先进入。</li>
	<li>如果前一秒使用门 <strong>离开</strong> ，那么想要 <strong>离开</strong> 的人会先离开。</li>
	<li>如果多个人都想朝同一方向走（都进入或都离开），编号最小的人会先通过门。</li>
</ul>

<p>返回一个长度为 <code>n</code> 的数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer[i]</code><em> </em>是第 <code>i</code> 个人通过门的时刻（秒）。</p>
<strong>注意：</strong>

<ul>
	<li>每秒只有一个人可以通过门。</li>
	<li>为遵循上述规则，一个人可以在到达门附近后等待，而不通过门进入或离开。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arrival = [0,1,1,2,4], state = [0,1,0,0,1]
<strong>输出：</strong>[0,3,1,2,4]
<strong>解释：</strong>每秒发生的情况如下：
- t = 0 ：第 0 个人是唯一一个想要进入的人，所以 TA 可以直接进入。
- t = 1 ：第 1 个人想要离开，第 2 个人想要进入。因为前一秒有人使用门进入，所以第 2 个人先进入。
- t = 2 ：第 1 个人还是想要离开，第 3 个人想要进入。因为前一秒有人使用门进入，所以第 3 个人先进入。
- t = 3 ：第 1 个人是唯一一个想要离开的人，所以 TA 可以直接离开。
- t = 4 ：第 4 个人是唯一一个想要进入的人，所以 TA 可以直接离开。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arrival = [0,0,0], state = [1,0,1]
<strong>输出：</strong>[0,2,1]
<strong>解释：</strong>每秒发生的情况如下：
- t = 0 ：第 1 个人想要进入，但是第 0 个人和第 2 个人都想要离开。因为前一秒没有使用门，所以想要离开的人会先离开。又因为第 0 个人的编号更小，所以 TA 先离开。
- t = 1 ：第 1 个人想要进入，第 2 个人想要离开。因为前一秒有人使用门离开，所以第 2 个人先离开。
- t = 2 ：第 1 个人是唯一一个想要进入的人，所以 TA 可以直接进入。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == arrival.length == state.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arrival[i] &lt;= n</code></li>
	<li><code>arrival</code> 按 <strong>非递减顺序</strong> 排列</li>
	<li><code>state[i]</code> 为 <code>0</code> 或 <code>1</code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
