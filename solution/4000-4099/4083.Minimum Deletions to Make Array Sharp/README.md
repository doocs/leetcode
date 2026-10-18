---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4083. 使相邻元素按位异或非零的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-array-sharp)

[English Version](/solution/4000-4099/4083.Minimum%20Deletions%20to%20Make%20Array%20Sharp/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>你可以从 <code>nums</code> 中删除任意数量的元素。剩余元素保持原有的相对顺序。</p>

<p>如果结果数组 <code>a</code> 中每一对<strong>&nbsp;相邻</strong>&nbsp;元素的&nbsp;<strong>按位异或&nbsp;</strong>结果都&nbsp;<strong>不为 0</strong>，则称数组 <code>a</code> 是&nbsp;<strong>相邻异或非零数组&nbsp;</strong>。也就是说，对于每个满足 <code>0 &lt;= i &lt; a.length - 1</code> 的下标 <code>i</code>，都有 <code>a[i] XOR a[i + 1] != 0</code>。长度不超过 1 的数组也被视为相邻异或非零数组。</p>

<p>返回使 <code>nums</code> 变为相邻异或非零数组所需的&nbsp;<strong>最少&nbsp;</strong>删除次数。</p>

<p><strong>按位异或&nbsp;</strong>会逐位比较两个整数对应的二进制位。如果对应位不同，则结果中的该位为 1；否则为 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,2,2,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标对 <code>(0, 1)</code>、<code>(2, 3)</code> 和 <code>(3, 4)</code> 对应元素的异或结果均为 0。</li>
	<li>删除一个 1 和两个 2 后，得到 <code>[1, 2, 3]</code>，其中 <code>1 XOR 2 == 3</code> 且 <code>2 XOR 3 == 1</code>，两者都不为 0。因此，答案为 3。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,4,9,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标对 <code>(0, 1)</code> 对应元素的异或结果为 0。</li>
	<li>删除这两个 4 中的任意一个后，得到 <code>[4, 9, 4]</code>，其中 <code>4 XOR 9 == 13</code> 且 <code>9 XOR 4 == 13</code>，两者都不为 0。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：统计相邻相等元素

<!-- thinking:start -->

> **思考**
>
> $n$ 不超过 $100$，逐对比较相邻元素完全来得及，但枚举删除子集仍然是指数级的。相邻异或是否为 $0$，只取决于两个数相不相等，不需要搜索子集。
>
> 瓶颈在连续相同的一段上。相对顺序固定，这一段只能留下一个元素。不同数值的段之间本来就不相等，删掉段内多余元素不会让它们互相影响。
>
> 长度为 $k$ 的相同段要删除 $k-1$ 个元素，段内相邻相等的位置对也正好是 $k-1$ 个。一次从左到右的扫描，数出 $\textit{nums}[i]=\textit{nums}[i-1]$ 的次数，就是最少删除次数。

<!-- thinking:end -->

两个整数的按位异或为 $0$，当且仅当它们相等。题目因此等价于：在保持相对顺序的前提下，让剩余数组中任意相邻元素都不相同，并且删除次数最少。

从左到右扫描数组。若 $\textit{nums}[i]=\textit{nums}[i-1]$，这两个位置不能同时留下。把每一段连续相同的元素看成一组，长度为 $k$ 的组必须删掉 $k-1$ 个，组内相邻相等的位置对也恰好有 $k-1$ 个。相邻两组的交界处元素已经不同，每组留下一个之后，接在一起的元素仍然不相等。因此最少删除次数等于相邻相等的位置对数。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletions(self, nums: list[int]) -> int:
        return sum(x == y for x, y in pairwise(nums))
```

#### Java

```java
class Solution {
    public int minDeletions(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) {
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
    int minDeletions(vector<int>& nums) {
        int ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] == nums[i - 1]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletions(nums []int) (ans int) {
	for i := 1; i < len(nums); i++ {
		if nums[i] == nums[i-1] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minDeletions(nums: number[]): number {
    let ans: number = 0;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] === nums[i - 1]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
