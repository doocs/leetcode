---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3289.The%20Two%20Sneaky%20Numbers%20of%20Digitville/README.md
rating: 1163
source: 第 415 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 数学
---

<!-- problem:start -->

# [3289. 数字小镇中的捣蛋鬼](https://leetcode.cn/problems/the-two-sneaky-numbers-of-digitville)

[English Version](/solution/3200-3299/3289.The%20Two%20Sneaky%20Numbers%20of%20Digitville/README_EN.md)

## 题目描述

<!-- description:start -->

<p>数字小镇 Digitville 中，存在一个数字列表 <code>nums</code>，其中包含从 <code>0</code> 到 <code>n - 1</code> 的整数。每个数字本应 <strong>只出现一次</strong>，然而，有 <strong>两个 </strong>顽皮的数字额外多出现了一次，使得列表变得比正常情况下更长。</p>

<p>为了恢复 Digitville 的和平，作为小镇中的名侦探，请你找出这两个顽皮的数字。</p>

<p>返回一个长度为 2 的数组，包含这两个数字（顺序任意）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,1,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,1]</span></p>

<p><strong>解释：</strong></p>

<p>数字 0 和 1 分别在数组中出现了两次。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,3,2,1,3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,3]</span></p>

<p><strong>解释: </strong></p>

<p>数字 2 和 3 分别在数组中出现了两次。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,1,5,4,3,4,6,0,9,5,8,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[4,5]</span></p>

<p><strong>解释: </strong></p>

<p>数字 4 和 5 分别在数组中出现了两次。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>nums.length == n + 2</code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li>输入保证 <code>nums</code> 中<strong> 恰好 </strong>包含两个重复的元素。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用一个数组 $\textit{cnt}$ 记录每个数字出现的次数。

遍历数组 $\textit{nums}$，当某个数字出现次数为 $2$ 时，将其加入答案数组中。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSneakyNumbers(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        return [x for x, v in cnt.items() if v == 2]
```

#### Java

```java
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] ans = new int[2];
        int[] cnt = new int[100];
        int k = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans[k++] = x;
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
    vector<int> getSneakyNumbers(vector<int>& nums) {
        vector<int> ans;
        int cnt[100]{};
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getSneakyNumbers(nums []int) (ans []int) {
	cnt := [100]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] == 2 {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function getSneakyNumbers(nums: number[]): number[] {
    const ans: number[] = [];
    const cnt: number[] = Array(100).fill(0);
    for (const x of nums) {
        if (++cnt[x] > 1) {
            ans.push(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
