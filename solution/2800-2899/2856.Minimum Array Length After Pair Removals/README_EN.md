# [2856. Minimum Array Length After Pair Removals](https://leetcode.com/problems/minimum-array-length-after-pair-removals)

[中文文档](/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <strong>sorted</strong> array of integers <code>nums</code>.</p>

<p>You can perform the following operation any number of times:</p>

<ul>
	<li>Choose <strong>two</strong> indices, <code>i</code> and <code>j</code>, where <code>i &lt; j</code>, such that <code>nums[i] &lt; nums[j]</code>.</li>
	<li>Then, remove the elements at indices <code>i</code> and <code>j</code> from <code>nums</code>. The remaining elements retain their original order, and the array is re-indexed.</li>
</ul>

<p>Return <em>an integer that denotes the <strong>minimum</strong> length of </em><code>nums</code><em> after performing the operation any number of times (<strong>including zero</strong>).</em></p>

<p>Note that <code>nums</code> is sorted in <strong>non-decreasing</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,4,9]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Initially, nums = [1, 3, 4, 9].
In the first operation, we can choose index 0 and 1 because nums[0] &lt; nums[1] &lt;=&gt; 1 &lt; 3.
Remove indices 0 and 1, and nums becomes [4, 9].
For the next operation, we can choose index 0 and 1 because nums[0] &lt; nums[1] &lt;=&gt; 4 &lt; 9.
Remove indices 0 and 1, and nums becomes an empty array [].
Hence, the minimum length achievable is 0.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,6,9]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Initially, nums = [2, 3, 6, 9]. 
In the first operation, we can choose index 0 and 2 because nums[0] &lt; nums[2] &lt;=&gt; 2 &lt; 6. 
Remove indices 0 and 2, and nums becomes [3, 9]. 
For the next operation, we can choose index 0 and 1 because nums[0] &lt; nums[1] &lt;=&gt; 3 &lt; 9. 
Remove indices 0 and 1, and nums becomes an empty array []. 
Hence, the minimum length achievable is 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Initially, nums = [1, 1, 2].
In an operation, we can choose index 0 and 2 because nums[0] &lt; nums[2] &lt;=&gt; 1 &lt; 2. 
Remove indices 0 and 2, and nums becomes [1]. 
It is no longer possible to perform an operation on the array. 
Hence, the minimum achievable length is 1. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minLengthAfterRemovals(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        pq = [-x for x in cnt.values()]
        heapify(pq)
        ans = len(nums)
        while len(pq) > 1:
            x, y = -heappop(pq), -heappop(pq)
            x -= 1
            y -= 1
            if x > 0:
                heappush(pq, -x)
            if y > 0:
                heappush(pq, -y)
            ans -= 2
        return ans
```

### **Java**

```java
class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : cnt.values()) {
            pq.offer(x);
        }
        int ans = nums.size();
        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();
            x--;
            y--;
            if (x > 0) {
                pq.offer(x);
            }
            if (y > 0) {
                pq.offer(y);
            }
            ans -= 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minLengthAfterRemovals(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        priority_queue<int> pq;
        for (auto& [_, v] : cnt) {
            pq.push(v);
        }
        int ans = nums.size();
        while (pq.size() > 1) {
            int x = pq.top();
            pq.pop();
            int y = pq.top();
            pq.pop();
            x--;
            y--;
            if (x > 0) {
                pq.push(x);
            }
            if (y > 0) {
                pq.push(y);
            }
            ans -= 2;
        }
        return ans;
    }
};
```

### **Go**

```go
func minLengthAfterRemovals(nums []int) int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	h := &hp{}
	for _, x := range cnt {
		h.push(x)
	}
	ans := len(nums)
	for h.Len() > 1 {
		x, y := h.pop(), h.pop()
		if x > 1 {
			h.push(x - 1)
		}
		if y > 1 {
			h.push(y - 1)
		}
		ans -= 2
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

### **TypeScript**

```ts
function minLengthAfterRemovals(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const pq = new MaxPriorityQueue();
    for (const [_, v] of cnt) {
        pq.enqueue(v);
    }
    let ans = nums.length;
    while (pq.size() > 1) {
        let x = pq.dequeue().element;
        let y = pq.dequeue().element;
        if (--x > 0) {
            pq.enqueue(x);
        }
        if (--y > 0) {
            pq.enqueue(y);
        }
        ans -= 2;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
