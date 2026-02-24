---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3847.Find%20the%20Score%20Difference%20in%20a%20Game/README.md
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [3847. 计算比赛分数差](https://leetcode.cn/problems/find-the-score-difference-in-a-game)

[English Version](/solution/3800-3899/3847.Find%20the%20Score%20Difference%20in%20a%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，其中 <code>nums[i]</code> 表示在第 <code>i</code>&nbsp;场比赛中获得的分数。</p>

<p><strong>恰好 </strong>有两位玩家。初始时，第一位玩家为<strong>&nbsp;主动玩家</strong>，第二位玩家为&nbsp;<strong>被动玩家</strong>。</p>

<p><strong>按顺序</strong>&nbsp;将下述规则应用于每场比赛 <code>i</code>：</p>

<ul>
	<li>如果 <code>nums[i]</code> 是奇数，主动玩家和被动玩家互换角色。</li>
	<li>在每第 6 场比赛（即比赛索引为 <code>5, 11, 17, ...</code> 的比赛中），主动玩家和被动玩家互换角色。</li>
	<li>主动玩家参与第 <code>i</code>&nbsp;场比赛，并获得 <code>nums[i]</code> 分。</li>
</ul>

<p>返回<strong>&nbsp;分数差</strong>，即第一位玩家的&nbsp;<strong>总分&nbsp;</strong>减去第二位玩家的&nbsp;<strong>总分&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong>​​​​​​​</p>

<ul>
	<li>第 0 场比赛：分数为奇数，第二位玩家成为主动玩家，获得 <code>nums[0] = 1</code> 分。</li>
	<li>第 1 场比赛：没有交换角色。第二位玩家获得 <code>nums[1] = 2</code> 分。</li>
	<li>第 2 场比赛：分数为奇数，第一位玩家成为主动玩家，获得 <code>nums[2] = 3</code> 分。</li>
	<li>分数差为 <code>3 - 3 = 0</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,4,2,1,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 0 到第 2 场比赛：第一位玩家获得 <code>2 + 4 + 2 = 8</code> 分。</li>
	<li>第 3 场比赛：分数为奇数，第二位玩家成为主动玩家，获得 <code>nums[3] = 1</code> 分。</li>
	<li>第 4 场比赛：第二位玩家获得 <code>nums[4] = 2</code> 分。</li>
	<li>第 5 场比赛：分数为奇数，玩家互换角色。由于这是第 6 场比赛，玩家再次互换角色。第二位玩家获得 <code>nums[5] = 1</code> 分。</li>
	<li>分数差为 <code>8 - 4 = 4</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 0 场比赛：分数为奇数，第二位玩家成为主动玩家，获得 <code>nums[0] = 1</code> 分。</li>
	<li>分数差为 <code>0 - 1 = -1</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个变量 $k$ 来表示当前玩家的角色，初始时 $k = 1$，当 $k = 1$ 时表示第一位玩家是主动玩家，当 $k = -1$ 时表示第二位玩家是主动玩家。对于每场比赛，我们根据题目描述更新 $k$ 的值，并将当前比赛的分数乘以 $k$ 加到答案中。最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def scoreDifference(self, nums: List[int]) -> int:
        ans, k = 0, 1
        for i, x in enumerate(nums):
            if x % 2:
                k *= -1
            if i % 6 == 5:
                k *= -1
            ans += k * x
        return ans
```

#### Java

```java
class Solution {
    public int scoreDifference(int[] nums) {
        int ans = 0;
        int k = 1;
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i];
            if ((x & 1) == 1) {
                k = -k;
            }
            if (i % 6 == 5) {
                k = -k;
            }
            ans += k * x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int scoreDifference(vector<int>& nums) {
        int ans = 0;
        int k = 1;
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i];
            if (x & 1) {
                k = -k;
            }
            if (i % 6 == 5) {
                k = -k;
            }
            ans += k * x;
        }
        return ans;
    }
};
```

#### Go

```go
func scoreDifference(nums []int) int {
	ans := 0
	k := 1
	for i, x := range nums {
		if x%2 != 0 {
			k = -k
		}
		if i%6 == 5 {
			k = -k
		}
		ans += k * x
	}
	return ans
}
```

#### TypeScript

```ts
function scoreDifference(nums: number[]): number {
    let ans = 0;
    let k = 1;

    nums.forEach((x, i) => {
        if (x % 2 !== 0) {
            k = -k;
        }
        if (i % 6 === 5) {
            k = -k;
        }
        ans += k * x;
    });

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
