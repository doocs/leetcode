# [3115. Maximum Prime Difference](https://leetcode.com/problems/maximum-prime-difference)

[中文文档](/solution/3100-3199/3115.Maximum%20Prime%20Difference/README.md)

<!-- tags: -->

## Description

<p>You are given an integer array <code>nums</code>.</p>

<p>Return an integer that is the <strong>maximum</strong> distance between the <strong>indices</strong> of two (not necessarily different) prime numbers in <code>nums</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,9,5,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong> <code>nums[1]</code>, <code>nums[3]</code>, and <code>nums[4]</code> are prime. So the answer is <code>|4 - 1| = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,8,2,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong> <code>nums[2]</code> is prime. Because there is just one prime number, the answer is <code>|2 - 2| = 0</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li>The input is generated such that the number of prime numbers in the <code>nums</code> is at least one.</li>
</ul>

## Solutions

### Solution 1: Traversal

According to the problem description, we need to find the index $i$ of the first prime number, then find the index $j$ of the last prime number, and return $j - i$ as the answer.

Therefore, we can traverse the array from left to right to find the index $i$ of the first prime number, then traverse the array from right to left to find the index $j$ of the last prime number. The answer is $j - i$.

The time complexity is $O(n \times \sqrt{M})$, where $n$ and $M$ are the length of the array $nums$ and the maximum value in the array, respectively. The space complexity is $O(1)$.

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
