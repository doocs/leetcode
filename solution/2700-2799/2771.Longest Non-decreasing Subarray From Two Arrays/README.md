# [2771. 构造最长非递减子数组](https://leetcode.cn/problems/longest-non-decreasing-subarray-from-two-arrays)

[English Version](/solution/2700-2799/2771.Longest%20Non-decreasing%20Subarray%20From%20Two%20Arrays/README_EN.md)

<!-- tags:数组,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong> 开始的整数数组 <code>nums1</code> 和 <code>nums2</code> ，长度均为 <code>n</code> 。</p>

<p>让我们定义另一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组，<code>nums3</code> 。对于范围&nbsp;<code>[0, n - 1]</code> 的每个下标 <code>i</code> ，你可以将 <code>nums1[i]</code> 或 <code>nums2[i]</code> 的值赋给 <code>nums3[i]</code> 。</p>

<p>你的任务是使用最优策略为 <code>nums3</code> 赋值，以最大化 <code>nums3</code> 中 <strong>最长非递减子数组</strong> 的长度。</p>

<p>以整数形式表示并返回 <code>nums3</code> 中 <strong>最长非递减</strong> 子数组的长度。</p>

<p><strong>注意：子数组</strong> 是数组中的一个连续非空元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums1 = [2,3,1], nums2 = [1,2,1]
<strong>输出：</strong>2
<strong>解释：</strong>构造 nums3 的方法之一是： 
nums3 = [nums1[0], nums2[1], nums2[2]] =&gt; [2,2,1]
从下标 0 开始到下标 1 结束，形成了一个长度为 2 的非递减子数组 [2,2] 。 
可以证明 2 是可达到的最大长度。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums1 = [1,3,2,1], nums2 = [2,2,3,4]
<strong>输出：</strong>4
<strong>解释：</strong>构造 nums3 的方法之一是： 
nums3 = [nums1[0], nums2[1], nums2[2], nums2[3]] =&gt; [1,2,3,4]
整个数组形成了一个长度为 4 的非递减子数组，并且是可达到的最大长度。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums1 = [1,1], nums2 = [2,2]
<strong>输出：</strong>2
<strong>解释：</strong>构造 nums3 的方法之一是： 
nums3 = [nums1[0], nums1[1]] =&gt; [1,1] 
整个数组形成了一个长度为 2 的非递减子数组，并且是可达到的最大长度。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length == nums2.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义两个变量 $f$ 和 $g$，分别表示当前位置的最长非递减子数组长度，其中 $f$ 表示以 $nums1$ 元素为结尾的最长非递减子数组长度，而 $g$ 表示以 $nums2$ 元素为结尾的最长非递减子数组长度。初始时 $f = g = 1$，初始答案 $ans = 1$。

接下来，我们在 $i \in [1, n)$ 的范围内遍历数组元素，对于每个 $i$，我们定义两个变量 $ff$ 和 $gg$，分别表示以 $nums1[i]$ 和 $nums2[i]$ 元素为结尾的最长非递减子数组长度，初始化时 $ff = gg = 1$。

我们可以通过 $f$ 和 $g$ 的值来计算出 $ff$ 和 $gg$ 的值：

-   如果 $nums1[i] \ge nums1[i - 1]$，那么 $ff = \max(ff, f + 1)$；
-   如果 $nums1[i] \ge nums2[i - 1]$，那么 $ff = \max(ff, g + 1)$；
-   如果 $nums2[i] \ge nums1[i - 1]$，那么 $gg = \max(gg, f + 1)$；
-   如果 $nums2[i] \ge nums2[i - 1]$，那么 $gg = \max(gg, g + 1)$。

然后，我们更新 $f = ff$ 和 $g = gg$，并将 $ans$ 更新为 $\max(ans, f, g)$。

遍历结束后，我们返回 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxNonDecreasingLength(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        f = g = 1
        ans = 1
        for i in range(1, n):
            ff = gg = 1
            if nums1[i] >= nums1[i - 1]:
                ff = max(ff, f + 1)
            if nums1[i] >= nums2[i - 1]:
                ff = max(ff, g + 1)
            if nums2[i] >= nums1[i - 1]:
                gg = max(gg, f + 1)
            if nums2[i] >= nums2[i - 1]:
                gg = max(gg, g + 1)
            f, g = ff, gg
            ans = max(ans, f, g)
        return ans
```

```java
class Solution {
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int f = 1, g = 1;
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            int ff = 1, gg = 1;
            if (nums1[i] >= nums1[i - 1]) {
                ff = Math.max(ff, f + 1);
            }
            if (nums1[i] >= nums2[i - 1]) {
                ff = Math.max(ff, g + 1);
            }
            if (nums2[i] >= nums1[i - 1]) {
                gg = Math.max(gg, f + 1);
            }
            if (nums2[i] >= nums2[i - 1]) {
                gg = Math.max(gg, g + 1);
            }
            f = ff;
            g = gg;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxNonDecreasingLength(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int f = 1, g = 1;
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            int ff = 1, gg = 1;
            if (nums1[i] >= nums1[i - 1]) {
                ff = max(ff, f + 1);
            }
            if (nums1[i] >= nums2[i - 1]) {
                ff = max(ff, g + 1);
            }
            if (nums2[i] >= nums1[i - 1]) {
                gg = max(gg, f + 1);
            }
            if (nums2[i] >= nums2[i - 1]) {
                gg = max(gg, g + 1);
            }
            f = ff;
            g = gg;
            ans = max(ans, max(f, g));
        }
        return ans;
    }
};
```

```go
func maxNonDecreasingLength(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f, g, ans := 1, 1, 1
	for i := 1; i < n; i++ {
		ff, gg := 1, 1
		if nums1[i] >= nums1[i-1] {
			ff = max(ff, f+1)
		}
		if nums1[i] >= nums2[i-1] {
			ff = max(ff, g+1)
		}
		if nums2[i] >= nums1[i-1] {
			gg = max(gg, f+1)
		}
		if nums2[i] >= nums2[i-1] {
			gg = max(gg, g+1)
		}
		f, g = ff, gg
		ans = max(ans, max(f, g))
	}
	return ans
}
```

```ts
function maxNonDecreasingLength(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    let [f, g, ans] = [1, 1, 1];
    for (let i = 1; i < n; ++i) {
        let [ff, gg] = [1, 1];
        if (nums1[i] >= nums1[i - 1]) {
            ff = Math.max(ff, f + 1);
        }
        if (nums1[i] >= nums2[i - 1]) {
            ff = Math.max(ff, g + 1);
        }
        if (nums2[i] >= nums1[i - 1]) {
            gg = Math.max(gg, f + 1);
        }
        if (nums2[i] >= nums2[i - 1]) {
            gg = Math.max(gg, g + 1);
        }
        f = ff;
        g = gg;
        ans = Math.max(ans, f, g);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
