---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1390.Four%20Divisors/README.md
rating: 1478
source: 第 181 场周赛 Q2
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [1390. 四因数](https://leetcode.cn/problems/four-divisors)

[English Version](/solution/1300-1399/1390.Four%20Divisors/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，请你返回该数组中恰有四个因数的这些整数的各因数之和。如果数组中不存在满足题意的整数，则返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [21,4,7]
<strong>输出：</strong>32
<strong>解释：</strong>
21 有 4 个因数：1, 3, 7, 21
4 有 3 个因数：1, 2, 4
7 有 2 个因数：1, 7
答案仅为 21 的所有因数的和。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [21,21]
<strong>输出:</strong> 64
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5]
<strong>输出:</strong> 0</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：因数分解

我们可以对每个数进行因数分解，如果因数的个数为 $4$ 个，那么这个数就是符合题意的数，我们将其因数累加到答案中即可。

时间复杂度 $O(n \times \sqrt{n})$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sumFourDivisors(self, nums: List[int]) -> int:
        def f(x: int) -> int:
            i = 2
            cnt, s = 2, x + 1
            while i <= x // i:
                if x % i == 0:
                    cnt += 1
                    s += i
                    if i * i != x:
                        cnt += 1
                        s += x // i
                i += 1
            return s if cnt == 4 else 0

        return sum(f(x) for x in nums)
```

```java
class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans += f(x);
        }
        return ans;
    }

    private int f(int x) {
        int cnt = 2, s = x + 1;
        for (int i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                ++cnt;
                s += i;
                if (i * i != x) {
                    ++cnt;
                    s += x / i;
                }
            }
        }
        return cnt == 4 ? s : 0;
    }
}
```

```cpp
class Solution {
public:
    int sumFourDivisors(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            ans += f(x);
        }
        return ans;
    }

    int f(int x) {
        int cnt = 2, s = x + 1;
        for (int i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                ++cnt;
                s += i;
                if (i * i != x) {
                    ++cnt;
                    s += x / i;
                }
            }
        }
        return cnt == 4 ? s : 0;
    }
};
```

```go
func sumFourDivisors(nums []int) (ans int) {
	f := func(x int) int {
		cnt, s := 2, x+1
		for i := 2; i <= x/i; i++ {
			if x%i == 0 {
				cnt++
				s += i
				if i*i != x {
					cnt++
					s += x / i
				}
			}
		}
		if cnt == 4 {
			return s
		}
		return 0
	}
	for _, x := range nums {
		ans += f(x)
	}
	return
}
```

```ts
function sumFourDivisors(nums: number[]): number {
    const f = (x: number): number => {
        let cnt = 2;
        let s = x + 1;
        for (let i = 2; i * i <= x; ++i) {
            if (x % i === 0) {
                ++cnt;
                s += i;
                if (i * i !== x) {
                    ++cnt;
                    s += Math.floor(x / i);
                }
            }
        }
        return cnt === 4 ? s : 0;
    };
    let ans = 0;
    for (const x of nums) {
        ans += f(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
