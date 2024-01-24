# [2862. 完全子集的最大元素和](https://leetcode.cn/problems/maximum-element-sum-of-a-complete-subset-of-indices)

[English Version](/solution/2800-2899/2862.Maximum%20Element-Sum%20of%20a%20Complete%20Subset%20of%20Indices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong> 开始、由 <code>n</code> 个整数组成的数组。</p>

<p>如果一组数字中每对元素的乘积都是一个完全平方数，则称这组数字是一个 <strong>完全集</strong> 。</p>

<p>下标集 <code>{1, 2, ..., n}</code> 的子集可以表示为 <code>{i<sub>1</sub>, i<sub>2</sub>, ..., i<sub>k</sub>}</code>，我们定义对应该子集的 <strong>元素和</strong> 为 <code>nums[i<sub>1</sub>] + nums[i<sub>2</sub>] + ... + nums[i<sub>k</sub>]</code> 。</p>

<p>返回下标集&nbsp;<code>{1, 2, ..., n}</code> 的 <strong>完全子集</strong> 所能取到的 <strong>最大元素和</strong> 。</p>

<p>完全平方数是指可以表示为一个整数和其自身相乘的数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [8,7,3,5,7,2,4,9]
<strong>输出：</strong>16
<strong>解释：</strong>除了由单个下标组成的子集之外，还有两个下标集的完全子集：{1,4} 和 {2,8} 。
与下标 1 和 4 对应的元素和等于 nums[1] + nums[4] = 8 + 5 = 13 。
与下标 2 和 8 对应的元素和等于 nums[2] + nums[8] = 7 + 9 = 16 。
因此，下标集的完全子集可以取到的最大元素和为 16 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,10,3,10,1,13,7,9,4]
<strong>输出：</strong>19
<strong>解释：</strong>除了由单个下标组成的子集之外，还有四个下标集的完全子集：{1,4}、{1,9}、{2,8}、{4,9} 和 {1,4,9} 。 
与下标 1 和 4 对应的元素和等于 nums[1] + nums[4] = 5 + 10 = 15 。 
与下标 1 和 9 对应的元素和等于 nums[1] + nums[9] = 5 + 4 = 9 。 
与下标 2 和 8 对应的元素和等于 nums[2] + nums[8] = 10 + 9 = 19 。
与下标 4 和 9 对应的元素和等于 nums[4] + nums[9] = 10 + 4 = 14 。 
与下标 1、4 和 9 对应的元素和等于 nums[1] + nums[4] + nums[9] = 5 + 10 + 4 = 19 。 
因此，下标集的完全子集可以取到的最大元素和为 19 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

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

<!-- end -->
