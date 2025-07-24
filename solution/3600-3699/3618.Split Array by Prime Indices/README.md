---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3618.Split%20Array%20by%20Prime%20Indices/README.md
tags:
    - 数组
    - 数学
    - 数论
---

<!-- problem:start -->

# [3618. 根据质数下标分割数组](https://leetcode.cn/problems/split-array-by-prime-indices)

[English Version](/solution/3600-3699/3618.Split%20Array%20by%20Prime%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>根据以下规则将 <code>nums</code> 分割成两个数组 <code>A</code> 和 <code>B</code>：</p>

<ul>
	<li><code>nums</code> 中位于&nbsp;<strong>质数 </strong>下标的元素必须放入数组 <code>A</code>。</li>
	<li>所有其他元素必须放入数组 <code>B</code>。</li>
</ul>

<p>返回两个数组和的&nbsp;<strong>绝对&nbsp;</strong>差值：<code>|sum(A) - sum(B)|</code>。</p>

<p><strong>质数&nbsp;</strong>是一个大于 1 的自然数，它只有两个因子，1和它本身。</p>

<p><strong>注意</strong>：空数组的和为 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [2,3,4]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>数组中唯一的质数下标是 2，所以 <code>nums[2] = 4</code> 被放入数组 <code>A</code>。</li>
	<li>其余元素 <code>nums[0] = 2</code> 和 <code>nums[1] = 3</code> 被放入数组 <code>B</code>。</li>
	<li><code>sum(A) = 4</code>，<code>sum(B) = 2 + 3 = 5</code>。</li>
	<li>绝对差值是 <code>|4 - 5| = 1</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [-1,5,7,0]</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>数组中的质数下标是 2 和 3，所以 <code>nums[2] = 7</code> 和 <code>nums[3] = 0</code> 被放入数组 <code>A</code>。</li>
	<li>其余元素 <code>nums[0] = -1</code> 和 <code>nums[1] = 5</code> 被放入数组 <code>B</code>。</li>
	<li><code>sum(A) = 7 + 0 = 7</code>，<code>sum(B) = -1 + 5 = 4</code>。</li>
	<li>绝对差值是 <code>|7 - 4| = 3</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：埃氏筛 + 模拟

我们可以用埃氏筛法预处理出 $[0, 10^5]$ 范围内的所有质数。然后遍历数组 $
\textit{nums}$，对于 $\textit{nums}[i]$，如果 $i$ 是质数，则将 $\textit{nums}[i]$ 加到答案中，否则将 $-\textit{nums}[i]$ 加到答案中。最后返回答案的绝对值。

忽略预处理的时间和空间，时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
m = 10**5 + 10
primes = [True] * m
primes[0] = primes[1] = False
for i in range(2, m):
    if primes[i]:
        for j in range(i + i, m, i):
            primes[j] = False


class Solution:
    def splitArray(self, nums: List[int]) -> int:
        return abs(sum(x if primes[i] else -x for i, x in enumerate(nums)))
```

#### Java

```java
class Solution {
    private static final int M = 100000 + 10;
    private static boolean[] primes = new boolean[M];

    static {
        for (int i = 0; i < M; i++) {
            primes[i] = true;
        }
        primes[0] = primes[1] = false;

        for (int i = 2; i < M; i++) {
            if (primes[i]) {
                for (int j = i + i; j < M; j += i) {
                    primes[j] = false;
                }
            }
        }
    }

    public long splitArray(int[] nums) {
        long ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            ans += primes[i] ? nums[i] : -nums[i];
        }
        return Math.abs(ans);
    }
}
```

#### C++

```cpp
const int M = 1e5 + 10;
bool primes[M];
auto init = [] {
    memset(primes, true, sizeof(primes));
    primes[0] = primes[1] = false;
    for (int i = 2; i < M; ++i) {
        if (primes[i]) {
            for (int j = i + i; j < M; j += i) {
                primes[j] = false;
            }
        }
    }
    return 0;
}();

class Solution {
public:
    long long splitArray(vector<int>& nums) {
        long long ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            ans += primes[i] ? nums[i] : -nums[i];
        }
        return abs(ans);
    }
};
```

#### Go

```go
const M = 100000 + 10

var primes [M]bool

func init() {
	for i := 0; i < M; i++ {
		primes[i] = true
	}
	primes[0], primes[1] = false, false

	for i := 2; i < M; i++ {
		if primes[i] {
			for j := i + i; j < M; j += i {
				primes[j] = false
			}
		}
	}
}

func splitArray(nums []int) (ans int64) {
	for i, num := range nums {
		if primes[i] {
			ans += int64(num)
		} else {
			ans -= int64(num)
		}
	}
	return max(ans, -ans)
}
```

#### TypeScript

```ts
const M = 100000 + 10;
const primes: boolean[] = Array(M).fill(true);

const init = (() => {
    primes[0] = primes[1] = false;

    for (let i = 2; i < M; i++) {
        if (primes[i]) {
            for (let j = i + i; j < M; j += i) {
                primes[j] = false;
            }
        }
    }
})();

function splitArray(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < nums.length; i++) {
        ans += primes[i] ? nums[i] : -nums[i];
    }
    return Math.abs(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
