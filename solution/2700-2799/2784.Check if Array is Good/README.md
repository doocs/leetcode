# [2784. 检查数组是否是好的](https://leetcode.cn/problems/check-if-array-is-good)

[English Version](/solution/2700-2799/2784.Check%20if%20Array%20is%20Good/README_EN.md)

<!-- tags:数组,哈希表,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，如果它是数组&nbsp;<code>base[n]</code>&nbsp;的一个排列，我们称它是个&nbsp;<strong>好</strong>&nbsp;数组。</p>

<p><code>base[n] = [1, 2, ..., n - 1, n, n]</code>&nbsp;（换句话说，它是一个长度为 <code>n + 1</code>&nbsp;且包含&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;恰好各一次，包含 <code>n</code>&nbsp; 两次的一个数组）。比方说，<code>base[1] = [1, 1]</code>&nbsp;，<code>base[3] = [1, 2, 3, 3]</code>&nbsp;。</p>

<p>如果数组是一个好数组，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p><strong>注意：</strong>数组的排列是这些数字按任意顺序排布后重新得到的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2, 1, 3]
<b>输出：</b>false
<b>解释：</b>因为数组的最大元素是 3 ，唯一可以构成这个数组的 base[n] 对应的 n = 3 。但是 base[3] 有 4 个元素，但数组 nums 只有 3 个元素，所以无法得到 base[3] = [1, 2, 3, 3] 的排列，所以答案为 false 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1, 3, 3, 2]
<b>输出：</b>true
<b>解释：因为</b>数组的最大元素是 3 ，唯一可以构成这个数组的 base[n] 对应的 n = 3 ，可以看出数组是 base[3] = [1, 2, 3, 3] 的一个排列（交换 nums 中第二个和第四个元素）。所以答案为 true 。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1, 1]
<b>输出：</b>true
<b>解释：</b>因为数组的最大元素是 1 ，唯一可以构成这个数组的 base[n] 对应的 n = 1，可以看出数组是 base[1] = [1, 1] 的一个排列。所以答案为 true 。</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>nums = [3, 4, 4, 1, 2, 1]
<b>输出：</b>false
<b>解释：</b>因为数组的最大元素是 4 ，唯一可以构成这个数组的 base[n] 对应的 n = 4 。但是 base[n] 有 5 个元素而 nums 有 6 个元素。所以答案为 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= num[i] &lt;= 200</code></li>
</ul>

## 解法

### 方法一：计数

我们可以用一个哈希表或数组 $cnt$ 记录数组 $nums$ 中每个元素出现的次数。然后我们判断是否满足以下条件：

1. $cnt[n] = 2$，即数组中最大的元素出现了两次；
2. 对于 $1 \leq i \leq n-1$，均满足 $cnt[i] = 1$，即数组中除了最大的元素外，其他元素都只出现了一次。

如果满足以上两个条件，那么数组 $nums$ 是一个好数组，否则不是。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def isGood(self, nums: List[int]) -> bool:
        cnt = Counter(nums)
        n = len(nums) - 1
        return cnt[n] == 2 and all(cnt[i] for i in range(1, n))
```

```java
class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        int[] cnt = new int[201];
        for (int x : nums) {
            ++cnt[x];
        }
        if (cnt[n] != 2) {
            return false;
        }
        for (int i = 1; i < n; ++i) {
            if (cnt[i] != 1) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isGood(vector<int>& nums) {
        int n = nums.size() - 1;
        int cnt[201]{};
        for (int x : nums) {
            ++cnt[x];
        }
        if (cnt[n] != 2) {
            return false;
        }
        for (int i = 1; i < n; ++i) {
            if (cnt[i] != 1) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func isGood(nums []int) bool {
	n := len(nums) - 1
	cnt := [201]int{}
	for _, x := range nums {
		cnt[x]++
	}
	if cnt[n] != 2 {
		return false
	}
	for i := 1; i < n; i++ {
		if cnt[i] != 1 {
			return false
		}
	}
	return true
}
```

```ts
function isGood(nums: number[]): boolean {
    const n = nums.length - 1;
    const cnt: number[] = Array(201).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    if (cnt[n] !== 2) {
        return false;
    }
    for (let i = 1; i < n; ++i) {
        if (cnt[i] !== 1) {
            return false;
        }
    }
    return true;
}
```

```cs
public class Solution {
    public bool IsGood(int[] nums) {
        int n = nums.Length - 1;
        int[] cnt = new int[201];
        foreach (int x in nums) {
            ++cnt[x];
        }
        if (cnt[n] != 2) {
            return false;
        }
        for (int i = 1; i < n; ++i) {
            if (cnt[i] != 1) {
                return false;
            }
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- end -->
