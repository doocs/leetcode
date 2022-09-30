# [313. Super Ugly Number](https://leetcode.com/problems/super-ugly-number)

[中文文档](/solution/0300-0399/0313.Super%20Ugly%20Number/README.md)

## Description

<p>A <strong>super ugly number</strong> is a positive integer whose prime factors are in the array <code>primes</code>.</p>

<p>Given an integer <code>n</code> and an array of integers <code>primes</code>, return <em>the</em> <code>n<sup>th</sup></code> <em><strong>super ugly number</strong></em>.</p>

<p>The <code>n<sup>th</sup></code> <strong>super ugly number</strong> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> signed integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 12, primes = [2,7,13,19]
<strong>Output:</strong> 32
<strong>Explanation:</strong> [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, primes = [2,3,5]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= primes.length &lt;= 100</code></li>
	<li><code>2 &lt;= primes[i] &lt;= 1000</code></li>
	<li><code>primes[i]</code> is <strong>guaranteed</strong> to be a prime number.</li>
	<li>All the values of <code>primes</code> are <strong>unique</strong> and sorted in <strong>ascending order</strong>.</li>
</ul>

## Solutions

Priority Queue.

<!-- tabs:start -->

### **Python3**

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
