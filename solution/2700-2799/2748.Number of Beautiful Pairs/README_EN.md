---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2748.Number%20of%20Beautiful%20Pairs/README_EN.md
rating: 1301
source: Weekly Contest 351 Q1
tags:
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [2748. Number of Beautiful Pairs](https://leetcode.com/problems/number-of-beautiful-pairs)

[中文文档](/solution/2700-2799/2748.Number%20of%20Beautiful%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed </strong>integer array <code>nums</code>. A pair of indices <code>i</code>, <code>j</code> where <code>0 &lt;=&nbsp;i &lt; j &lt; nums.length</code> is called beautiful if the <strong>first digit</strong> of <code>nums[i]</code> and the <strong>last digit</strong> of <code>nums[j]</code> are <strong>coprime</strong>.</p>

<p>Return <em>the total number of beautiful pairs in </em><code>nums</code>.</p>

<p>Two integers <code>x</code> and <code>y</code> are <strong>coprime</strong> if there is no integer greater than 1 that divides both of them. In other words, <code>x</code> and <code>y</code> are coprime if <code>gcd(x, y) == 1</code>, where <code>gcd(x, y)</code> is the <strong>greatest common divisor</strong> of <code>x</code> and <code>y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,5,1,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 beautiful pairs in nums:
When i = 0 and j = 1: the first digit of nums[0] is 2, and the last digit of nums[1] is 5. We can confirm that 2 and 5 are coprime, since gcd(2,5) == 1.
When i = 0 and j = 2: the first digit of nums[0] is 2, and the last digit of nums[2] is 1. Indeed, gcd(2,1) == 1.
When i = 1 and j = 2: the first digit of nums[1] is 5, and the last digit of nums[2] is 1. Indeed, gcd(5,1) == 1.
When i = 1 and j = 3: the first digit of nums[1] is 5, and the last digit of nums[3] is 4. Indeed, gcd(5,4) == 1.
When i = 2 and j = 3: the first digit of nums[2] is 1, and the last digit of nums[3] is 4. Indeed, gcd(1,4) == 1.
Thus, we return 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [11,21,12]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 beautiful pairs:
When i = 0 and j = 1: the first digit of nums[0] is 1, and the last digit of nums[1] is 1. Indeed, gcd(1,1) == 1.
When i = 0 and j = 2: the first digit of nums[0] is 1, and the last digit of nums[2] is 2. Indeed, gcd(1,2) == 1.
Thus, we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 9999</code></li>
	<li><code>nums[i] % 10 != 0</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

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
