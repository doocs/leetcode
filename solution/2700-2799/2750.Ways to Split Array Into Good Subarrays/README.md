# [2750. 将数组划分成若干好子数组的方式](https://leetcode.cn/problems/ways-to-split-array-into-good-subarrays)

[English Version](/solution/2700-2799/2750.Ways%20to%20Split%20Array%20Into%20Good%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二元数组 <code>nums</code> 。</p>

<p>如果数组中的某个子数组 <strong>恰好</strong> 只存在 <strong>一</strong> 个值为 <code>1</code> 的元素，则认为该子数组是一个 <strong>好子数组</strong> 。</p>

<p>请你统计将数组 <code>nums</code> 划分成若干 <strong>好子数组</strong> 的方法数，并以整数形式返回。由于数字可能很大，返回其对 <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>之后的结果。</p>

<p>子数组是数组中的一个连续 <strong>非空</strong> 元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,0,0,1]
<strong>输出：</strong>3
<strong>解释：</strong>存在 3 种可以将 nums 划分成若干好子数组的方式：
- [0,1] [0,0,1]
- [0,1,0] [0,1]
- [0,1,0,0] [1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,0]
<strong>输出：</strong>1
<strong>解释：</strong>存在 1 种可以将 nums 划分成若干好子数组的方式：
- [0,1,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

## 解法

### 方法一：乘法原理

根据题目描述，我们可以在两个 $1$ 之间画一条分割线，假设两个 $1$ 之间的下标分别为 $j$ 和 $i$，那么可以画的不同分割线的数量为 $i - j$。我们找出所有满足条件的 $j$ 和 $i$，然后将所有的 $i - j$ 相乘即可。如果找不到两个 $1$ 之间的分割线，那么说明数组中不存在 $1$，此时答案为 $0$。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfGoodSubarraySplits(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        ans, j = 1, -1
        for i, x in enumerate(nums):
            if x == 0:
                continue
            if j > -1:
                ans = ans * (i - j) % mod
            j = i
        return 0 if j == -1 else ans
```

```java
class Solution {
    public int numberOfGoodSubarraySplits(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int ans = 1, j = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = (int) ((long) ans * (i - j) % mod);
            }
            j = i;
        }
        return j == -1 ? 0 : ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfGoodSubarraySplits(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int ans = 1, j = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = 1LL * ans * (i - j) % mod;
            }
            j = i;
        }
        return j == -1 ? 0 : ans;
    }
};
```

```go
func numberOfGoodSubarraySplits(nums []int) int {
	const mod int = 1e9 + 7
	ans, j := 1, -1
	for i, x := range nums {
		if x == 0 {
			continue
		}
		if j > -1 {
			ans = ans * (i - j) % mod
		}
		j = i
	}
	if j == -1 {
		return 0
	}
	return ans
}
```

```ts
function numberOfGoodSubarraySplits(nums: number[]): number {
    let ans = 1;
    let j = -1;
    const mod = 10 ** 9 + 7;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (nums[i] === 0) {
            continue;
        }
        if (j > -1) {
            ans = (ans * (i - j)) % mod;
        }
        j = i;
    }
    return j === -1 ? 0 : ans;
}
```

```cs
public class Solution {
    public int NumberOfGoodSubarraySplits(int[] nums) {
        long ans = 1, j = -1;
        int mod = 1000000007;
        int n = nums.Length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = ans * (i - j) % mod;
            }
            j = i;
        }
        return j == -1 ? 0 : (int) ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
