# [330. 按要求补齐数组](https://leetcode.cn/problems/patching-array)

[English Version](/solution/0300-0399/0330.Patching%20Array/README_EN.md)

<!-- tags:贪心,数组 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个已排序的正整数数组 <code>nums</code>&nbsp;<em>，</em>和一个正整数&nbsp;<code>n</code><em> 。</em>从&nbsp;<code>[1, n]</code>&nbsp;区间内选取任意个数字补充到&nbsp;nums&nbsp;中，使得&nbsp;<code>[1, n]</code>&nbsp;区间内的任何数字都可以用&nbsp;nums&nbsp;中某几个数字的和来表示。</p>

<p>请返回 <em>满足上述要求的最少需要补充的数字个数</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,3]</code>, n = <code>6</code>
<strong>输出: </strong>1 
<strong>解释:</strong>
根据 nums&nbsp;里现有的组合&nbsp;<code>[1], [3], [1,3]</code>，可以得出&nbsp;<code>1, 3, 4</code>。
现在如果我们将&nbsp;<code>2</code>&nbsp;添加到&nbsp;nums 中，&nbsp;组合变为: <code>[1], [2], [3], [1,3], [2,3], [1,2,3]</code>。
其和可以表示数字&nbsp;<code>1, 2, 3, 4, 5, 6</code>，能够覆盖&nbsp;<code>[1, 6]</code>&nbsp;区间里所有的数。
所以我们最少需要添加一个数字。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,5,10]</code>, n = <code>20</code>
<strong>输出:</strong> 2
<strong>解释: </strong>我们需要添加&nbsp;<code>[2,4]</code>。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,2,2]</code>, n = <code>5</code>
<strong>输出:</strong> 0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code>&nbsp;按 <strong>升序排列</strong></li>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

## 解法

### 方法一：贪心

我们假设数字 $x$ 是最小的不能表示的正整数，那么 $[1,..x-1]$ 的这些数都是可以表示的。为了能表示数字 $x$，我们需要添加一个小于等于 $x$ 的数：

-   如果添加的数等于 $x$，由于 $[1,..x-1]$ 的数都可以表示，添加 $x$ 后，区间 $[1,..2x-1]$ 内的数都可以表示，最小的不能表示的正整数变成了 $2x$。
-   如果添加的数小于 $x$，不妨设为 $x'$，由于 $[1,..x-1]$ 的数都可以表示，添加 $x'$ 后，区间 $[1,..x+x'-1]$ 内的数都可以表示，最小的不能表示的正整数变成了 $x+x' \lt 2x$。

因此，我们应该贪心地添加数字 $x$，这样可以覆盖的区间更大。

我们用一个变量 $x$ 记录当前不能表示的最小正整数，初始化为 $1$，此时 $[1,..x-1]$ 是空的，表示当前没有任何数可以被覆盖；用一个变量 $i$ 记录当前遍历到的数组下标。

循环进行以下操作：

-   如果 $i$ 在数组范围内且 $nums[i] \le x$，说明当前数字可以被覆盖，因此将 $x$ 的值加上 $nums[i]$，并将 $i$ 的值加 $1$。
-   否则，说明 $x$ 没有被覆盖，因此需要在数组中补充一个数 $x$，然后 $x$ 更新为 $2x$。
-   重复上述操作，直到 $x$ 的值大于 $n$。

最终答案即为补充的数的数量。

时间复杂度 $O(m + \log n)$，其中 $m$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minPatches(self, nums: List[int], n: int) -> int:
        x = 1
        ans = i = 0
        while x <= n:
            if i < len(nums) and nums[i] <= x:
                x += nums[i]
                i += 1
            else:
                ans += 1
                x <<= 1
        return ans
```

```java
class Solution {
    public int minPatches(int[] nums, int n) {
        long x = 1;
        int ans = 0;
        for (int i = 0; x <= n;) {
            if (i < nums.length && nums[i] <= x) {
                x += nums[i++];
            } else {
                ++ans;
                x <<= 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minPatches(vector<int>& nums, int n) {
        long long x = 1;
        int ans = 0;
        for (int i = 0; x <= n;) {
            if (i < nums.size() && nums[i] <= x) {
                x += nums[i++];
            } else {
                ++ans;
                x <<= 1;
            }
        }
        return ans;
    }
};
```

```go
func minPatches(nums []int, n int) (ans int) {
	x := 1
	for i := 0; x <= n; {
		if i < len(nums) && nums[i] <= x {
			x += nums[i]
			i++
		} else {
			ans++
			x <<= 1
		}
	}
	return
}
```

```ts
function minPatches(nums: number[], n: number): number {
    let x = 1;
    let ans = 0;
    for (let i = 0; x <= n; ) {
        if (i < nums.length && nums[i] <= x) {
            x += nums[i++];
        } else {
            ++ans;
            x *= 2;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
