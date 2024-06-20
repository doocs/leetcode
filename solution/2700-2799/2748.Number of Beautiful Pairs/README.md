---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2748.Number%20of%20Beautiful%20Pairs/README.md
rating: 1301
source: 第 351 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 数学
    - 计数
    - 数论
---

<!-- problem:start -->

# [2748. 美丽下标对的数目](https://leetcode.cn/problems/number-of-beautiful-pairs)

[English Version](/solution/2700-2799/2748.Number%20of%20Beautiful%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。如果下标对 <code>i</code>、<code>j</code> 满足 <code>0 ≤ i &lt; j &lt; nums.length</code> ，如果&nbsp;<code>nums[i]</code> 的 <strong>第一个数字</strong> 和 <code>nums[j]</code> 的 <strong>最后一个数字</strong> <strong>互质</strong> ，则认为 <code>nums[i]</code> 和 <code>nums[j]</code> 是一组 <strong>美丽下标对</strong> 。</p>

<p>返回 <code>nums</code> 中 <strong>美丽下标对</strong> 的总数目。</p>

<p>对于两个整数 <code>x</code> 和 <code>y</code> ，如果不存在大于 1 的整数可以整除它们，则认为 <code>x</code> 和 <code>y</code> <strong>互质</strong> 。换而言之，如果 <code>gcd(x, y) == 1</code> ，则认为 <code>x</code> 和 <code>y</code> 互质，其中 <code>gcd(x, y)</code> 是 <code>x</code> 和 <code>y</code>&nbsp;的&nbsp;<strong>最大公因数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,5,1,4]
<strong>输出：</strong>5
<strong>解释：</strong>nums 中共有 5 组美丽下标对：
i = 0 和 j = 1 ：nums[0] 的第一个数字是 2 ，nums[1] 的最后一个数字是 5 。2 和 5 互质，因此 gcd(2,5) == 1 。
i = 0 和 j = 2 ：nums[0] 的第一个数字是 2 ，nums[2] 的最后一个数字是 1 。2 和 1 互质，因此 gcd(2,1) == 1 。
i = 1 和 j = 2 ：nums[1] 的第一个数字是 5 ，nums[2] 的最后一个数字是 1 。5 和 1 互质，因此 gcd(5,1) == 1 。
i = 1 和 j = 3 ：nums[1] 的第一个数字是 5 ，nums[3] 的最后一个数字是 4 。5 和 4 互质，因此 gcd(5,4) == 1 。
i = 2 和 j = 3 ：nums[2] 的第一个数字是 1 ，nums[3] 的最后一个数字是 4 。1 和 4 互质，因此 gcd(1,4) == 1 。
因此，返回 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [11,21,12]
<strong>输出：</strong>2
<strong>解释：</strong>共有 2 组美丽下标对：
i = 0 和 j = 1 ：nums[0] 的第一个数字是 1 ，nums[1] 的最后一个数字是 1 。gcd(1,1) == 1 。
i = 0 和 j = 2 ：nums[0] 的第一个数字是 1 ，nums[2] 的最后一个数字是 2 。gcd(1,2) == 1 。
因此，返回 2 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 9999</code></li>
	<li><code>nums[i] % 10 != 0</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用一个长度为 $10$ 的数组 $\text{cnt}$ 来记录每个数字的第一个数字出现的次数。

遍历数组 $\text{nums}$，对于每个数字 $x$，我们枚举 $0$ 到 $9$ 的每个数字 $y$，如果 $\text{cnt}[y]$ 不为 $0$ 且 $\text{gcd}(x b\mod 10, y) = 1$，则答案加上 $\text{cnt}[y]$。然后，我们将 $x$ 的第一个数字出现的次数加 $1$。

遍历结束后，返回答案即可。

时间复杂度 $O(n \times (k + \log M))$，空间复杂度 $O(k + \log M)$。其中 $n$ 为数组 $\text{nums}$ 的长度，而 $k$ 和 $M$ 分别表示数组 $\text{nums}$ 中的数字的种类以及最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBeautifulPairs(self, nums: List[int]) -> int:
        cnt = [0] * 10
        ans = 0
        for x in nums:
            for y in range(10):
                if cnt[y] and gcd(x % 10, y) == 1:
                    ans += cnt[y]
            cnt[int(str(x)[0])] += 1
        return ans
```

#### Java

```java
class Solution {
    public int countBeautifulPairs(int[] nums) {
        int[] cnt = new int[10];
        int ans = 0;
        for (int x : nums) {
            for (int y = 0; y < 10; ++y) {
                if (cnt[y] > 0 && gcd(x % 10, y) == 1) {
                    ans += cnt[y];
                }
            }
            while (x > 9) {
                x /= 10;
            }
            ++cnt[x];
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countBeautifulPairs(vector<int>& nums) {
        int cnt[10]{};
        int ans = 0;
        for (int x : nums) {
            for (int y = 0; y < 10; ++y) {
                if (cnt[y] && gcd(x % 10, y) == 1) {
                    ans += cnt[y];
                }
            }
            while (x > 9) {
                x /= 10;
            }
            ++cnt[x];
        }
        return ans;
    }
};
```

#### Go

```go
func countBeautifulPairs(nums []int) (ans int) {
	cnt := [10]int{}
	for _, x := range nums {
		for y := 0; y < 10; y++ {
			if cnt[y] > 0 && gcd(x%10, y) == 1 {
				ans += cnt[y]
			}
		}
		for x > 9 {
			x /= 10
		}
		cnt[x]++
	}
	return
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function countBeautifulPairs(nums: number[]): number {
    const cnt: number[] = Array(10).fill(0);
    let ans = 0;
    for (let x of nums) {
        for (let y = 0; y < 10; ++y) {
            if (cnt[y] > 0 && gcd(x % 10, y) === 1) {
                ans += cnt[y];
            }
        }
        while (x > 9) {
            x = Math.floor(x / 10);
        }
        ++cnt[x];
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
