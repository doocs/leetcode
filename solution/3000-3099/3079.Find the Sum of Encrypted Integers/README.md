---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3079.Find%20the%20Sum%20of%20Encrypted%20Integers/README.md
rating: 1190
source: 第 126 场双周赛 Q1
tags:
    - 数组
    - 数学
---

# [3079. 求出加密整数的和](https://leetcode.cn/problems/find-the-sum-of-encrypted-integers)

[English Version](/solution/3000-3099/3079.Find%20the%20Sum%20of%20Encrypted%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，数组中的元素都是&nbsp;<strong>正</strong>&nbsp;整数。定义一个加密函数&nbsp;<code>encrypt</code>&nbsp;，<code>encrypt(x)</code>&nbsp;将一个整数 <code>x</code>&nbsp;中 <strong>每一个</strong>&nbsp;数位都用 <code>x</code>&nbsp;中的&nbsp;<strong>最大</strong>&nbsp;数位替换。比方说&nbsp;<code>encrypt(523) = 555</code> 且&nbsp;<code>encrypt(213) = 333</code>&nbsp;。</p>

<p>请你返回数组中所有元素加密后的 <strong>和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,2,3]</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">6</span></p>

<p><b>解释：</b>加密后的元素位&nbsp;<code>[1,2,3]</code>&nbsp;。加密元素的和为&nbsp;<code>1 + 2 + 3 == 6</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [10,21,31]</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">66</span></p>

<p><b>解释：</b>加密后的元素为&nbsp;<code>[11,22,33]</code>&nbsp;。加密元素的和为&nbsp;<code>11 + 22 + 33 == 66</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：模拟

我们直接模拟加密的过程，定义一个函数 $encrypt(x)$，将一个整数 $x$ 中每一个数位都用 $x$ 中的最大数位替换。函数的实现如下：

我们可以通过不断地对 $x$ 取模和整除 $10$ 来得到 $x$ 的每一位数，找到最大的数位，记为 $mx$。在循环的过程中，我们还可以用一个变量 $p$ 来记录 $mx$ 的基础底数，即 $p = 1, 11, 111, \cdots$。最后返回 $mx \times p$ 即可。

时间复杂度 $O(n \times \log M)$，其中 $n$ 是数组的长度，而 $M$ 是数组中元素的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sumOfEncryptedInt(self, nums: List[int]) -> int:
        def encrypt(x: int) -> int:
            mx = p = 0
            while x:
                x, v = divmod(x, 10)
                mx = max(mx, v)
                p = p * 10 + 1
            return mx * p

        return sum(encrypt(x) for x in nums)
```

```java
class Solution {
    public int sumOfEncryptedInt(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans += encrypt(x);
        }
        return ans;
    }

    private int encrypt(int x) {
        int mx = 0, p = 0;
        for (; x > 0; x /= 10) {
            mx = Math.max(mx, x % 10);
            p = p * 10 + 1;
        }
        return mx * p;
    }
}
```

```cpp
class Solution {
public:
    int sumOfEncryptedInt(vector<int>& nums) {
        auto encrypt = [&](int x) {
            int mx = 0, p = 0;
            for (; x; x /= 10) {
                mx = max(mx, x % 10);
                p = p * 10 + 1;
            }
            return mx * p;
        };
        int ans = 0;
        for (int x : nums) {
            ans += encrypt(x);
        }
        return ans;
    }
};
```

```go
func sumOfEncryptedInt(nums []int) (ans int) {
	encrypt := func(x int) int {
		mx, p := 0, 0
		for ; x > 0; x /= 10 {
			mx = max(mx, x%10)
			p = p*10 + 1
		}
		return mx * p
	}
	for _, x := range nums {
		ans += encrypt(x)
	}
	return
}
```

```ts
function sumOfEncryptedInt(nums: number[]): number {
    const encrypt = (x: number): number => {
        let [mx, p] = [0, 0];
        for (; x > 0; x = Math.floor(x / 10)) {
            mx = Math.max(mx, x % 10);
            p = p * 10 + 1;
        }
        return mx * p;
    };
    return nums.reduce((acc, x) => acc + encrypt(x), 0);
}
```

<!-- tabs:end -->

<!-- end -->
