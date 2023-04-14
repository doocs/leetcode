# [2599. Make the Prefix Sum Non-negative](https://leetcode.com/problems/make-the-prefix-sum-non-negative)

[中文文档](/solution/2500-2599/2599.Make%20the%20Prefix%20Sum%20Non-negative/README.md)

## Description

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

## Solutions

**Method 1: Greedy + Priority Queue (Min Heap)**

We use a variable $s$ to record the prefix sum of the current array.

Traverse the array $nums$, add the current element $x$ to the prefix sum $s$. If $x$ is a negative number, add $x$ to the min heap. If $s$ is negative at this time, greedily take out the smallest negative number and subtract it from $s$, and add one to the answer. Finally, return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

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
