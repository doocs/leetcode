# [2454. Next Greater Element IV](https://leetcode.com/problems/next-greater-element-iv)

[中文文档](/solution/2400-2499/2454.Next%20Greater%20Element%20IV/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of non-negative integers <code>nums</code>. For each integer in <code>nums</code>, you must find its respective <strong>second greater</strong> integer.</p>

<p>The <strong>second greater</strong> integer of <code>nums[i]</code> is <code>nums[j]</code> such that:</p>

<ul>
	<li><code>j &gt; i</code></li>
	<li><code>nums[j] &gt; nums[i]</code></li>
	<li>There exists <strong>exactly one</strong> index <code>k</code> such that <code>nums[k] &gt; nums[i]</code> and <code>i &lt; k &lt; j</code>.</li>
</ul>

<p>If there is no such <code>nums[j]</code>, the second greater integer is considered to be <code>-1</code>.</p>

<ul>
	<li>For example, in the array <code>[1, 2, 4, 3]</code>, the second greater integer of <code>1</code> is <code>4</code>, <code>2</code> is <code>3</code>,&nbsp;and that of <code>3</code> and <code>4</code> is <code>-1</code>.</li>
</ul>

<p>Return<em> an integer array </em><code>answer</code><em>, where </em><code>answer[i]</code><em> is the second greater integer of </em><code>nums[i]</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,0,9,6]
<strong>Output:</strong> [9,6,6,-1,-1]
<strong>Explanation:</strong>
0th index: 4 is the first integer greater than 2, and 9 is the second integer greater than 2, to the right of 2.
1st index: 9 is the first, and 6 is the second integer greater than 4, to the right of 4.
2nd index: 9 is the first, and 6 is the second integer greater than 0, to the right of 0.
3rd index: There is no integer greater than 9 to its right, so the second greater integer is considered to be -1.
4th index: There is no integer greater than 6 to its right, so the second greater integer is considered to be -1.
Thus, we return [9,6,6,-1,-1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3]
<strong>Output:</strong> [-1,-1]
<strong>Explanation:</strong>
We return [-1,-1] since neither integer has any integer greater than it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def secondGreaterElement(self, nums: List[int]) -> List[int]:
        stk = []
        q = []
        ans = [-1] * len(nums)
        for i, v in enumerate(nums):
            while q and q[0][0] < v:
                ans[q[0][1]] = v
                heappop(q)
            while stk and nums[stk[-1]] < v:
                heappush(q, (nums[stk[-1]], stk.pop()))
            stk.append(i)
        return ans
```

### **Java**

```java
class Solution {
    public int[] secondGreaterElement(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!q.isEmpty() && q.peek()[0] < v) {
                ans[q.peek()[1]] = v;
                q.poll();
            }
            while (!stk.isEmpty() && nums[stk.peek()] < v) {
                q.offer(new int[] {nums[stk.peek()], stk.pop()});
            }
            stk.push(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    vector<int> secondGreaterElement(vector<int>& nums) {
        stack<int> stk;
        priority_queue<pii, vector<pii>, greater<pii>> q;
        int n = nums.size();
        vector<int> ans(n, -1);
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!q.empty() && q.top().first < v) {
                ans[q.top().second] = v;
                q.pop();
            }
            while (!stk.empty() && nums[stk.top()] < v) {
                q.push({nums[stk.top()], stk.top()});
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func secondGreaterElement(nums []int) []int {
	stk := []int{}
	q := hp{}
	n := len(nums)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	for i, v := range nums {
		for len(q) > 0 && q[0].v < v {
			ans[q[0].i] = v
			heap.Pop(&q)
		}
		for len(stk) > 0 && nums[stk[len(stk)-1]] < v {
			heap.Push(&q, pair{nums[stk[len(stk)-1]], stk[len(stk)-1]})
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}

type pair struct{ v, i int }

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v < b.v
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
