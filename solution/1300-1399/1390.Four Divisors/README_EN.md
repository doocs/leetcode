---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1390.Four%20Divisors/README_EN.md
rating: 1478
source: Weekly Contest 181 Q2
tags:
    - Array
    - Math
---

# [1390. Four Divisors](https://leetcode.com/problems/four-divisors)

[中文文档](/solution/1300-1399/1390.Four%20Divisors/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the sum of divisors of the integers in that array that have exactly four divisors</em>. If there is no such integer in the array, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [21,4,7]
<strong>Output:</strong> 32
<strong>Explanation:</strong> 
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [21,21]
<strong>Output:</strong> 64
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Factor Decomposition

We can perform factor decomposition on each number. If the number of factors is $4$, then this number meets the requirements of the problem, and we can add its factors to the answer.

The time complexity is $O(n \times \sqrt{n})$, where $n$ is the length of the array. The space complexity is $O(1)$.

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

<!-- end -->
