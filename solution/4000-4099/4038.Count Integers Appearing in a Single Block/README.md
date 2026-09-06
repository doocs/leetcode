---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4038.Count%20Integers%20Appearing%20in%20a%20Single%20Block/README.md
rating: 1165
source: 第 517 场周赛 Q1
---

<!-- problem:start -->

# [4038. 统计特殊整数个数](https://leetcode.cn/problems/count-integers-appearing-in-a-single-block)

[English Version](/solution/4000-4099/4038.Count%20Integers%20Appearing%20in%20a%20Single%20Block/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果整数 <code>x</code> 在 <code>nums</code> 中的所有出现位置都位于同一个<strong>&nbsp;连续&nbsp;</strong>区间内，则称 <code>x</code> 为<strong>&nbsp;特殊整数</strong>。</p>

<p>返回 <code>nums</code> 中<strong>&nbsp;不同</strong>&nbsp;特殊整数的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>1 出现在下标 0 和 3，形成了两个分离的区间，因此它不是特殊整数。</li>
	<li>2 在下标 <code>[1, 2]</code> 处形成一个连续区间，因此它是特殊整数。</li>
</ul>

<p>因此，共有一个特殊整数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,1,2,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>3 在下标 <code>[0, 1]</code> 处形成一个连续区间，因此它是特殊整数。</li>
	<li>1 出现在下标 2 和 5，形成了两个分离的区间，因此它不是特殊整数。</li>
	<li>2 在下标 <code>[3, 4]</code> 处形成一个连续区间，因此它是特殊整数。</li>
</ul>

<p>因此，共有两个特殊整数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：统计每个整数所在的块数

把数组中每一段极大的连续相等元素称为一个 **块**。整数 $x$ 是特殊整数，当且仅当 $x$ 恰好只构成一个块。

因此，我们遍历数组，当 $i = 0$ 或 $\textit{nums}[i] \neq \textit{nums}[i - 1]$ 时，说明位置 $i$ 是一个新块的起点，我们将 $\textit{cnt}[\textit{nums}[i]]$ 加一。遍历结束后，统计 $\textit{cnt}$ 中值恰好为 $1$ 的整数个数即为答案。

时间复杂度 $O(n + M)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $\textit{nums}$ 的长度，而 $M = 100$ 是数组中元素的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialIntegers(self, nums: List[int]) -> int:
        cnt = Counter(x for i, x in enumerate(nums) if i == 0 or x != nums[i - 1])
        return sum(v == 1 for v in cnt.values())
```

#### Java

```java
class Solution {
    public int countSpecialIntegers(int[] nums) {
        int[] cnt = new int[101];
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                ++cnt[nums[i]];
            }
        }
        int ans = 0;
        for (int c : cnt) {
            if (c == 1) {
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
    int countSpecialIntegers(vector<int>& nums) {
        int cnt[101]{};
        for (int i = 0; i < nums.size(); ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                ++cnt[nums[i]];
            }
        }
        return count(begin(cnt), end(cnt), 1);
    }
};
```

#### Go

```go
func countSpecialIntegers(nums []int) int {
	cnt := [101]int{}
	for i, x := range nums {
		if i == 0 || x != nums[i-1] {
			cnt[x]++
		}
	}
	ans := 0
	for _, c := range cnt {
		if c == 1 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSpecialIntegers(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (let i = 0; i < nums.length; ++i) {
        if (i === 0 || nums[i] !== nums[i - 1]) {
            ++cnt[nums[i]];
        }
    }
    return cnt.filter(c => c === 1).length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
