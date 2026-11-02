---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4097. 合并整数后的最小值](https://leetcode.cn/problems/minimum-value-of-merged-integers)

[English Version](/solution/4000-4099/4097.Minimum%20Value%20of%20Merged%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为偶数 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>你需要将 <code>nums</code> 中的元素两两<strong>合并</strong>：将 <code>nums[0]</code> 与 <code>nums[1]</code> 合并，将 <code>nums[2]</code> 与 <code>nums[3]</code> 合并，依此类推，直到将 <code>nums[n - 2]</code> 与 <code>nums[n - 1]</code> 合并。</p>

<p>返回所有合并后得到的整数中的<strong>最小值</strong>。</p>

<p><strong>合并</strong>两个整数，是指将第二个整数的所有十进制数位拼接在第一个整数的所有十进制数位之后。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [23,55,42,21]</span></p>

<p><strong>输出：</strong> <span class="example-io">2355</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>nums[0] = 23</code> 与 <code>nums[1] = 55</code> 合并，得到 2355。</li>
	<li>将 <code>nums[2] = 42</code> 与 <code>nums[3] = 21</code> 合并，得到 4221。</li>
	<li>合并后得到的整数中的最小值为 <code>min(2355, 4221) = 2355</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [34,7,11,11]</span></p>

<p><strong>输出：</strong> <span class="example-io">347</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>nums[0] = 34</code> 与 <code>nums[1] = 7</code> 合并，得到 347。</li>
	<li>将 <code>nums[2] = 11</code> 与 <code>nums[3] = 11</code> 合并，得到 1111。</li>
	<li>合并后得到的整数中的最小值为 <code>min(347, 1111) = 347</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [9,20]</span></p>

<p><strong>输出：</strong> <span class="example-io">920</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>nums[0] = 9</code> 与 <code>nums[1] = 20</code> 合并，得到 920。</li>
	<li>合并后只有一个整数，因此最小值为 920。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>n</code> 为偶数</li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

<!-- thinking:start -->

> **思考**
>
> 数组长度不超过 $100$，每个数至多有 $4$ 位十进制数字，合并后的数至多 $8$ 位，都在标准整数范围内。直接按题意把相邻的一对数转成字符串拼接后再转回整数，取所有合并结果的最小值即可。

<!-- thinking:end -->

我们以步长 $2$ 遍历数组 $\textit{nums}$，每次将 $\textit{nums}[i]$ 和 $\textit{nums}[i + 1]$ 转换为字符串拼接后再转回整数，维护所有合并结果的最小值并返回。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMergedInteger(self, nums: list[int]) -> int:
        return min(int(str(nums[i]) + str(nums[i + 1])) for i in range(0, len(nums), 2))
```

#### Java

```java
class Solution {
    public int minMergedInteger(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i += 2) {
            int v = Integer.parseInt(String.valueOf(nums[i]) + nums[i + 1]);
            ans = Math.min(ans, v);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMergedInteger(vector<int>& nums) {
        int ans = INT_MAX;
        for (int i = 0; i < (int) nums.size(); i += 2) {
            int v = stoi(to_string(nums[i]) + to_string(nums[i + 1]));
            ans = min(ans, v);
        }
        return ans;
    }
};
```

#### Go

```go
func minMergedInteger(nums []int) int {
	ans := math.MaxInt32
	for i := 0; i < len(nums); i += 2 {
		v, _ := strconv.Atoi(strconv.Itoa(nums[i]) + strconv.Itoa(nums[i+1]))
		ans = min(ans, v)
	}
	return ans
}
```

#### TypeScript

```ts
function minMergedInteger(nums: number[]): number {
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < nums.length; i += 2) {
        ans = Math.min(ans, Number(String(nums[i]) + nums[i + 1]));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
