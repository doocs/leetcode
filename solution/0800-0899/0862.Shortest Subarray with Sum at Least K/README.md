# [862. 和至少为 K 的最短子数组](https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k)

[English Version](/solution/0800-0899/0862.Shortest%20Subarray%20with%20Sum%20at%20Least%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，找出 <code>nums</code> 中和至少为 <code>k</code> 的 <strong>最短非空子数组</strong> ，并返回该子数组的长度。如果不存在这样的 <strong>子数组</strong> ，返回 <code>-1</code> 。</p>

<p><strong>子数组</strong> 是数组中 <strong>连续</strong> 的一部分。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1], k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2], k = 4
<strong>输出：</strong>-1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,-1,2], k = 3
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 单调队列**

题目要求找到一个最短的子数组，使得子数组的和大于等于 $k$。不难想到，可以使用前缀和快速计算子数组的和。

我们用一个长度为 $n+1$ 的数组 $s[i]$ 表示数组 `nums` 前 $i$ 个元素的和。另外，我们需要维护一个严格单调递增的队列 $q$，队列中存储的是前缀和数组 $s[i]$ 的下标。注意，这里的单调递增是指下标对应的前缀和的大小，而不是下标的大小。

为什么存的是下标呢？这是为了方便计算子数组的长度。那为什么队列严格单调递增？我们可以用反证法来说明。

假设队列元素非严格单调递增，也即是说，存在下标 $i$ 和 $j$，满足 $i < j$，且 $s[i] \geq s[j]$。

当遍历到下标 $k$，其中 $i \lt j \lt k \leq n$，此时 $s[k]-s[j] \geq s[k]-s[i]$，且 $nums[j..k-1]$ 的长度小于 $nums[i..k-1]$ 的长度。由于下标 $j$ 的存在，子数组 $nums[i..k-1]$ 一定不是最优解，队列中的下标 $i$ 是不必要的，需要将其移除。因此，队列中的元素一定严格单调递增。

回到这道题目上，我们遍历前缀和数组 $s$，对于遍历到的下标 $i$，如果 $s[i] - s[q.front] \geq k$，说明当前遇到了一个可行解，我们可以更新答案。此时，我们需要将队首元素出队，直到队列为空或者 $s[i] - s[q.front] \lt k$ 为止。

如果此时队列不为空，为了维持队列的严格单调递增，我们还需要判断队尾元素是否需要出队，如果 $s[q.back] \geq s[i]$，则需要循环将队尾元素出队，直到队列为空或者 $s[q.back] \lt s[i]$ 为止。然后，我们将下标 $i$ 入队。

遍历结束，如果我们没有找到可行解，那么返回 $-1$。否则，返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestSubarray(self, nums: List[int], k: int) -> int:
        s = list(accumulate(nums, initial=0))
        q = deque()
        ans = inf
        for i, v in enumerate(s):
            while q and v - s[q[0]] >= k:
                ans = min(ans, i - q.popleft())
            while q and s[q[-1]] >= v:
                q.pop()
            q.append(i)
        return -1 if ans == inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            while (!q.isEmpty() && s[i] - s[q.peek()] >= k) {
                ans = Math.min(ans, i - q.poll());
            }
            while (!q.isEmpty() && s[q.peekLast()] >= s[i]) {
                q.pollLast();
            }
            q.offer(i);
        }
        return ans > n ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<long> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        deque<int> q;
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            while (!q.empty() && s[i] - s[q.front()] >= k) {
                ans = min(ans, i - q.front());
                q.pop_front();
            }
            while (!q.empty() && s[q.back()] >= s[i]) q.pop_back();
            q.push_back(i);
        }
        return ans > n ? -1 : ans;
    }
};
```

### **Go**

```go
func shortestSubarray(nums []int, k int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	q := []int{}
	ans := n + 1
	for i, v := range s {
		for len(q) > 0 && v-s[q[0]] >= k {
			ans = min(ans, i-q[0])
			q = q[1:]
		}
		for len(q) > 0 && s[q[len(q)-1]] >= v {
			q = q[:len(q)-1]
		}
		q = append(q, i)
	}
	if ans > n {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
