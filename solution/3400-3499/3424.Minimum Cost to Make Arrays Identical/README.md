---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3424.Minimum%20Cost%20to%20Make%20Arrays%20Identical/README.md
rating: 1502
source: 第 148 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [3424. 将数组变相同的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-arrays-identical)

[English Version](/solution/3400-3499/3424.Minimum%20Cost%20to%20Make%20Arrays%20Identical/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度都为 <code>n</code>&nbsp;的整数数组&nbsp;<code>arr</code> 和&nbsp;<code>brr</code>&nbsp;以及一个整数&nbsp;<code>k</code>&nbsp;。你可以对 <code>arr</code>&nbsp;执行以下操作任意次：</p>

<ul>
	<li>将&nbsp;<code>arr</code>&nbsp;分割成若干个&nbsp;<strong>连续的</strong>&nbsp;子数组，并将这些子数组按任意顺序重新排列。这个操作的代价为&nbsp;<code>k</code>&nbsp;。</li>
	<li>
	<p>选择 <code>arr</code>&nbsp;中的任意一个元素，将它增加或者减少一个正整数&nbsp;<code>x</code>&nbsp;。这个操作的代价为 <code>x</code>&nbsp;。</p>
	</li>
</ul>

<p>请你返回将 <code>arr</code>&nbsp;变为 <code>brr</code>&nbsp;的 <strong>最小</strong>&nbsp;总代价。</p>

<p><strong>子数组</strong>&nbsp;是一个数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>arr = [-7,9,5], brr = [7,-2,-5], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><b>解释：</b></p>

<ul>
	<li>将&nbsp;<code>arr</code>&nbsp;分割成两个连续子数组：<code>[-7]</code> 和&nbsp;<code>[9, 5]</code>&nbsp;然后将它们重新排列成&nbsp;<code>[9, 5, -7]</code>&nbsp;，代价为 2 。</li>
	<li>将&nbsp;<code>arr[0]</code>&nbsp;减小 2 ，数组变为&nbsp;<code>[7, 5, -7]</code>&nbsp;，操作代价为 2 。</li>
	<li>将&nbsp;<code>arr[1]</code>&nbsp;减小 7 ，数组变为&nbsp;<code>[7, -2, -7]</code>&nbsp;，操作代价为 7 。</li>
	<li>将&nbsp;<code>arr[2]</code>&nbsp;增加 2 ，数组变为&nbsp;<code>[7, -2, -5]</code>&nbsp;，操作代价为 2 。</li>
</ul>

<p>将两个数组变相等的总代价为&nbsp;<code>2 + 2 + 7 + 2 = 13</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>arr = [2,1], brr = [2,1], k = 0</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b></p>

<p>由于数组已经相等，不需要进行任何操作，所以总代价为 0 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length == brr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>10</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= brr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, arr: List[int], brr: List[int], k: int) -> int:
        c1 = sum(abs(a - b) for a, b in zip(arr, brr))
        arr.sort()
        brr.sort()
        c2 = k + sum(abs(a - b) for a, b in zip(arr, brr))
        return min(c1, c2)
```

#### Java

```java
class Solution {
    public long minCost(int[] arr, int[] brr, long k) {
        long c1 = calc(arr, brr);
        Arrays.sort(arr);
        Arrays.sort(brr);
        long c2 = calc(arr, brr) + k;
        return Math.min(c1, c2);
    }

    private long calc(int[] arr, int[] brr) {
        long ans = 0;
        for (int i = 0; i < arr.length; ++i) {
            ans += Math.abs(arr[i] - brr[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCost(vector<int>& arr, vector<int>& brr, long long k) {
        auto calc = [&](vector<int>& arr, vector<int>& brr) {
            long long ans = 0;
            for (int i = 0; i < arr.size(); ++i) {
                ans += abs(arr[i] - brr[i]);
            }
            return ans;
        };
        long long c1 = calc(arr, brr);
        ranges::sort(arr);
        ranges::sort(brr);
        long long c2 = calc(arr, brr) + k;
        return min(c1, c2);
    }
};
```

#### Go

```go
func minCost(arr []int, brr []int, k int64) int64 {
	calc := func(a, b []int) (ans int64) {
		for i := range a {
			ans += int64(abs(a[i] - b[i]))
		}
		return
	}
	c1 := calc(arr, brr)
	sort.Ints(arr)
	sort.Ints(brr)
	c2 := calc(arr, brr) + k
	return min(c1, c2)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minCost(arr: number[], brr: number[], k: number): number {
    const calc = (a: number[], b: number[]) => {
        let ans = 0;
        for (let i = 0; i < a.length; ++i) {
            ans += Math.abs(a[i] - b[i]);
        }
        return ans;
    };
    const c1 = calc(arr, brr);
    arr.sort((a, b) => a - b);
    brr.sort((a, b) => a - b);
    const c2 = calc(arr, brr) + k;
    return Math.min(c1, c2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
