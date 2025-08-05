---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3591.Check%20if%20Any%20Element%20Has%20Prime%20Frequency/README_EN.md
rating: 1234
source: Weekly Contest 455 Q1
tags:
    - Array
    - Hash Table
    - Math
    - Counting
    - Number Theory
---

<!-- problem:start -->

# [3591. Check if Any Element Has Prime Frequency](https://leetcode.com/problems/check-if-any-element-has-prime-frequency)

[中文文档](/solution/3500-3599/3591.Check%20if%20Any%20Element%20Has%20Prime%20Frequency/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Return <code>true</code> if the frequency of any element of the array is <strong>prime</strong>, otherwise, return <code>false</code>.</p>

<p>The <strong>frequency</strong> of an element <code>x</code> is the number of times it occurs in the array.</p>

<p>A prime number is a natural number greater than 1 with only two factors, 1 and itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>4 has a frequency of two, which is a prime number.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>All elements have a frequency of one.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,2,4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Both 2 and 4 have a prime frequency.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Prime Check

We use a hash table $\text{cnt}$ to count the frequency of each element. Then, we iterate through the values in $\text{cnt}$ and check if any of them is a prime number. If there is a prime, return `true`; otherwise, return `false`.

The time complexity is $O(n \times \sqrt{M})$, and the space complexity is $O(n)$, where $n$ is the length of the array $\text{nums}$ and $M$ is the maximum

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
