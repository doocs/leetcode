---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3158.Find%20the%20XOR%20of%20Numbers%20Which%20Appear%20Twice/README.md
---

<!-- problem:start -->

# [3158. 求出出现两次数字的 XOR 值](https://leetcode.cn/problems/find-the-xor-of-numbers-which-appear-twice)

[English Version](/solution/3100-3199/3158.Find%20the%20XOR%20of%20Numbers%20Which%20Appear%20Twice/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;，数组中的数字 <strong>要么</strong> 出现一次，<strong>要么</strong>&nbsp;出现两次。</p>

<p>请你返回数组中所有出现两次数字的按位<em>&nbsp;</em><code>XOR</code>&nbsp;值，如果没有数字出现过两次，返回 0 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,3]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;中唯一出现过两次的数字是 1 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;中没有数字出现两次。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,2,1]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>数字 1 和&nbsp;2 出现过两次。<code>1 XOR 2 == 3</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>nums</code>&nbsp;中每个数字要么出现过一次，要么出现过两次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们定义一个数组或哈希表 $\text{cnt}$ 记录每个数字出现的次数。

接下来，遍历数组 $\text{nums}$，当某个数字出现两次时，我们将其与答案进行异或运算。

最后返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $\text{nums}$ 的长度，而 $M$ 是数组 $\text{nums}$ 中的最大值或数组 $\text{nums}$ 不同数字的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def duplicateNumbersXOR(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return reduce(xor, [x for x, v in cnt.items() if v == 2], 0)
```

#### Java

```java
class Solution {
    public int duplicateNumbersXOR(int[] nums) {
        int[] cnt = new int[51];
        int ans = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans ^= x;
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
    int duplicateNumbersXOR(vector<int>& nums) {
        int cnt[51]{};
        int ans = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans ^= x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func duplicateNumbersXOR(nums []int) (ans int) {
	cnt := [51]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] == 2 {
			ans ^= x
		}
	}
	return
}
```

#### TypeScript

```ts
function duplicateNumbersXOR(nums: number[]): number {
    const cnt: number[] = Array(51).fill(0);
    let ans: number = 0;
    for (const x of nums) {
        if (++cnt[x] === 2) {
            ans ^= x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
