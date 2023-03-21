# [2366. 将数组排序的最少替换次数](https://leetcode.cn/problems/minimum-replacements-to-sort-the-array)

[English Version](/solution/2300-2399/2366.Minimum%20Replacements%20to%20Sort%20the%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下表从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。每次操作中，你可以将数组中任何一个元素替换为&nbsp;<strong>任意两个</strong>&nbsp;和为该元素的数字。</p>

<ul>
	<li>比方说，<code>nums = [5,6,7]</code>&nbsp;。一次操作中，我们可以将&nbsp;<code>nums[1]</code> 替换成&nbsp;<code>2</code> 和&nbsp;<code>4</code>&nbsp;，将&nbsp;<code>nums</code>&nbsp;转变成&nbsp;<code>[5,2,4,7]</code>&nbsp;。</li>
</ul>

<p>请你执行上述操作，将数组变成元素按 <strong>非递减</strong> 顺序排列的数组，并返回所需的最少操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,9,3]
<b>输出：</b>2
<b>解释：</b>以下是将数组变成非递减顺序的步骤：
- [3,9,3] ，将9 变成 3 和 6 ，得到数组 [3,3,6,3] 
- [3,3,6,3] ，将 6 变成 3 和 3 ，得到数组 [3,3,3,3,3] 
总共需要 2 步将数组变成非递减有序，所以我们返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>0
<b>解释：</b>数组已经是非递减顺序，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们观察发现，要使得数组 $nums$ 变成非递减有序，也即单调递增，那么数组后面的元素应该尽可能大，所以，将数组 $nums$ 的最后一个元素 $nums[n-1]$ 替换成多个更小的数是没有必要的。

也即是说，我们可以从后往前遍历数组 $nums$，并且维护当前的最大值 $mx$，初始时 $mx = nums[n-1]$。

-   若当前遍历到的元素 $nums[i] \leq mx$，此时不需要将 $nums[i]$ 进行替换，我们直接更新 $mx = nums[i]$ 即可。
-   否则，我们需要将 $nums[i]$ 替换成多个和为 $nums[i]$ 的数，这些数的最大值为 $mx$，总共替换成 $k=\left \lceil \frac{nums[i]}{mx} \right \rceil$ 个数，所以需要进行 $k-1$ 次操作，累加到答案中。这 $k$ 个数中，最小的数为 $\left \lfloor \frac{nums[i]}{k} \right \rfloor$，因此，我们更新 $mx = \left \lfloor \frac{nums[i]}{k} \right \rfloor$。

遍历结束，返回总的操作次数即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumReplacement(self, nums: List[int]) -> int:
        ans = 0
        n = len(nums)
        mx = nums[-1]
        for i in range(n - 2, -1, -1):
            if nums[i] <= mx:
                mx = nums[i]
                continue
            k = (nums[i] + mx - 1) // mx
            ans += k - 1
            mx = nums[i] // k
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minimumReplacement(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int mx = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= mx) {
                mx = nums[i];
                continue;
            }
            int k = (nums[i] + mx - 1) / mx;
            ans += k - 1;
            mx = nums[i] / k;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimumReplacement(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        int mx = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= mx) {
                mx = nums[i];
                continue;
            }
            int k = (nums[i] + mx - 1) / mx;
            ans += k - 1;
            mx = nums[i] / k;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumReplacement(nums []int) (ans int64) {
	n := len(nums)
	mx := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i] <= mx {
			mx = nums[i]
			continue
		}
		k := (nums[i] + mx - 1) / mx
		ans += int64(k - 1)
		mx = nums[i] / k
	}
	return
}
```

### **TypeScript**

```ts
function minimumReplacement(nums: number[]): number {
    const n = nums.length;
    let mx = nums[n - 1];
    let ans = 0;
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] <= mx) {
            mx = nums[i];
            continue;
        }
        const k = Math.ceil(nums[i] / mx);
        ans += k - 1;
        mx = Math.floor(nums[i] / k);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
