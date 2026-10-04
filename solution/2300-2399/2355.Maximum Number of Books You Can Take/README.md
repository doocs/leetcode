---
comments: true
difficulty: 困难
tags:
    - 栈
    - 数组
    - 动态规划
    - 单调栈
---

<!-- problem:start -->

# [2355. 你能拿走的最大图书数量 🔒](https://leetcode.cn/problems/maximum-number-of-books-you-can-take)

[English Version](/solution/2300-2399/2355.Maximum%20Number%20of%20Books%20You%20Can%20Take/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的<b>&nbsp;下标从 0 开始&nbsp;</b>的整数数组 <code>books</code>，其中 <code>books[i]</code> 表示书架的第 <code>i</code> 个书架上的书的数量。</p>

<p>你要从书架&nbsp;<code>l</code> 到 <code>r</code> 的一个&nbsp;<strong>连续&nbsp;</strong>的部分中取书，其中 <code>0 &lt;= l &lt;= r &lt; n</code>。对于 <code>l &lt;= i &lt; r</code> 范围内的每个索引 <code>i</code>，你从书架 <code>i</code>&nbsp;取书的数量必须&nbsp;<strong>严格小于 </strong>你从书架 <code>i + 1</code> 取书的数量。</p>

<p>返回<em>你能从书架上拿走的书的&nbsp;<strong>最大&nbsp;</strong>数量。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> books = [8,5,2,7,9]
<strong>输出:</strong> 19
<strong>解释:</strong>
- 从书架 1 上取 1 本书。
- 从书架 2 上取 2 本书。
- 从书架 3 上取 7 本书
- 从书架 4 上取 9 本书
你已经拿了19本书，所以返回 19。
可以证明 19 本是你所能拿走的书的最大数量。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> books = [7,0,3,4,5]
<strong>输出:</strong> 12
<strong>解释:</strong>
- 从书架 2 上取 3 本书。
- 从书架 3 上取 4 本书。
- 从书架 4 上取 5 本书。
你已经拿了 12 本书，所以返回 12。
可以证明 12 本是你所能拿走的书的最大数量。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> books = [8,2,3,7,3,4,0,1,4,3]
<strong>输出:</strong> 13
<strong>解释:</strong>
- 从书架 0 上取 1 本书。
- 从书架 1 上取 2 本书。
- 从书架 2 上取 3 本书。
- 从书架 3 上取 7 本书。
你已经拿了 13 本书，所以返回 13。
可以证明 13 本是你所能拿走的书的最大数量。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= books.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= books[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈 + 动态规划

<!-- thinking:start -->

> **思考**
>
> 从连续书架取书，且往左每层至少少一本。枚举右端后若再暴力找断裂点，总复杂度过高。
>
> 令 $nums[i]=books[i]-i$，断裂发生在左侧第一个更小的 $nums[j]$。单调栈预处理 $left[i]$，再以 $dp[i]$ 表示以 $i$ 为右端的最大取书量：中间一段是等差数列，左侧接上 $dp[j]$。

<!-- thinking:end -->

我们定义 $dp[i]$ 表示以书架 $i$ 为右端点时能取走的书的最大数量。令 $nums[i] = books[i] - i$。若往左的严格递减在某个 $j$ 处断开（$0 \le j < i$），则 $books[j] < books[i] - (i - j)$，也就是 $nums[j] < nums[i]$。用单调栈为每个位置求出左侧第一个严格更小的 $nums$，记为 $j$；不存在时 $j = -1$。

从 $j + 1$ 到 $i$ 实际取走的本数是公差为 $1$ 的等差数列。其长度为 $cnt = \min(books[i], i - j)$，末项为 $books[i]$，首项为 $u = books[i] - cnt + 1$，和为 $s = (u + books[i]) \times cnt / 2$。于是 $dp[i] = s$，若 $j \ne -1$ 再加 $dp[j]$。

答案为 $\max\limits_{i} dp[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBooks(self, books: List[int]) -> int:
        nums = [v - i for i, v in enumerate(books)]
        n = len(nums)
        left = [-1] * n
        stk = []
        for i, v in enumerate(nums):
            while stk and nums[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        ans = 0
        dp = [0] * n
        dp[0] = books[0]
        for i, v in enumerate(books):
            j = left[i]
            cnt = min(v, i - j)
            u = v - cnt + 1
            s = (u + v) * cnt // 2
            dp[i] = s + (0 if j == -1 else dp[j])
            ans = max(ans, dp[i])
        return ans
```

#### Java

```java
class Solution {
    public long maximumBooks(int[] books) {
        int n = books.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = books[i] - i;
        }
        int[] left = new int[n];
        Arrays.fill(left, -1);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        long ans = 0;
        long[] dp = new long[n];
        dp[0] = books[0];
        for (int i = 0; i < n; ++i) {
            int j = left[i];
            int v = books[i];
            int cnt = Math.min(v, i - j);
            int u = v - cnt + 1;
            long s = (long) (u + v) * cnt / 2;
            dp[i] = s + (j == -1 ? 0 : dp[j]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
using ll = long long;

class Solution {
public:
    long long maximumBooks(vector<int>& books) {
        int n = books.size();
        vector<int> nums(n);
        for (int i = 0; i < n; ++i) nums[i] = books[i] - i;
        vector<int> left(n, -1);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && nums[stk.top()] >= nums[i]) stk.pop();
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        vector<ll> dp(n);
        dp[0] = books[0];
        ll ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = books[i];
            int j = left[i];
            int cnt = min(v, i - j);
            int u = v - cnt + 1;
            ll s = 1ll * (u + v) * cnt / 2;
            dp[i] = s + (j == -1 ? 0 : dp[j]);
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumBooks(books []int) int64 {
	n := len(books)
	nums := make([]int, n)
	left := make([]int, n)
	for i, v := range books {
		nums[i] = v - i
		left[i] = -1
	}
	stk := []int{}
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	dp := make([]int, n)
	dp[0] = books[0]
	ans := 0
	for i, v := range books {
		j := left[i]
		cnt := min(v, i-j)
		u := v - cnt + 1
		s := (u + v) * cnt / 2
		dp[i] = s
		if j != -1 {
			dp[i] += dp[j]
		}
		ans = max(ans, dp[i])
	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
