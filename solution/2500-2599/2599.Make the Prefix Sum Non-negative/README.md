# [2599. Make the Prefix Sum Non-negative](https://leetcode.cn/problems/make-the-prefix-sum-non-negative)

[English Version](/solution/2500-2599/2599.Make%20the%20Prefix%20Sum%20Non-negative/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. You can apply the following operation any number of times:</p>

<ul>
	<li>Pick any element from <code>nums</code> and put it at the end of <code>nums</code>.</li>
</ul>

<p>The prefix sum array of <code>nums</code> is an array <code>prefix</code> of the same length as <code>nums</code> such that <code>prefix[i]</code> is the sum of all the integers <code>nums[j]</code> where <code>j</code> is in the inclusive range <code>[0, i]</code>.</p>

<p>Return <em>the minimum number of operations such that the prefix sum array does not contain negative integers</em>. The test cases are generated such that it is always possible to make the prefix sum array non-negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-5,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> we do not need to do any operations.
The array is [2,3,-5,4]. The prefix sum array is [2, 5, 0, 4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,-5,-2,6]
<strong>Output:</strong> 1
<strong>Explanation:</strong> we can do one operation on index 1.
The array after the operation is [3,-2,6,-5]. The prefix sum array is [3, 1, 7, 2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列（小根堆）**

我们用变量 $s$ 记录当前数组的前缀和。

遍历数组 $nums$，将当前元素 $x$ 加入前缀和 $s$ 中，如果 $x$ 为负数，则将 $x$ 加入小根堆中。如果此时 $s$ 为负数，我们贪心地取出最小的负数，将其从 $s$ 中减去，同时将答案加一。最终返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makePrefSumNonNegative(self, nums: List[int]) -> int:
        h = []
        ans = s = 0
        for x in nums:
            s += x
            if x < 0:
                heappush(h, x)
            while s < 0:
                s -= heappop(h)
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int makePrefSumNonNegative(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        long s = 0;
        for (int x : nums) {
            s += x;
            if (x < 0) {
                pq.offer(x);
            }
            while (s < 0) {
                s -= pq.poll();
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int makePrefSumNonNegative(vector<int>& nums) {
        priority_queue<int, vector<int>, greater<int>> pq;
        int ans = 0;
        long long s = 0;
        for (int& x : nums) {
            s += x;
            if (x < 0) {
                pq.push(x);
            }
            while (s < 0) {
                s -= pq.top();
                pq.pop();
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func makePrefSumNonNegative(nums []int) (ans int) {
	pq := hp{}
	s := 0
	for _, x := range nums {
		s += x
		if x < 0 {
			heap.Push(&pq, x)
		}
		for s < 0 {
			s -= heap.Pop(&pq).(int)
			ans++
		}
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **TypeScript**

```ts
function makePrefSumNonNegative(nums: number[]): number {
    const pq = new MinPriorityQueue();
    let ans = 0;
    let s = 0;
    for (const x of nums) {
        s += x;
        if (x < 0) {
            pq.enqueue(x);
        }
        while (s < 0) {
            s -= pq.dequeue().element;
            ++ans;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
