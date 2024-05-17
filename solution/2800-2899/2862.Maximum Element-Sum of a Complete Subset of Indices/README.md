---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2862.Maximum%20Element-Sum%20of%20a%20Complete%20Subset%20of%20Indices/README.md
rating: 2291
source: 第 363 场周赛 Q4
tags:
    - 数组
    - 数学
    - 数论
---

<!-- problem:start -->

# [2862. 完全子集的最大元素和](https://leetcode.cn/problems/maximum-element-sum-of-a-complete-subset-of-indices)

[English Version](/solution/2800-2899/2862.Maximum%20Element-Sum%20of%20a%20Complete%20Subset%20of%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>1</strong> 开始、由 <code>n</code> 个整数组成的数组。你需要从&nbsp;<code>nums</code>&nbsp;选择一个&nbsp;<strong>完全集</strong>，其中每对元素下标的乘积都是一个 <span data-keyword="perfect-square">完全平方数</span>，例如选择&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>a<sub>j</sub></code>&nbsp;，<code>i * j</code>&nbsp;一定是完全平方数。</p>

<p>返回&nbsp;<strong>完全子集</strong> 所能取到的 <strong>最大元素和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [8,7,3,5,7,2,4,9]
<strong>输出：</strong>16
<strong>解释：</strong>我们选择了下标 1 和 4 的元素，并且 1 * 4 是一个完全平方数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [8,10,3,8,1,13,7,9,4]
<strong>输出：</strong>20
<strong>解释：</strong>我们选择了下标 1，4 和 9 的元素。1 * 4，1 * 9，4 * 9 都是完全平方数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们注意到，如果一个数字可以表示成 $k \times j^2$ 的形式，那么所有该形式的数字的 $k$ 是相同的。

因此，我们可以在 $[1,..n]$ 范围内枚举 $k$，然后从 $1$ 开始枚举 $j$，每一次累加 $nums[k \times j^2 - 1]$ 的值到 $t$ 中，直到 $k \times j^2 > n$。此时更新答案为 $ans = \max(ans, t)$。

最后返回答案 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for k in range(1, n + 1):
            t = 0
            j = 1
            while k * j * j <= n:
                t += nums[k * j * j - 1]
                j += 1
            ans = max(ans, t)
        return ans
```

```java
class Solution {
    public long maximumSum(List<Integer> nums) {
        long ans = 0;
        int n = nums.size();
        for (int k = 1; k <= n; ++k) {
            long t = 0;
            for (int j = 1; k * j * j <= n; ++j) {
                t += nums.get(k * j * j - 1);
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

```java
class Solution {
    public long maximumSum(List<Integer> nums) {
        long ans = 0;
        int n = nums.size();
        boolean[] used = new boolean[n + 1];
        int bound = (int) Math.floor(Math.sqrt(n));
        int[] squares = new int[bound + 1];
        for (int i = 1; i <= bound + 1; i++) {
            squares[i - 1] = i * i;
        }
        for (int i = 1; i <= n; i++) {
            long res = 0;
            int idx = 0;
            int curr = i * squares[idx];
            while (curr <= n) {
                res += nums.get(curr - 1);
                curr = i * squares[++idx];
            }
            ans = Math.max(ans, res);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maximumSum(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        for (int k = 1; k <= n; ++k) {
            long long t = 0;
            for (int j = 1; k * j * j <= n; ++j) {
                t += nums[k * j * j - 1];
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

```go
func maximumSum(nums []int) (ans int64) {
	n := len(nums)
	for k := 1; k <= n; k++ {
		var t int64
		for j := 1; k*j*j <= n; j++ {
			t += int64(nums[k*j*j-1])
		}
		ans = max(ans, t)
	}
	return
}
```

```ts
function maximumSum(nums: number[]): number {
    let ans = 0;
    const n = nums.length;
    for (let k = 1; k <= n; ++k) {
        let t = 0;
        for (let j = 1; k * j * j <= n; ++j) {
            t += nums[k * j * j - 1];
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
