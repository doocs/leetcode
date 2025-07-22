---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README.md
rating: 2333
source: 第 217 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 前缀和
---

<!-- problem:start -->

# [1674. 使数组互补的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-make-array-complementary)

[English Version](/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为<strong> 偶数</strong> <code>n</code> 的整数数组 <code>nums</code> 和一个整数 <code>limit</code> 。每一次操作，你可以将 <code>nums</code> 中的任何整数替换为 <code>1</code> 到 <code>limit</code> 之间的另一个整数。</p>

<p>如果对于所有下标 <code>i</code>（<strong>下标从 </strong><code>0</code><strong> 开始</strong>），<code>nums[i] + nums[n - 1 - i]</code> 都等于同一个数，则数组 <code>nums</code> 是 <strong>互补的</strong> 。例如，数组 <code>[1,2,3,4]</code> 是互补的，因为对于所有下标 <code>i</code> ，<code>nums[i] + nums[n - 1 - i] = 5</code> 。</p>

<p>返回使数组 <strong>互补</strong> 的 <strong>最少</strong> 操作次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,3], limit = 4
<strong>输出：</strong>1
<strong>解释：</strong>经过 1 次操作，你可以将数组 nums 变成 [1,2,<strong>2</strong>,3]（加粗元素是变更的数字）：
nums[0] + nums[3] = 1 + 3 = 4.
nums[1] + nums[2] = 2 + 2 = 4.
nums[2] + nums[1] = 2 + 2 = 4.
nums[3] + nums[0] = 3 + 1 = 4.
对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,1], limit = 2
<strong>输出：</strong>2
<strong>解释：</strong>经过 2 次操作，你可以将数组 nums 变成 [<strong>2</strong>,2,2,<strong>2</strong>] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2], limit = 2
<strong>输出：</strong>0
<strong>解释：</strong>nums 已经是互补的。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= limit <= 10<sup>5</sup></code></li>
	<li><code>n</code> 是偶数。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

假设最终的数组中，数对 $\textit{nums}[i]$ 和 $\textit{nums}[n-i-1]$ 的和为 $s$。

我们不妨设 $x$ 为 $\textit{nums}[i]$ 和 $\textit{nums}[n-i-1]$ 的较小值，设 $y$ 为 $\textit{nums}[i]$ 和 $\textit{nums}[n-i-1]$ 的较大值。

对于每一对数，我们有以下几种情况：

-   如果不需要替换，那么 $x + y = s$。
-   如果替换一次，那么 $x + 1 \le s \le y + \textit{limit}$。
-   如果替换两次，那么 $2 \le s \le x$ 或 $y + \textit{limit} + 1 \le s \le 2 \times \textit{limit}$。

即：

-   在 $[2,..x]$ 范围内，需要替换 $2$ 次。
-   在 $[x+1,..x+y-1]$ 范围内，需要替换 $1$ 次。
-   在 $[x+y]$ 时，不需要替换。
-   在 $[x+y+1,..y + \textit{limit}]$ 范围内，需要替换 $1$ 次。
-   在 $[y + \textit{limit} + 1,..2 \times \textit{limit}]$ 范围内，需要替换 $2$ 次。

我们枚举每一个数对，利用差分数组，更新每个数对在不同区间范围内的替换次数。

最后，我们求出下标 $2$ 到 $2 \times \textit{limit}$ 的前缀和中的最小值，即为最少的替换次数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

相似题目：

-   [3224. 使差值相等的最少数组改动次数](https://github.com/doocs/leetcode/blob/main/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        d = [0] * (2 * limit + 2)
        n = len(nums)
        for i in range(n // 2):
            x, y = nums[i], nums[-i - 1]
            if x > y:
                x, y = y, x
            d[2] += 2
            d[x + 1] -= 2
            d[x + 1] += 1
            d[x + y] -= 1
            d[x + y + 1] += 1
            d[y + limit + 1] -= 1
            d[y + limit + 1] += 2
        return min(accumulate(d[2:]))
```

#### Java

```java
class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] d = new int[2 * limit + 2];
        int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            int x = Math.min(nums[i], nums[n - i - 1]);
            int y = Math.max(nums[i], nums[n - i - 1]);
            d[2] += 2;
            d[x + 1] -= 2;
            d[x + 1] += 1;
            d[x + y] -= 1;
            d[x + y + 1] += 1;
            d[y + limit + 1] -= 1;
            d[y + limit + 1] += 2;
        }
        int ans = n;
        for (int i = 2, s = 0; i < d.length; ++i) {
            s += d[i];
            ans = Math.min(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        int d[limit * 2 + 2];
        memset(d, 0, sizeof(d));
        for (int i = 0; i < n / 2; ++i) {
            int x = nums[i], y = nums[n - i - 1];
            if (x > y) {
                swap(x, y);
            }
            d[2] += 2;
            d[x + 1] -= 2;
            d[x + 1] += 1;
            d[x + y] -= 1;
            d[x + y + 1] += 1;
            d[y + limit + 1] -= 1;
            d[y + limit + 1] += 2;
        }
        int ans = n;
        for (int i = 2, s = 0; i <= limit * 2; ++i) {
            s += d[i];
            ans = min(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func minMoves(nums []int, limit int) int {
	n := len(nums)
	d := make([]int, 2*limit+2)
	for i := 0; i < n/2; i++ {
		x, y := nums[i], nums[n-1-i]
		if x > y {
			x, y = y, x
		}
		d[2] += 2
		d[x+1] -= 2
		d[x+1] += 1
		d[x+y] -= 1
		d[x+y+1] += 1
		d[y+limit+1] -= 1
		d[y+limit+1] += 2
	}
	ans, s := n, 0
	for _, x := range d[2:] {
		s += x
		ans = min(ans, s)
	}
	return ans
}
```

#### TypeScript

```ts
function minMoves(nums: number[], limit: number): number {
    const n = nums.length;
    const d: number[] = Array(limit * 2 + 2).fill(0);
    for (let i = 0; i < n >> 1; ++i) {
        const x = Math.min(nums[i], nums[n - 1 - i]);
        const y = Math.max(nums[i], nums[n - 1 - i]);
        d[2] += 2;
        d[x + 1] -= 2;
        d[x + 1] += 1;
        d[x + y] -= 1;
        d[x + y + 1] += 1;
        d[y + limit + 1] -= 1;
        d[y + limit + 1] += 2;
    }
    let ans = n;
    let s = 0;
    for (let i = 2; i < d.length; ++i) {
        s += d[i];
        ans = Math.min(ans, s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
