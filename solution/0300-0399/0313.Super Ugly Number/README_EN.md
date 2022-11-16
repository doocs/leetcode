# [313. Super Ugly Number](https://leetcode.com/problems/super-ugly-number)

[中文文档](/solution/0300-0399/0313.Super%20Ugly%20Number/README.md)

## Description

<p>A <strong>super ugly number</strong> is a positive integer whose prime factors are in the array <code>primes</code>.</p>

<p>Given an integer <code>n</code> and an array of integers <code>primes</code>, return <em>the</em> <code>n<sup>th</sup></code> <em><strong>super ugly number</strong></em>.</p>

<p>The <code>n<sup>th</sup></code> <strong>super ugly number</strong> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> signed integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 12, primes = [2,7,13,19]
<strong>Output:</strong> 32
<strong>Explanation:</strong> [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
</pre>

<p><strong class="example">Example 2:</strong></p>

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
class Solution:
    def nthSuperUglyNumber(self, n: int, primes: List[int]) -> int:
        q = [1]
        x = 0
        mx = (1 << 31) - 1
        for _ in range(n):
            x = heappop(q)
            for k in primes:
                if x <= mx // k:
                    heappush(q, k * x)
                if x % k == 0:
                    break
        return x
```

### **Java**

```java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(1);
        int x = 0;
        while (n-- > 0) {
            x = q.poll();
            while (!q.isEmpty() && q.peek() == x) {
                q.poll();
            }
            for (int k : primes) {
                if (k <= Integer.MAX_VALUE / x) {
                    q.offer(k * x);
                }
                if (x % k == 0) {
                    break;
                }
            }
        }
        return x;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int nthSuperUglyNumber(int n, vector<int>& primes) {
        priority_queue<int, vector<int>, greater<int>> q;
        q.push(1);
        int x = 0;
        while (n--) {
            x = q.top();
            q.pop();
            for (int& k : primes) {
                if (x <= INT_MAX / k) {
                    q.push(k * x);
                }
                if (x % k == 0) {
                    break;
                }
            }
        }
        return x;
    }
};
```

### **Go**

```go
func nthSuperUglyNumber(n int, primes []int) (x int) {
	q := hp{[]int{1}}
	for n > 0 {
		n--
		x = heap.Pop(&q).(int)
		for _, k := range primes {
			if x <= math.MaxInt32/k {
				heap.Push(&q, k*x)
			}
			if x%k == 0 {
				break
			}
		}
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

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
