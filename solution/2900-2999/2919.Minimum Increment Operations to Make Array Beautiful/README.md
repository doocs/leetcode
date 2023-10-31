# [2919. 使数组变美的最小增量运算数](https://leetcode.cn/problems/minimum-increment-operations-to-make-array-beautiful)

[English Version](/solution/2900-2999/2919.Minimum%20Increment%20Operations%20to%20Make%20Array%20Beautiful/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组 <code>nums</code> ，和一个整数 <code>k</code> 。</p>

<p>你可以执行下述 <strong>递增</strong> 运算 <strong>任意</strong> 次（可以是 <strong>0</strong> 次）：</p>

<ul>
	<li>从范围&nbsp;<code>[0, n - 1]</code> 中选则一个下标 <code>i</code> ，并将 <code>nums[i]</code> 的值加 <code>1</code> 。</li>
</ul>

<p>如果数组中任何长度 <strong>大于或等于 3</strong> 的子数组，其 <strong>最大</strong> 元素都大于或等于 <code>k</code> ，则认为数组是一个 <strong>美丽数组</strong> 。</p>

<p>以整数形式返回使数组变为 <strong>美丽数组</strong> 需要执行的 <strong>最小</strong> 递增运算数。</p>

<p>子数组是数组中的一个连续 <strong>非空</strong> 元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,0,0,2], k = 4
<strong>输出：</strong>3
<strong>解释：</strong>可以执行下述递增运算，使 nums 变为美丽数组：
选择下标 i = 1 ，并且将 nums[1] 的值加 1 -&gt; [2,4,0,0,2] 。
选择下标 i = 4 ，并且将 nums[4] 的值加 1 -&gt; [2,4,0,0,3] 。
选择下标 i = 4 ，并且将 nums[4] 的值加 1 -&gt; [2,4,0,0,4] 。
长度大于或等于 3 的子数组为 [2,4,0], [4,0,0], [0,0,4], [2,4,0,0], [4,0,0,4], [2,4,0,0,4] 。
在所有子数组中，最大元素都等于 k = 4 ，所以 nums 现在是美丽数组。
可以证明无法用少于 3 次递增运算使 nums 变为美丽数组。
因此，答案为 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,3,3], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>可以执行下述递增运算，使 nums 变为美丽数组：
选择下标 i = 2 ，并且将 nums[2] 的值加 1 -&gt; [0,1,4,3] 。
选择下标 i = 2 ，并且将 nums[2] 的值加 1 -&gt; [0,1,5,3] 。
长度大于或等于 3 的子数组为 [0,1,5]、[1,5,3]、[0,1,5,3] 。
在所有子数组中，最大元素都等于 k = 5 ，所以 nums 现在是美丽数组。
可以证明无法用少于 2 次递增运算使 nums 变为美丽数组。 
因此，答案为 2 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2], k = 1
<strong>输出：</strong>0
<strong>解释：</strong>在这个示例中，只有一个长度大于或等于 3 的子数组 [1,1,2] 。
其最大元素 2 已经大于 k = 1 ，所以无需执行任何增量运算。
因此，答案为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f$, $g$, $h$ 表示前 $i$ 项中，分别以最后三项作为子数组的最大值所需要的最小增量运算数，初始时 $f = 0$, $g = 0$, $h = 0$。

接下来，我们遍历数组 $nums$，对于每个 $x$，我们需要更新 $f$, $g$, $h$ 的值，使其满足题目要求，即：

$$
\begin{aligned}
f' &= g \\
g' &= h \\
h' &= \min(f, g, h) + \max(k - x, 0)
\end{aligned}
$$

最后，我们只需要返回 $f$, $g$, $h$ 中的最小值即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minIncrementOperations(self, nums: List[int], k: int) -> int:
        f = g = h = 0
        for x in nums:
            f, g, h = g, h, min(f, g, h) + max(k - x, 0)
        return min(f, g, h)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minIncrementOperations(int[] nums, int k) {
        long f = 0, g = 0, h = 0;
        for (int x : nums) {
            long hh = Math.min(Math.min(f, g), h) + Math.max(k - x, 0);
            f = g;
            g = h;
            h = hh;
        }
        return Math.min(Math.min(f, g), h);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minIncrementOperations(vector<int>& nums, int k) {
        long long f = 0, g = 0, h = 0;
        for (int x : nums) {
            long long hh = min({f, g, h}) + max(k - x, 0);
            f = g;
            g = h;
            h = hh;
        }
        return min({f, g, h});
    }
};
```

### **Go**

```go
func minIncrementOperations(nums []int, k int) int64 {
	var f, g, h int
	for _, x := range nums {
		f, g, h = g, h, min(f, g, h)+max(k-x, 0)
	}
	return int64(min(f, g, h))
}
```

### **TypeScript**

```ts
function minIncrementOperations(nums: number[], k: number): number {
    let [f, g, h] = [0, 0, 0];
    for (const x of nums) {
        [f, g, h] = [g, h, Math.min(f, g, h) + Math.max(k - x, 0)];
    }
    return Math.min(f, g, h);
}
```

### **...**

```

```

<!-- tabs:end -->
