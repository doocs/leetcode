---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README_EN.md
rating: 1876
source: Weekly Contest 238 Q2
tags:
    - Greedy
    - Array
    - Binary Search
    - Prefix Sum
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [1838. Frequency of the Most Frequent Element](https://leetcode.com/problems/frequency-of-the-most-frequent-element)

[中文文档](/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README.md)

## Description

<p>The <strong>frequency</strong> of an element is the number of times it occurs in an array.</p>

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. In one operation, you can choose an index of <code>nums</code> and increment the element at that index by <code>1</code>.</p>

<p>Return <em>the <strong>maximum possible frequency</strong> of an element after performing <strong>at most</strong> </em><code>k</code><em> operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4], k = 5
<strong>Output:</strong> 3<strong>
Explanation:</strong> Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,8,13], k = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,9,6], k = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Prefix Sum + Binary Search

According to the problem description, we can draw three conclusions:

1. After several operations, the element with the highest frequency in the array must be an element in the original array. Why? Suppose the elements operated are $a_1, a_2, \cdots, a_m$, where the maximum is $a_m$. These elements have all been changed to the same value $x$, where $x \geq a_m$. Then we can also change these elements all to $a_m$, and the number of operations will not increase.
2. The elements operated must be a continuous subarray in the sorted array.
3. If a frequency $m$ satisfies the condition, then all $m' < m$ also satisfy the condition. This inspires us to consider using binary search to find the maximum frequency that satisfies the condition.

Therefore, we can sort the array $nums$ and then calculate the prefix sum array $s$ of the sorted array, where $s[i]$ represents the sum of the first $i$ elements.

Next, we define the left boundary of the binary search as $l = 1$, and the right boundary as $r = n$. For each binary search, we take the middle value $m = (l + r + 1) / 2$, and then check whether there exists a continuous subarray of length $m$ such that all elements in the subarray can be changed to an element in the array, and the number of operations does not exceed $k$. If such a subarray exists, we can update the left boundary $l$ to $m$, otherwise update the right boundary $r$ to $m - 1$.

Finally, return the left boundary $l$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        def check(m: int) -> bool:
            for i in range(m, n + 1):
                if nums[i - 1] * m - (s[i] - s[i - m]) <= k:
                    return True
            return False

        n = len(nums)
        nums.sort()
        s = list(accumulate(nums, initial=0))
        l, r = 1, n
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

```java
class Solution {
    private int[] nums;
    private long[] s;
    private int k;

    public int maxFrequency(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        Arrays.sort(nums);
        int n = nums.length;
        s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 1, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int m) {
        for (int i = m; i <= nums.length; ++i) {
            if (1L * nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        int n = nums.size();
        sort(nums.begin(), nums.end());
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 1, r = n;
        auto check = [&](int m) {
            for (int i = m; i <= n; ++i) {
                if (1LL * nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
                    return true;
                }
            }
            return false;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

```go
func maxFrequency(nums []int, k int) int {
	n := len(nums)
	sort.Ints(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	check := func(m int) bool {
		for i := m; i <= n; i++ {
			if nums[i-1]*m-(s[i]-s[i-m]) <= k {
				return true
			}
		}
		return false
	}
	l, r := 1, n
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

```ts
function maxFrequency(nums: number[], k: number): number {
    const n = nums.length;
    nums.sort((a, b) => a - b);
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        s[i] = s[i - 1] + nums[i - 1];
    }
    let [l, r] = [1, n];
    const check = (m: number): boolean => {
        for (let i = m; i <= n; ++i) {
            if (nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
                return true;
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorting + Two Pointers

We can also use two pointers to maintain a sliding window, where all elements in the window can be changed to the maximum value in the window. The number of operations for the elements in the window is $s$, and $s \leq k$.

Initially, we set the left pointer $j$ to point to the first element of the array, and the right pointer $i$ also points to the first element of the array. Next, we move the right pointer $i$ each time, changing all elements in the window to $nums[i]$. At this time, the number of operations to be increased is $(nums[i] - nums[i - 1]) \times (i - j)$. If this number of operations exceeds $k$, then we need to move the left pointer $j$ until the number of operations for the elements in the window does not exceed $k$. Then, we update the answer to the maximum length of the window.

The time complexity is $O(n \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 1
        s = j = 0
        for i in range(1, len(nums)):
            s += (nums[i] - nums[i - 1]) * (i - j)
            while s > k:
                s -= nums[i] - nums[j]
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        long s = 0;
        for (int i = 1, j = 0; i < nums.length; ++i) {
            s += 1L * (nums[i] - nums[i - 1]) * (i - j);
            while (s > k) {
                s -= nums[i] - nums[j++];
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = 1;
        long long s = 0;
        for (int i = 1, j = 0; i < nums.size(); ++i) {
            s += 1LL * (nums[i] - nums[i - 1]) * (i - j);
            while (s > k) {
                s -= nums[i] - nums[j++];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans := 1
	s := 0
	for i, j := 1, 0; i < len(nums); i++ {
		s += (nums[i] - nums[i-1]) * (i - j)
		for ; s > k; j++ {
			s -= nums[i] - nums[j]
		}
		ans = max(ans, i-j+1)
	}
	return ans
}
```

```ts
function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let [s, j] = [0, 0];
    for (let i = 1; i < nums.length; ++i) {
        s += (nums[i] - nums[i - 1]) * (i - j);
        while (s > k) {
            s -= nums[i] - nums[j++];
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
