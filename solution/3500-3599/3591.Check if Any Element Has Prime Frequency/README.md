---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3591.Check%20if%20Any%20Element%20Has%20Prime%20Frequency/README.md
rating: 1234
source: 第 455 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 数学
    - 计数
    - 数论
---

<!-- problem:start -->

# [3591. 检查元素频次是否为质数](https://leetcode.cn/problems/check-if-any-element-has-prime-frequency)

[English Version](/solution/3500-3599/3591.Check%20if%20Any%20Element%20Has%20Prime%20Frequency/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果数组中任一元素的&nbsp;<strong>频次&nbsp;</strong>是&nbsp;<strong>质数</strong>，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>元素 <code>x</code> 的&nbsp;<strong>频次&nbsp;</strong>是它在数组中出现的次数。</p>

<p>质数是一个大于 1 的自然数，并且只有两个因数：1 和它本身。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4,5,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>数字 4 的频次是 2，而 2 是质数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>所有元素的频次都是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,2,2,4,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>数字 2 和 4 的频次都是质数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 判断质数

我们用一个哈希表 $\text{cnt}$ 统计每个元素的频次。然后遍历 $\text{cnt}$ 中的值，判断是否有质数，如果有则返回 `true`，否则返回 `false`。

时间复杂度 $O(n \times \sqrt{M})$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\text{nums}$ 的长度，而 $M$ 是 $\text{cnt}$ 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkPrimeFrequency(self, nums: List[int]) -> bool:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        cnt = Counter(nums)
        return any(is_prime(x) for x in cnt.values())
```

#### Java

```java
import java.util.*;

class Solution {
    public boolean checkPrimeFrequency(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }

        for (int x : cnt.values()) {
            if (isPrime(x)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkPrimeFrequency(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }

        for (auto& [_, x] : cnt) {
            if (isPrime(x)) {
                return true;
            }
        }
        return false;
    }

private:
    bool isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func checkPrimeFrequency(nums []int) bool {
	cnt := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
	}
	for _, x := range cnt {
		if isPrime(x) {
			return true
		}
	}
	return false
}

func isPrime(x int) bool {
	if x < 2 {
		return false
	}
	for i := 2; i*i <= x; i++ {
		if x%i == 0 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function checkPrimeFrequency(nums: number[]): boolean {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    for (const x of Object.values(cnt)) {
        if (isPrime(x)) {
            return true;
        }
    }
    return false;
}

function isPrime(x: number): boolean {
    if (x < 2) {
        return false;
    }
    for (let i = 2; i * i <= x; i++) {
        if (x % i === 0) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
