# [1354. 多次求和构造目标数组](https://leetcode.cn/problems/construct-target-array-with-multiple-sums)

[English Version](/solution/1300-1399/1354.Construct%20Target%20Array%20With%20Multiple%20Sums/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>target</code> 。一开始，你有一个数组&nbsp;<code>A</code> ，它的所有元素均为 1 ，你可以执行以下操作：</p>

<ul>
	<li>令&nbsp;<code>x</code>&nbsp;为你数组里所有元素的和</li>
	<li>选择满足&nbsp;<code>0 &lt;= i &lt; target.size</code>&nbsp;的任意下标&nbsp;<code>i</code>&nbsp;，并让&nbsp;<code>A</code>&nbsp;数组里下标为&nbsp;<code>i</code>&nbsp;处的值为&nbsp;<code>x</code>&nbsp;。</li>
	<li>你可以重复该过程任意次</li>
</ul>

<p>如果能从&nbsp;<code>A</code>&nbsp;开始构造出目标数组&nbsp;<code>target</code>&nbsp;，请你返回 True ，否则返回 False 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>target = [9,3,5]
<strong>输出：</strong>true
<strong>解释：</strong>从 [1, 1, 1] 开始
[1, 1, 1], 和为 3 ，选择下标 1
[1, 3, 1], 和为 5， 选择下标 2
[1, 3, 5], 和为 9， 选择下标 0
[9, 3, 5] 完成
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>target = [1,1,1,2]
<strong>输出：</strong>false
<strong>解释：</strong>不可能从 [1,1,1,1] 出发构造目标数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>target = [8,5]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>N == target.length</code></li>
	<li><code>1 &lt;= target.length&nbsp;&lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;= target[i] &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：逆向构造 + 优先队列（大根堆）**

我们发现，如果从数组 $arr$ 开始正向构造目标数组 $target$，每次都不好确定选择哪个下标 $i$，问题比较复杂。而如果我们从数组 $target$ 开始逆向构造，每次构造都一定是选择当前数组中最大的元素，这样就可以保证每次构造都是唯一的，问题比较简单。

因此，我们可以使用优先队列（大根堆）来存储数组 $target$ 中的元素，用一个变量 $s$ 记录数组 $target$ 中所有元素的和。每次从优先队列中取出最大的元素 $mx$，计算当前数组中除 $mx$ 以外的所有元素之和 $t$，如果 $t \lt 1$ 或者 $mx - t \lt 1$，则说明无法构造目标数组 $target$，返回 `false`。否则，我们计算 $mx \bmod t$，如果 $mx \bmod t = 0$，则令 $x = t$，否则令 $x = mx \bmod t$，将 $x$ 加入优先队列中，并更新 $s$ 的值，重复上述操作，直到优先队列中的所有元素都变为 $1$，此时返回 `true`。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $target$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPossible(self, target: List[int]) -> bool:
        s = sum(target)
        pq = [-x for x in target]
        heapify(pq)
        while -pq[0] > 1:
            mx = -heappop(pq)
            t = s - mx
            if t == 0 or mx - t < 1:
                return False
            x = (mx % t) or t
            heappush(pq, -x)
            s = s - mx + x
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPossible(int[] target) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long s = 0;
        for (int x : target) {
            s += x;
            pq.offer((long) x);
        }
        while (pq.peek() > 1) {
            long mx = pq.poll();
            long t = s - mx;
            if (t == 0 || mx - t < 1) {
                return false;
            }
            long x = mx % t;
            if (x == 0) {
                x = t;
            }
            pq.offer(x);
            s = s - mx + x;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPossible(vector<int>& target) {
        priority_queue<int> pq;
        long long s = 0;
        for (int i = 0; i < target.size(); i++) {
            s += target[i];
            pq.push(target[i]);
        }
        while (pq.top() != 1) {
            int mx = pq.top();
            pq.pop();
            long long t = s - mx;
            if (t < 1 || mx - t < 1) {
                return false;
            }
            int x = mx % t;
            if (x == 0) {
                x = t;
            }
            pq.push(x);
            s = s - mx + x;
        }
        return true;
    }
};
```

### **Go**

```go
func isPossible(target []int) bool {
	pq := &hp{target}
	s := 0
	for _, x := range target {
		s += x
	}
	heap.Init(pq)
	for target[0] > 1 {
		mx := target[0]
		t := s - mx
		if t < 1 || mx-t < 1 {
			return false
		}
		x := mx % t
		if x == 0 {
			x = t
		}
		target[0] = x
		heap.Fix(pq, 0)
		s = s - mx + x
	}
	return true
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Pop() (_ any)         { return }
func (hp) Push(any)             {}
```

### **TypeScript**

```ts
function isPossible(target: number[]): boolean {
    const pq = new MaxPriorityQueue();
    let s = 0;
    for (const x of target) {
        s += x;
        pq.enqueue(x);
    }
    while (pq.front().element > 1) {
        const mx = pq.dequeue().element;
        const t = s - mx;
        if (t < 1 || mx - t < 1) {
            return false;
        }
        const x = mx % t || t;
        pq.enqueue(x);
        s = s - mx + x;
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
