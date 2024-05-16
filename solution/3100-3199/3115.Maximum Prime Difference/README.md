---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3115.Maximum%20Prime%20Difference/README.md
rating: 1294
source: 第 393 场周赛 Q2
tags:
    - 数组
    - 数学
    - 数论
---

# [3115. 质数的最大距离](https://leetcode.cn/problems/maximum-prime-difference)

[English Version](/solution/3100-3199/3115.Maximum%20Prime%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>返回两个（不一定不同的）质数在 <code>nums</code> 中&nbsp;<strong>下标</strong> 的 <strong>最大距离</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,2,9,5,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong> <code>nums[1]</code>、<code>nums[3]</code> 和 <code>nums[4]</code> 是质数。因此答案是 <code>|4 - 1| = 3</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,8,2,8]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong> <code>nums[2]</code> 是质数。因为只有一个质数，所以答案是 <code>|2 - 2| = 0</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li>输入保证 <code>nums</code> 中至少有一个质数。</li>
</ul>

## 解法

### 方法一：遍历

根据题目描述，我们需要找出第一个质数所在的下标 $i$，然后找出最后一个质数所在的下标 $j$，将 $j - i$ 作为答案返回即可。

因此，我们可以从左到右遍历数组，找到第一个质数所在的下标 $i$，然后从右到左遍历数组，找到最后一个质数所在的下标 $j$，答案即为 $j - i$。

时间复杂度 $O(n \times \sqrt{M})$，其中 $n$ 和 $M$ 分别是数组 $nums$ 的长度和数组中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maximumPrimeDifference(self, nums: List[int]) -> int:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        for i, x in enumerate(nums):
            if is_prime(x):
                for j in range(len(nums) - 1, i - 1, -1):
                    if is_prime(nums[j]):
                        return j - i
```

```java
class Solution {
    public int maximumPrimeDifference(int[] nums) {
        for (int i = 0;; ++i) {
            if (isPrime(nums[i])) {
                for (int j = nums.length - 1;; --j) {
                    if (isPrime(nums[j])) {
                        return j - i;
                    }
                }
            }
        }
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int v = 2; v * v <= x; ++v) {
            if (x % v == 0) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    int maximumPrimeDifference(vector<int>& nums) {
        for (int i = 0;; ++i) {
            if (isPrime(nums[i])) {
                for (int j = nums.size() - 1;; --j) {
                    if (isPrime(nums[j])) {
                        return j - i;
                    }
                }
            }
        }
    }

    bool isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n / i; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func maximumPrimeDifference(nums []int) int {
	for i := 0; ; i++ {
		if isPrime(nums[i]) {
			for j := len(nums) - 1; ; j-- {
				if isPrime(nums[j]) {
					return j - i
				}
			}
		}
	}
}

func isPrime(n int) bool {
	if n < 2 {
		return false
	}
	for i := 2; i <= n/i; i++ {
		if n%i == 0 {
			return false
		}
	}
	return true
}
```

```ts
function maximumPrimeDifference(nums: number[]): number {
    const isPrime = (x: number): boolean => {
        if (x < 2) {
            return false;
        }
        for (let i = 2; i <= x / i; i++) {
            if (x % i === 0) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0; ; ++i) {
        if (isPrime(nums[i])) {
            for (let j = nums.length - 1; ; --j) {
                if (isPrime(nums[j])) {
                    return j - i;
                }
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
