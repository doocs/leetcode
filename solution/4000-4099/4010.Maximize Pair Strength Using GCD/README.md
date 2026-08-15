---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4010.Maximize%20Pair%20Strength%20Using%20GCD/README.md
rating: 1216
source: 第 513 场周赛 Q1
---

<!-- problem:start -->

# [4010. 数对的最大强度](https://leetcode.cn/problems/maximize-pair-strength-using-gcd)

[English Version](/solution/4000-4099/4010.Maximize%20Pair%20Strength%20Using%20GCD/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>选择&nbsp;<strong>恰好一对&nbsp;</strong>不同下标 <code>i</code> 和 <code>j</code>。该数对的&nbsp;<strong>强度&nbsp;</strong>定义为：</p>

<p><code>(nums[i] * nums[j]) / gcd(nums[i], nums[j])<sup>2</sup></code></p>

<p>返回所有可能数对中的<strong>&nbsp;最大&nbsp;</strong>强度。</p>

<p><code>gcd(a, b)</code> 表示 <code>a</code> 和 <code>b</code> 的<strong>&nbsp;最大公约数&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,3,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">15</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 1</code> 和 <code>j = 2</code>，得到强度：</p>

<p><code>(3 * 5) / gcd(3, 5)<sup>2</sup> = 15 / 1 = 15</code>，这是所有数对中的最大值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,6,8]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 1</code> 和 <code>j = 2</code>，得到强度：</p>

<p><code>(6 * 8) / gcd(6, 8)<sup>2</sup> = 48 / 4 = 12</code>，这是所有数对中的最大值。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 0</code> 和 <code>j = 1</code>，得到强度：</p>

<p><code>(3 * 3) / gcd(3, 3)<sup>2</sup> = 9 / 9 = 1</code>，这是唯一数对的强度。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们直接枚举所有的数对 $(i, j)$，其中 $i < j$，计算每个数对的强度 $\frac{\textit{nums}[i] \times \textit{nums}[j]}{\gcd(\textit{nums}[i], \textit{nums}[j])^2}$，取最大值即可。

其中，最大公约数 $\gcd$ 可以使用辗转相除法求得。

时间复杂度 $O(n^2 \times \log M)$，其中 $n$ 是数组 $\textit{nums}$ 的长度，而 $M$ 是数组元素的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPairStrength(self, nums: list[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                x = nums[i] * nums[j] // gcd(nums[i], nums[j]) ** 2
                ans = max(ans, x)
        return ans
```

#### Java

```java
class Solution {
    public long maxPairStrength(int[] nums) {
        int n = nums.length;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long g = gcd(nums[i], nums[j]);
                long x = (long) nums[i] * nums[j] / (g * g);
                ans = Math.max(ans, x);
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxPairStrength(vector<int>& nums) {
        int n = nums.size();
        long long ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long long g = gcd(nums[i], nums[j]);
                long long x = 1LL * nums[i] * nums[j] / (g * g);
                ans = max(ans, x);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxPairStrength(nums []int) int64 {
	n := len(nums)
	var ans int64 = 0

	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			g := gcd(int64(nums[i]), int64(nums[j]))
			x := int64(nums[i]) * int64(nums[j]) / (g * g)
			ans = max(ans, x)
		}
	}

	return ans
}

func gcd(a, b int64) int64 {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}
```

#### TypeScript

```ts
function maxPairStrength(nums: number[]): number {
    const n = nums.length;
    let ans = 0;

    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            const g = gcd(nums[i], nums[j]);
            const x = Math.floor((nums[i] * nums[j]) / (g * g));
            ans = Math.max(ans, x);
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    while (b !== 0) {
        const t = a % b;
        a = b;
        b = t;
    }
    return a;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
