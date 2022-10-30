# [2454. 下一个更大元素 IV](https://leetcode.cn/problems/next-greater-element-iv)

[English Version](/solution/2400-2499/2454.Next%20Greater%20Element%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的非负整数数组&nbsp;<code>nums</code>&nbsp;。对于&nbsp;<code>nums</code>&nbsp;中每一个整数，你必须找到对应元素的&nbsp;<strong>第二大</strong>&nbsp;整数。</p>

<p>如果&nbsp;<code>nums[j]</code>&nbsp;满足以下条件，那么我们称它为&nbsp;<code>nums[i]</code>&nbsp;的&nbsp;<strong>第二大</strong>&nbsp;整数：</p>

<ul>
	<li><code>j &gt; i</code></li>
	<li><code>nums[j] &gt; nums[i]</code></li>
	<li>恰好存在 <strong>一个</strong>&nbsp;<code>k</code>&nbsp;满足 <code>i &lt; k &lt; j</code>&nbsp;且&nbsp;<code>nums[k] &gt; nums[i]</code>&nbsp;。</li>
</ul>

<p>如果不存在&nbsp;<code>nums[j]</code>&nbsp;，那么第二大整数为&nbsp;<code>-1</code>&nbsp;。</p>

<ul>
	<li>比方说，数组&nbsp;<code>[1, 2, 4, 3]</code>&nbsp;中，<code>1</code>&nbsp;的第二大整数是&nbsp;<code>4</code>&nbsp;，<code>2</code>&nbsp;的第二大整数是&nbsp;<code>3</code>&nbsp;，<code>3</code> 和&nbsp;<code>4</code>&nbsp;的第二大整数是&nbsp;<code>-1</code>&nbsp;。</li>
</ul>

<p>请你返回一个整数数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中<em>&nbsp;</em><code>answer[i]</code>是<em>&nbsp;</em><code>nums[i]</code>&nbsp;的第二大整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,4,0,9,6]
<b>输出：</b>[9,6,6,-1,-1]
<strong>解释：</strong>
下标为 0 处：2 的右边，4 是大于 2 的第一个整数，9 是第二个大于 2 的整数。
下标为 1 处：4 的右边，9 是大于 4 的第一个整数，6 是第二个大于 4 的整数。
下标为 2 处：0 的右边，9 是大于 0 的第一个整数，6 是第二个大于 0 的整数。
下标为 3 处：右边不存在大于 9 的整数，所以第二大整数为 -1 。
下标为 4 处：右边不存在大于 6 的整数，所以第二大整数为 -1 。
所以我们返回 [9,6,6,-1,-1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3]
<b>输出：</b>[-1,-1]
<strong>解释：</strong>
由于每个数右边都没有更大的数，所以我们返回 [-1,-1] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈 + 优先队列（小根堆）**

求下一个更大的元素，可以使用单调栈来实现。我们维护一个栈，然后从左到右遍历数组，如果栈顶元素小于当前元素，则当前元素就是栈顶元素的下一个更大的元素。

这道题的变形是求下一个更大的元素的下一个更大的元素，即第二大的元素。我们观察单调栈求下一个更大元素的过程，每次出栈时，栈顶元素找到了下一个更大的元素，但我们是要为栈顶元素找到第二个更大的元素。次数，我们可以将栈顶元素出栈，放到一个优先队列（小根堆）中。每次遍历数组元素时，先判断当前元素是否大于优先队列的堆顶元素，如果大于，说明堆顶元素找打了第二个更大的元素，更新答案数组，然后弹出堆顶元素，继续判断当前元素是否大于优先队列的堆顶元素，直到堆为空或者当前元素不大于堆顶元素。

接着，执行单调栈的相关操作，弹出栈顶元素后，放入到优先队列中。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
