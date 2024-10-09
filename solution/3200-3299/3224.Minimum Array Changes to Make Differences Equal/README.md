---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README.md
rating: 1996
source: 第 135 场双周赛 Q3
tags:
    - 数组
    - 哈希表
    - 前缀和
---

<!-- problem:start -->

# [3224. 使差值相等的最少数组改动次数](https://leetcode.cn/problems/minimum-array-changes-to-make-differences-equal)

[English Version](/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，<code>n</code>&nbsp;是 <strong>偶数</strong>&nbsp;，同时给你一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以对数组进行一些操作。每次操作中，你可以将数组中 <strong>任一</strong>&nbsp;元素替换为 <code>0</code>&nbsp;到 <code>k</code>&nbsp;之间的<strong>&nbsp;任一</strong>&nbsp;整数。</p>

<p>执行完所有操作以后，你需要确保最后得到的数组满足以下条件：</p>

<ul>
	<li>存在一个整数 <code>X</code>&nbsp;，满足对于所有的&nbsp;<code>(0 &lt;= i &lt; n)</code>&nbsp;都有&nbsp;<code>abs(a[i] - a[n - i - 1]) = X</code>&nbsp;。</li>
</ul>

<p>请你返回满足以上条件 <strong>最少</strong>&nbsp;修改次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,0,1,2,4,3], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong><br />
我们可以执行以下操作：</p>

<ul>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;变为 2 ，结果数组为&nbsp;<code>nums = [1,<em><strong>2</strong></em>,1,2,4,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[3]</code>&nbsp;变为 3 ，结果数组为&nbsp;<code>nums = [1,2,1,<em><strong>3</strong></em>,4,3]</code>&nbsp;。</li>
</ul>

<p>整数&nbsp;<code>X</code>&nbsp;为 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [0,1,2,3,3,6,5,4], k = 6</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong><br />
我们可以执行以下操作：</p>

<ul>
	<li>将&nbsp;<code>nums[3]</code>&nbsp;变为 0 ，结果数组为&nbsp;<code>nums = [0,1,2,<em><strong>0</strong></em>,3,6,5,4]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[4]</code>&nbsp;变为 4 ，结果数组为&nbsp;<code>nums = [0,1,2,0,<em><strong>4</strong></em>,6,5,4]</code>&nbsp;。</li>
</ul>

<p>整数 <code>X</code>&nbsp;为 4 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code>&nbsp;是偶数。</li>
	<li><code>0 &lt;= nums[i] &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

假设最终的数组中，数对 $\textit{nums}[i]$ 和 $\textit{nums}[n-i-1]$ 的差值为 $s$。

我们不妨设 $x$ 为 $\textit{nums}[i]$ 和 $\textit{nums}[n-i-1]$ 的较小值，设 $y$ 为 $\textit{nums}[i]$ 和 $\textit{nums}[n-i-1]$ 的较大值。

对于每一对数，我们有以下几种情况：

-   如果不需要改动，那么 $y - x = s$。
-   如果改动一次，那么 $s \le \max(y, k - x)$，最大值就是把 $x$ 变为 $0$，或者把 $y$ 变为 $k$。
-   如果改动两次，那么 $s \gt \max(y, k - x)$。

即：

-   在 $[0,y-x-1]$ 范围内，需要改动 $1$ 次。
-   在 $[y-x]$ 时，不需要改动。
-   在 $[y-x+1, \max(y, k-x)]$ 范围内，需要改动 $1$ 次。
-   在 $[\max(y, k-x)+1, k]$ 范围内，需要改动 $2$ 次。

我们枚举每一个数对，利用差分数组，更新每个数对在不同区间范围内的改动次数。

最后，我们求出差分数组的前缀和中的最小值，即为最少的改动次数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

相似题目：

-   [1674. 使数组互补的最少操作次数](https://github.com/doocs/leetcode/tree/main/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minChanges(self, nums: List[int], k: int) -> int:
        d = [0] * (k + 2)
        n = len(nums)
        for i in range(n // 2):
            x, y = nums[i], nums[-i - 1]
            if x > y:
                x, y = y, x
            d[0] += 1
            d[y - x] -= 1
            d[y - x + 1] += 1
            d[max(y, k - x) + 1] -= 1
            d[max(y, k - x) + 1] += 2
        return min(accumulate(d))
```

#### Java

```java
class Solution {
    public int minChanges(int[] nums, int k) {
        int[] d = new int[k + 2];
        int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            int x = Math.min(nums[i], nums[n - i - 1]);
            int y = Math.max(nums[i], nums[n - i - 1]);
            d[0] += 1;
            d[y - x] -= 1;
            d[y - x + 1] += 1;
            d[Math.max(y, k - x) + 1] -= 1;
            d[Math.max(y, k - x) + 1] += 2;
        }
        int ans = n, s = 0;
        for (int x : d) {
            s += x;
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
    int minChanges(vector<int>& nums, int k) {
        int d[k + 2];
        memset(d, 0, sizeof(d));
        int n = nums.size();
        for (int i = 0; i < n / 2; ++i) {
            int x = min(nums[i], nums[n - i - 1]);
            int y = max(nums[i], nums[n - i - 1]);
            d[0] += 1;
            d[y - x] -= 1;
            d[y - x + 1] += 1;
            d[max(y, k - x) + 1] -= 1;
            d[max(y, k - x) + 1] += 2;
        }
        int ans = n, s = 0;
        for (int x : d) {
            s += x;
            ans = min(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func minChanges(nums []int, k int) int {
	d := make([]int, k+2)
	n := len(nums)
	for i := 0; i < n/2; i++ {
		x, y := nums[i], nums[n-1-i]
		if x > y {
			x, y = y, x
		}
		d[0] += 1
		d[y-x] -= 1
		d[y-x+1] += 1
		d[max(y, k-x)+1] -= 1
		d[max(y, k-x)+1] += 2
	}
	ans, s := n, 0
	for _, x := range d {
		s += x
		ans = min(ans, s)
	}
	return ans
}
```

#### TypeScript

```ts
function minChanges(nums: number[], k: number): number {
    const d: number[] = Array(k + 2).fill(0);
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        const x = Math.min(nums[i], nums[n - 1 - i]);
        const y = Math.max(nums[i], nums[n - 1 - i]);
        d[0] += 1;
        d[y - x] -= 1;
        d[y - x + 1] += 1;
        d[Math.max(y, k - x) + 1] -= 1;
        d[Math.max(y, k - x) + 1] += 2;
    }
    let [ans, s] = [n, 0];
    for (const x of d) {
        s += x;
        ans = Math.min(ans, s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
