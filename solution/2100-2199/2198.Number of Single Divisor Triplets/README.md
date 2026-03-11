---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2198.Number%20of%20Single%20Divisor%20Triplets/README.md
tags:
    - 数组
    - 计数
    - 枚举
---

<!-- problem:start -->

# [2198. 单因数三元组 🔒](https://leetcode.cn/problems/number-of-single-divisor-triplets)

[English Version](/solution/2100-2199/2198.Number%20of%20Single%20Divisor%20Triplets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个下标从 <strong>0</strong> 开始的正整数数组 <code>nums</code>。由三个&nbsp;<strong>不同&nbsp;</strong>索引&nbsp;<code>(i, j, k)</code> 组成的三元组，如果 <code>nums[i] + nums[j] + nums[k]</code> 能被 <code>nums[i]</code>、<code>nums[j]</code>&nbsp;或 <code>nums[k]</code> 中的&nbsp;<strong>一个&nbsp;</strong>整除，则称为 <code>nums</code> 的&nbsp;<strong>单因数三元组</strong>。</p>

<p>返回 <em><code>nums</code> 的单因数三元组</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,6,7,3,2]
<strong>输出:</strong> 12
<strong>解释:
</strong>三元组索引 (0, 3, 4), (0, 4, 3), (3, 0, 4), (3, 4, 0), (4, 0, 3), 和 (4, 3, 0) 的值为 [4, 3, 2] (或者说排列为 [4, 3, 2]).
4 + 3 + 2 = 9 只能被 3 整除，所以所有的三元组都是单因数三元组。
三元组索引 (0, 2, 3), (0, 3, 2), (2, 0, 3), (2, 3, 0), (3, 0, 2), 和 (3, 2, 0) 的值为 [4, 7, 3]  (或者说排列为 [4, 7, 3]).
4 + 7 + 3 = 14 只能被 7 整除，所以所有的三元组都是单因数三元组。
一共有 12 个单因数三元组。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,2]
<strong>输出:</strong> 6
<strong>提示:</strong>
三元组索引 (0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), 和 (2, 1, 0) 的值为 [1, 2, 2] (或者说排列为 [1, 2, 2]).
1 + 2 + 2 = 5 只能被 1 整除，所以所有的三元组都是单因数三元组。
一共有6个单因数三元组。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,1,1]
<strong>输出:</strong> 0
<strong>提示:</strong>
没有单因数三元组。
注意 (0, 1, 2) 不是单因数三元组。 因为 nums[0] + nums[1] + nums[2] = 3，3 可以被 nums[0], nums[1], nums[2] 整除。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

我们注意到，数组 $\textit{nums}$ 的元素的范围是 $[1, 100]$，因此我们可以枚举三个数 $a, b, c$，其中 $a, b, c \in [1, 100]$，然后判断 $a + b + c$ 是否只能被 $a, b, c$ 中的一个数整除。如果是，则我们可以计算出以 $a, b, c$ 为元素的单因数三元组的个数。具体计算方法如下：

- 如果 $a = b$，那么以 $a, b, c$ 为元素的单因数三元组的个数为 $x \times (x - 1) \times z$，其中 $x$, $y$, $z$ 分别表示 $a$, $b$, $c$ 在数组 $\textit{nums}$ 中出现的次数。
- 如果 $a = c$，那么以 $a, b, c$ 为元素的单因数三元组的个数为 $x \times (x - 1) \times y$。
- 如果 $b = c$，那么以 $a, b, c$ 为元素的单因数三元组的个数为 $x \times y \times (y - 1)$。
- 如果 $a, b, c$ 互不相等，那么以 $a, b, c$ 为元素的单因数三元组的个数为 $x \times y \times z$。

最后，我们将所有的单因数三元组的个数相加即可。

时间复杂度 $O(M^3)$，空间复杂度 $O(M)$。其中 $M$ 为数组 $\textit{nums}$ 中元素的取值范围。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleDivisorTriplet(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        ans = 0
        for a, x in cnt.items():
            for b, y in cnt.items():
                for c, z in cnt.items():
                    s = a + b + c
                    if sum(s % v == 0 for v in (a, b, c)) == 1:
                        if a == b:
                            ans += x * (x - 1) * z
                        elif a == c:
                            ans += x * (x - 1) * y
                        elif b == c:
                            ans += x * y * (y - 1)
                        else:
                            ans += x * y * z
        return ans
```

#### Java

```java
class Solution {
    public long singleDivisorTriplet(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        long ans = 0;
        for (int a = 1; a <= 100; ++a) {
            for (int b = 1; b <= 100; ++b) {
                for (int c = 1; c <= 100; ++c) {
                    int s = a + b + c;
                    int x = cnt[a], y = cnt[b], z = cnt[c];
                    int t = 0;
                    t += s % a == 0 ? 1 : 0;
                    t += s % b == 0 ? 1 : 0;
                    t += s % c == 0 ? 1 : 0;
                    if (t == 1) {
                        if (a == b) {
                            ans += 1L * x * (x - 1) * z;
                        } else if (a == c) {
                            ans += 1L * x * (x - 1) * y;
                        } else if (b == c) {
                            ans += 1L * x * y * (y - 1);
                        } else {
                            ans += 1L * x * y * z;
                        }
                    }
                }
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
    long long singleDivisorTriplet(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        long long ans = 0;
        for (int a = 1; a <= 100; ++a) {
            for (int b = 1; b <= 100; ++b) {
                for (int c = 1; c <= 100; ++c) {
                    int s = a + b + c;
                    int x = cnt[a], y = cnt[b], z = cnt[c];
                    int t = (s % a == 0) + (s % b == 0) + (s % c == 0);
                    if (t == 1) {
                        if (a == b) {
                            ans += 1LL * x * (x - 1) * z;
                        } else if (a == c) {
                            ans += 1LL * x * (x - 1) * y;
                        } else if (b == c) {
                            ans += 1LL * x * y * (y - 1);
                        } else {
                            ans += 1LL * x * y * z;
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func singleDivisorTriplet(nums []int) (ans int64) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	f := func(a, b int) int {
		if a%b == 0 {
			return 1
		}
		return 0
	}
	for a := 1; a <= 100; a++ {
		for b := 1; b <= 100; b++ {
			for c := 1; c <= 100; c++ {
				s := a + b + c
				t := f(s, a) + f(s, b) + f(s, c)
				if t == 1 {
					if a == b {
						ans += int64(cnt[a] * (cnt[a] - 1) * cnt[c])
					} else if a == c {
						ans += int64(cnt[a] * (cnt[a] - 1) * cnt[b])
					} else if b == c {
						ans += int64(cnt[b] * (cnt[b] - 1) * cnt[a])
					} else {
						ans += int64(cnt[a] * cnt[b] * cnt[c])
					}
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function singleDivisorTriplet(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let ans = 0;
    const f = (a: number, b: number) => (a % b === 0 ? 1 : 0);
    for (let a = 1; a <= 100; ++a) {
        for (let b = 1; b <= 100; ++b) {
            for (let c = 1; c <= 100; ++c) {
                const s = a + b + c;
                const t = f(s, a) + f(s, b) + f(s, c);
                if (t === 1) {
                    if (a === b) {
                        ans += cnt[a] * (cnt[a] - 1) * cnt[c];
                    } else if (a === c) {
                        ans += cnt[a] * (cnt[a] - 1) * cnt[b];
                    } else if (b === c) {
                        ans += cnt[b] * (cnt[b] - 1) * cnt[a];
                    } else {
                        ans += cnt[a] * cnt[b] * cnt[c];
                    }
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
