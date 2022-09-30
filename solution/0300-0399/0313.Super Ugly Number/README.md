# [313. 超级丑数](https://leetcode.cn/problems/super-ugly-number)

[English Version](/solution/0300-0399/0313.Super%20Ugly%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>超级丑数</strong> 是一个正整数，并满足其所有质因数都出现在质数数组 <code>primes</code> 中。</p>

<p>给你一个整数 <code>n</code> 和一个整数数组 <code>primes</code> ，返回第 <code>n</code> 个 <strong>超级丑数</strong> 。</p>

<p>题目数据保证第 <code>n</code> 个 <strong>超级丑数</strong> 在 <strong>32-bit</strong> 带符号整数范围内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 12, <code>primes</code> = <code>[2,7,13,19]</code>
<strong>输出：</strong>32 
<strong>解释：</strong>给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, primes = [2,3,5]
<strong>输出：</strong>1
<strong>解释：</strong>1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
</pre>

&nbsp;

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= primes.length &lt;= 100</code></li>
	<li><code>2 &lt;= primes[i] &lt;= 1000</code></li>
	<li>题目数据<strong> 保证</strong> <code>primes[i]</code> 是一个质数</li>
	<li><code>primes</code> 中的所有值都 <strong>互不相同</strong> ，且按 <strong>递增顺序</strong> 排列</li>
</ul>
</div>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列**

根据题目要求，模拟第 1 ～ n 个丑数，显然任意一个丑数 `ugly[i]` 乘以 `primes` 数组中的质数 `primes[i]` 仍旧是丑数。已知 `ugly[1]=1`，`ugly[2] = min(ugly[1]*primes[i])`，则 `ugly[3]` 应该等于集合 `ugly[2]*primes[i], ugly[1]*primes[i]` 中排除 `ugly[2]` 之后的最小值，依次类推可以得到 `ugly[n]` 的值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from queue import PriorityQueue


class Solution:
    def nthSuperUglyNumber(self, n: int, primes: List[int]) -> int:
        ugly, pq, p = [0]*(n+1), PriorityQueue(), 2
        ugly[1] = 1
        for prime in primes:
            pq.put([prime, prime, 2])

        while p <= n:
            top = pq.get()
            if top[0] != ugly[p-1]:
                ugly[p], p = top[0], p+1
            top[0], top[2] = ugly[top[2]]*top[1], top[2]+1
            pq.put(top)
        return ugly[n]
```

### **Go**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```go
type Ugly struct{ value, prime, index int }
type Queue []Ugly

func (u Queue) Len() int            { return len(u) }
func (u Queue) Swap(i, j int)       { u[i], u[j] = u[j], u[i] }
func (u Queue) Less(i, j int) bool  { return u[i].value < u[j].value }
func (u *Queue) Push(v interface{}) { *u = append(*u, v.(Ugly)) }
func (u *Queue) Pop() interface{} {
	old, x := *u, (*u)[len(*u)-1]
	*u = old[:len(old)-1]
	return x
}

func nthSuperUglyNumber(n int, primes []int) int {
	ugly, pq, p := make([]int, n+1), &Queue{}, 2
	ugly[1] = 1
	heap.Init(pq)
	for _, v := range primes {
		heap.Push(pq, Ugly{value: v, prime: v, index: 2})
	}
	for p <= n {
		top := heap.Pop(pq).(Ugly)
		if ugly[p-1] != top.value {
			ugly[p], p = top.value, p+1
		}
		top.value, top.index = ugly[top.index]*top.prime, top.index+1
		heap.Push(pq, top)
	}
	return ugly[n]
}
```

### **...**

```

```

<!-- tabs:end -->
