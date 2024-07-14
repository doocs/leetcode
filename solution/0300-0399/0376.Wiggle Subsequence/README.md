---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0376.Wiggle%20Subsequence/README.md
tags:
    - 贪心
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [376. 摆动序列](https://leetcode.cn/problems/wiggle-subsequence)

[English Version](/solution/0300-0399/0376.Wiggle%20Subsequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为<strong> 摆动序列 。</strong>第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。</p>

<ul>
	<li>
	<p>例如， <code>[1, 7, 4, 9, 2, 5]</code> 是一个 <strong>摆动序列</strong> ，因为差值 <code>(6, -3, 5, -7, 3)</code> 是正负交替出现的。</p>
	</li>
	<li>相反，<code>[1, 4, 7, 2, 5]</code> 和 <code>[1, 7, 4, 5, 5]</code> 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。</li>
</ul>

<p><strong>子序列</strong> 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。</p>

<p>给你一个整数数组 <code>nums</code> ，返回 <code>nums</code> 中作为 <strong>摆动序列 </strong>的 <strong>最长子序列的长度</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,7,4,9,2,5]
<strong>输出：</strong>6
<strong>解释：</strong>整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,17,5,10,13,15,10,5,16,8]
<strong>输出：</strong>7
<strong>解释：</strong>这个序列包含几个长度为 7 摆动序列。
其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5,6,7,8,9]
<strong>输出：</strong>2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>0 <= nums[i] <= 1000</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能否用 <code>O(n)</code><em> </em>时间复杂度完成此题?</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示以第 $i$ 个元素结尾且最后是上升趋势的摆动序列的长度，定义 $g[i]$ 表示以第 $i$ 个元素结尾且最后是下降趋势的摆动序列的长度。初始时 $f[0] = g[0] = 1$，因为只有一个元素时，摆动序列的长度为 $1$。初始化答案为 $1$。

对于 $f[i]$，其中 $i \geq 1$，我们在 $[0, i)$ 的范围内枚举 $j$，如果 $nums[j] < nums[i]$，则说明 $i$ 可以接在 $j$ 的后面形成一个上升的摆动序列，此时 $f[i] = \max(f[i], g[j] + 1)$；如果 $nums[j] > nums[i]$，则说明 $i$ 可以接在 $j$ 的后面形成一个下降的摆动序列，此时 $g[i] = \max(g[i], f[j] + 1)$。然后我们更新答案为 $\max(f[i], g[i])$。

最后，我们返回答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wiggleMaxLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 1
        f = [1] * n
        g = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    f[i] = max(f[i], g[j] + 1)
                elif nums[j] > nums[i]:
                    g[i] = max(g[i], f[j] + 1)
            ans = max(ans, f[i], g[i])
        return ans
```

#### Java

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], g[j] + 1);
                } else if (nums[j] > nums[i]) {
                    g[i] = Math.max(g[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, Math.max(f[i], g[i]));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 1;
        vector<int> f(n, 1);
        vector<int> g(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = max(f[i], g[j] + 1);
                } else if (nums[j] > nums[i]) {
                    g[i] = max(g[i], f[j] + 1);
                }
            }
            ans = max({ans, f[i], g[i]});
        }
        return ans;
    }
};
```

#### Go

```go
func wiggleMaxLength(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	g := make([]int, n)
	f[0], g[0] = 1, 1
	ans := 1
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				f[i] = max(f[i], g[j]+1)
			} else if nums[j] > nums[i] {
				g[i] = max(g[i], f[j]+1)
			}
		}
		ans = max(ans, max(f[i], g[i]))
	}
	return ans
}
```

#### TypeScript

```ts
function wiggleMaxLength(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(1);
    const g: number[] = Array(n).fill(1);
    let ans = 1;
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[i] > nums[j]) {
                f[i] = Math.max(f[i], g[j] + 1);
            } else if (nums[i] < nums[j]) {
                g[i] = Math.max(g[i], f[j] + 1);
            }
        }
        ans = Math.max(ans, f[i], g[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
