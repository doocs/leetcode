---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3982.Sum%20of%20Integers%20with%20Maximum%20Digit%20Range/README.md
---

<!-- problem:start -->

# [3982. 最大数字范围的整数之和](https://leetcode.cn/problems/sum-of-integers-with-maximum-digit-range)

[English Version](/solution/3900-3999/3982.Sum%20of%20Integers%20with%20Maximum%20Digit%20Range/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>一个整数的&nbsp;<strong>数字范围&nbsp;</strong>定义为其<strong>&nbsp;最大</strong>&nbsp;数字与<strong>&nbsp;最小&nbsp;</strong>数字之间的差。</p>

<p>例如，5724 的数字范围为 <code>7 - 2 = 5</code>。</p>

<p>返回 <code>nums</code> 中所有&nbsp;<strong>数字范围&nbsp;</strong>等于数组中&nbsp;<strong>最大数字范围&nbsp;</strong>的整数之和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5724,111,350]</span></p>

<p><strong>输出：</strong> <span class="example-io">6074</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<tbody>
		<tr>
			<th style="text-align:center;"><code>i</code></th>
			<th style="text-align:center;"><code>nums[i]</code></th>
			<th style="text-align:center;">最大数字</th>
			<th style="text-align:center;">最小数字</th>
			<th style="text-align:center;">数字范围</th>
		</tr>
		<tr>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">5724</td>
			<td style="text-align:center;">7</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">5</td>
		</tr>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">111</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">0</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">350</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">5</td>
		</tr>
	</tbody>
</table>

<p>最大数字范围为 5。数字范围为 5 的整数是 5724 和 350，因此答案为 <code>5724 + 350 = 6074</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [90,900]</span></p>

<p><strong>输出：</strong> <span class="example-io">990</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<tbody>
		<tr>
			<th style="text-align:center;"><code>i</code></th>
			<th style="text-align:center;"><code>nums[i]</code></th>
			<th style="text-align:center;">最大数字</th>
			<th style="text-align:center;">最小数字</th>
			<th style="text-align:center;">数字范围</th>
		</tr>
		<tr>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">90</td>
			<td style="text-align:center;">9</td>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">9</td>
		</tr>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">900</td>
			<td style="text-align:center;">9</td>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">9</td>
		</tr>
	</tbody>
</table>

<p>最大数字范围为 9。两个整数的数字范围都是 9&nbsp;，因此答案为 <code>90 + 900 = 990</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>10 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历数组 $\textit{nums}$，对于每个整数 $x$，逐位取出数字，求出最大数字 $b$ 和最小数字 $a$，计算数字范围 $r = b - a$。若 $r$ 大于当前最大数字范围 $\textit{mx}$，则更新 $\textit{mx} = r$ 并将答案重置为 $x$；若 $r$ 等于 $\textit{mx}$，则将 $x$ 累加到答案中。

时间复杂度 $O(n \log M)$，空间复杂度 $O(1)$。其中 $n$ 是数组 $\textit{nums}$ 的长度，$M$ 是数组元素的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDigitRange(self, nums: list[int]) -> int:
        ans = mx = 0
        for x in nums:
            a, b = 10, 0
            y = x
            while y:
                v = y % 10
                y //= 10
                a = min(a, v)
                b = max(b, v)
            r = b - a
            if mx < r:
                mx = r
                ans = x
            elif mx == r:
                ans += x
        return ans
```

#### Java

```java
class Solution {
    public int maxDigitRange(int[] nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            int a = 10, b = 0;
            for (int y = x; y > 0; y /= 10) {
                int v = y % 10;
                a = Math.min(a, v);
                b = Math.max(b, v);
            }
            int r = b - a;
            if (mx < r) {
                mx = r;
                ans = x;
            } else if (mx == r) {
                ans += x;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDigitRange(vector<int>& nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            int a = 10, b = 0;
            for (int y = x; y > 0; y /= 10) {
                int v = y % 10;
                a = min(a, v);
                b = max(b, v);
            }
            int r = b - a;
            if (mx < r) {
                mx = r;
                ans = x;
            } else if (mx == r) {
                ans += x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDigitRange(nums []int) (ans int) {
	mx := 0
	for _, x := range nums {
		a, b := 10, 0
		for y := x; y > 0; y /= 10 {
			v := y % 10
			a = min(a, v)
			b = max(b, v)
		}
		r := b - a
		if mx < r {
			mx = r
			ans = x
		} else if mx == r {
			ans += x
		}
	}
	return
}
```

#### TypeScript

```ts
function maxDigitRange(nums: number[]): number {
    let [ans, mx] = [0, 0];
    for (const x of nums) {
        let [a, b] = [10, 0];
        for (let y = x; y; y = (y / 10) | 0) {
            const v = y % 10;
            a = Math.min(a, v);
            b = Math.max(b, v);
        }
        const r = b - a;
        if (mx < r) {
            mx = r;
            ans = x;
        } else if (mx == r) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
