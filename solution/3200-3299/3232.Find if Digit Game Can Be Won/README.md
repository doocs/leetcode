---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3232.Find%20if%20Digit%20Game%20Can%20Be%20Won/README.md
---

<!-- problem:start -->

# [3232. 判断是否可以赢得数字游戏](https://leetcode.cn/problems/find-if-digit-game-can-be-won)

[English Version](/solution/3200-3299/3232.Find%20if%20Digit%20Game%20Can%20Be%20Won/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>正整数 </strong>数组 <code>nums</code>。</p>

<p>小红和小明正在玩游戏。在游戏中，小红可以从 <code>nums</code> 中选择所有个位数 <strong>或</strong> 所有两位数，剩余的数字归小明所有。如果小红所选数字之和 <strong>严格大于 </strong>小明的数字之和，则小红获胜。</p>

<p>如果小红能赢得这场游戏，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2,3,4,10]</span></p>

<p><strong>输出：</strong><span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>小红不管选个位数还是两位数都无法赢得比赛。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2,3,4,5,14]</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>小红选择个位数可以赢得比赛，所选数字之和为 15。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [5,5,5,25]</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>小红选择两位数可以赢得比赛，所选数字之和为 25。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 99</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：求和

根据题目描述，只要个位数之和不等于两位数之和，那么小红一定可以选择一个较大的和来获胜。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canAliceWin(self, nums: List[int]) -> bool:
        a = sum(x for x in nums if x < 10)
        b = sum(x for x in nums if x > 9)
        return a != b
```

#### Java

```java
class Solution {
    public boolean canAliceWin(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x < 10) {
                a += x;
            } else {
                b += x;
            }
        }
        return a != b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canAliceWin(vector<int>& nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x < 10) {
                a += x;
            } else {
                b += x;
            }
        }
        return a != b;
    }
};
```

#### Go

```go
func canAliceWin(nums []int) bool {
	a, b := 0, 0
	for _, x := range nums {
		if x < 10 {
			a += x
		} else {
			b += x
		}
	}
	return a != b
}
```

#### TypeScript

```ts
function canAliceWin(nums: number[]): boolean {
    let [a, b] = [0, 0];
    for (const x of nums) {
        if (x < 10) {
            a += x;
        } else {
            b += x;
        }
    }
    return a !== b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
