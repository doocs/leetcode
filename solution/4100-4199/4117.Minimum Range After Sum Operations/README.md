---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4117. 求和操作以后的最小极差](https://leetcode.cn/problems/minimum-range-after-sum-operations)

[English Version](/solution/4100-4199/4117.Minimum%20Range%20After%20Sum%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数 <code>a</code>、<code>b</code> 和 <code>c</code>。</p>

<p>你可以执行以下操作 <strong>任意</strong> 次（包括零次）：</p>

<ul>
	<li>选择三个整数中的一个，并将其 <strong>替换</strong> 为另外两个整数之和。另外两个整数保持不变。</li>
</ul>

<p>三个整数的 <strong>极差</strong> 定义为 <strong>最大值</strong> 与 <strong>最小值</strong> 之差。</p>

<p>返回执行任意次操作后可以得到的 <strong>最小极差</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 9, b = 2, c = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>一种得到最小极差的操作序列如下：</p>

<ul>
	<li>将 <code>a</code> 设为 <code>b + c = 2 + 5 = 7</code>。此时三个整数为 <code>a = 7</code>，<code>b = 2</code>，<code>c = 5</code>。</li>
	<li>三个整数的极差为 <code>max(7, 2, 5) - min(7, 2, 5) = 7 - 2 = 5</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 5, b = 7, c = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>不执行任何操作，三个整数的极差为 <code>max(5, 7, 6) - min(5, 7, 6) = 7 - 5 = 2</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = 3, b = 3, c = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不执行任何操作，三个整数的极差为 <code>max(3, 3, 3) - min(3, 3, 3) = 3 - 3 = 0</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

<!-- thinking:start -->

> **思考**
>
> 每次操作都把一个数换成另外两个正数之和，数值可以继续增大，枚举全部操作序列没有自然的终点。
>
> 极差等于最大值减去最小值。换出来的数不小于留下来的两个数，最小值不会下降，所以极差要变小，只能把最大值降下来。
>
> 将三个数排序后，只有把最大数换成另外两个数之和才可能降低最大值，换完后的极差等于中间那个数。替换最小数或中间数都会抬高最大值，而替换最大数之后再操作也不会得到更小的极差。因此比较原极差和排序后的中间数即可。

<!-- thinking:end -->

我们将 $a$、 $b$、 $c$ 放入 $\textit{nums}$ 并从小到大排序，于是 $\textit{nums}[0]\le\textit{nums}[1]\le\textit{nums}[2]$。

不操作时，极差为 $\textit{nums}[2]-\textit{nums}[0]$。三个数均为正整数，一次操作得到的新数不小于留下来的任意一个，因此最小值不会下降。最小值不下降时，只有把最大数换成另外两个数之和才可能减小极差，新的三个数为 $\textit{nums}[0]$、 $\textit{nums}[1]$、 $\textit{nums}[0]+\textit{nums}[1]$，极差等于 $\textit{nums}[1]$。若这个和仍然不小于原来的最大数，最大值没有下降。此后再操作，新的最大值至少为 $\textit{nums}[0]+\textit{nums}[1]$，极差不会更小。把最小数或中间数换成另外两个数之和，新数大于原来的最大数，极差也不会更小。

因此答案是 $\min(\textit{nums}[2]-\textit{nums}[0], \textit{nums}[1])$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRange(self, a: int, b: int, c: int) -> int:
        nums = [a, b, c]
        nums.sort()
        return min(nums[2] - nums[0], nums[1])
```

#### Java

```java
class Solution {
    public int minRange(int a, int b, int c) {
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        return Math.min(nums[2] - nums[0], nums[1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRange(int a, int b, int c) {
        vector<int> nums = {a, b, c};
        sort(nums.begin(), nums.end());
        return min(nums[2] - nums[0], nums[1]);
    }
};
```

#### Go

```go
func minRange(a int, b int, c int) int {
	nums := []int{a, b, c}
	sort.Ints(nums)
	return min(nums[2]-nums[0], nums[1])
}
```

#### TypeScript

```ts
function minRange(a: number, b: number, c: number): number {
    const nums = [a, b, c].sort((a, b) => a - b);
    return Math.min(nums[2] - nums[0], nums[1]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
