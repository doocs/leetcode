---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3192.Minimum%20Operations%20to%20Make%20Binary%20Array%20Elements%20Equal%20to%20One%20II/README.md
rating: 1432
source: 第 133 场双周赛 Q3
tags:
    - 贪心
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3192. 使二进制数组全部等于 1 的最少操作次数 II](https://leetcode.cn/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii)

[English Version](/solution/3100-3199/3192.Minimum%20Operations%20to%20Make%20Binary%20Array%20Elements%20Equal%20to%20One%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你可以对数组执行以下操作&nbsp;<strong>任意</strong>&nbsp;次（也可以 0 次）：</p>

<ul>
	<li>选择数组中 <strong>任意</strong>&nbsp;一个下标 <code>i</code>&nbsp;，并将从下标 <code>i</code>&nbsp;开始一直到数组末尾 <strong>所有</strong>&nbsp;元素 <strong>反转</strong>&nbsp;。</li>
</ul>

<p><b>反转</b>&nbsp;一个元素指的是将它的值从 0 变 1 ，或者从 1 变 0 。</p>

<p>请你返回将 <code>nums</code>&nbsp;中所有元素变为 1 的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [0,1,1,0,1]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong><br />
我们可以执行以下操作：</p>

<ul>
	<li>选择下标&nbsp;<code>i = 1</code>&nbsp;执行操作，得到<span class="example-io">&nbsp;<code>nums = [0,<u><strong>0</strong></u>,<u><strong>0</strong></u>,<u><strong>1</strong></u>,<u><strong>0</strong></u>]</code>&nbsp;。</span></li>
	<li>选择下标&nbsp;<code>i = 0</code>&nbsp;执行操作，得到<span class="example-io">&nbsp;<code>nums = [<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>0</strong></u>,<u><strong>1</strong></u>]</code>&nbsp;。</span></li>
	<li>选择下标&nbsp;<code>i = 4</code>&nbsp;执行操作，得到<span class="example-io">&nbsp;<code>nums = [1,1,1,0,<u><strong>0</strong></u>]</code>&nbsp;。</span></li>
	<li>选择下标&nbsp;<code>i = 3</code>&nbsp;执行操作，得到<span class="example-io">&nbsp;<code>nums = [1,1,1,<u><strong>1</strong></u>,<u><strong>1</strong></u>]</code>&nbsp;。</span></li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,0,0,0]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong><br />
我们可以执行以下操作：</p>

<ul>
	<li>选择下标&nbsp;<code>i = 1</code>&nbsp;执行操作，得到<span class="example-io">&nbsp;<code>nums = [1,<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>1</strong></u>]</code>&nbsp;。</span></li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们注意到，每当我们将某个位置的元素变为 1 时，它的右侧的所有元素都会被反转。因此，我们可以用一个变量 $v$ 来记录当前位置及其右侧的元素是否被反转，如果被反转，那么 $v$ 的值为 1，否则为 0。

我们遍历数组 $\textit{nums}$，对于每个元素 $x$，我们将 $x$ 与 $v$ 进行异或运算，如果 $x$ 为 0，那么我们需要将 $x$ 变为 1，我们需要进行反转操作，我们将答案加一，并将 $v$ 取反。

遍历结束后，我们就可以得到最少操作次数。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        ans = v = 0
        for x in nums:
            x ^= v
            if x == 0:
                ans += 1
                v ^= 1
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        int ans = 0, v = 0;
        for (int x : nums) {
            x ^= v;
            if (x == 0) {
                v ^= 1;
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0, v = 0;
        for (int x : nums) {
            x ^= v;
            if (x == 0) {
                v ^= 1;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) (ans int) {
	v := 0
	for _, x := range nums {
		x ^= v
		if x == 0 {
			v ^= 1
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    let [ans, v] = [0, 0];
    for (let x of nums) {
        x ^= v;
        if (x === 0) {
            v ^= 1;
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
