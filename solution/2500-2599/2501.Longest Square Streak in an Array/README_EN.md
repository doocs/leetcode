---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2501.Longest%20Square%20Streak%20in%20an%20Array/README_EN.md
rating: 1479
source: Weekly Contest 323 Q2
tags:
    - Array
    - Hash Table
    - Binary Search
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [2501. Longest Square Streak in an Array](https://leetcode.com/problems/longest-square-streak-in-an-array)

[中文文档](/solution/2500-2599/2501.Longest%20Square%20Streak%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. A subsequence of <code>nums</code> is called a <strong>square streak</strong> if:</p>

<ul>
	<li>The length of the subsequence is at least <code>2</code>, and</li>
	<li><strong>after</strong> sorting the subsequence, each element (except the first element) is the <strong>square</strong> of the previous number.</li>
</ul>

<p>Return<em> the length of the <strong>longest square streak</strong> in </em><code>nums</code><em>, or return </em><code>-1</code><em> if there is no <strong>square streak</strong>.</em></p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,6,16,8,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
- 4 = 2 * 2.
- 16 = 4 * 4.
Therefore, [4,16,2] is a square streak.
It can be shown that every subsequence of length 4 is not a square streak.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,5,6,7]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no square streak in nums so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

We first use a hash table to record all elements in the array. Then, we enumerate each element in the array as the first element of the subsequence, square this element continuously, and check whether the squared result is in the hash table. If it is, we use the squared result as the next element and continue checking until the squared result is not in the hash table. At this point, we check whether the length of the subsequence is greater than $1$. If it is, we update the answer.

The time complexity is $O(n \times \log \log M)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$, and $M$ is the maximum value of the elements in the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSquareStreak(self, nums: List[int]) -> int:
        s = set(nums)
        ans = -1
        for x in nums:
            t = 0
            while x in s:
                x *= x
                t += 1
            if t > 1:
                ans = max(ans, t)
        return ans
```

#### Java

```java
class Solution {
    public int longestSquareStreak(int[] nums) {
        Set<Long> s = new HashSet<>();
        for (long x : nums) {
            s.add(x);
        }
        int ans = -1;
        for (long x : s) {
            int t = 0;
            for (; s.contains(x); x *= x) {
                ++t;
            }
            if (t > 1) {
                ans = Math.max(ans, t);
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
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<long long> s(nums.begin(), nums.end());
        int ans = -1;
        for (long long x : nums) {
            int t = 0;
            for (; s.contains(x); x *= x) {
                ++t;
            }
            if (t > 1) {
                ans = max(ans, t);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestSquareStreak(nums []int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	ans := -1
	for x := range s {
		t := 0
		for s[x] {
			x *= x
			t++
		}
		if t > 1 {
			ans = max(ans, t)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestSquareStreak(nums: number[]): number {
    const s = new Set(nums);
    let ans = -1;

    for (const num of nums) {
        let x = num;
        let t = 0;

        while (s.has(x)) {
            x *= x;
            t += 1;
        }

        if (t > 1) {
            ans = Math.max(ans, t);
        }
    }

    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestSquareStreak = function (nums) {
    const s = new Set(nums);
    let ans = -1;

    for (const num of nums) {
        let x = num;
        let t = 0;

        while (s.has(x)) {
            x *= x;
            t += 1;
        }

        if (t > 1) {
            ans = Math.max(ans, t);
        }
    }

    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Memoization Search

Similar to Solution 1, we first use a hash table to record all elements in the array. Then, we design a function $\textit{dfs}(x)$, which represents the length of the square wave starting with $x$. The answer is $\max(\textit{dfs}(x))$, where $x$ is an element in the array $\textit{nums}$.

The calculation process of the function $\textit{dfs}(x)$ is as follows:

- If $x$ is not in the hash table, return $0$.
- Otherwise, return $1 + \textit{dfs}(x^2)$.

During the process, we can use memoization, i.e., use a hash table to record the value of the function $\textit{dfs}(x)$ to avoid redundant calculations.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSquareStreak(self, nums: List[int]) -> int:
        @cache
        def dfs(x: int) -> int:
            if x not in s:
                return 0
            return 1 + dfs(x * x)

        s = set(nums)
        ans = max(dfs(x) for x in s)
        return -1 if ans < 2 else ans
```

#### Java

```java
class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private Set<Long> s = new HashSet<>();

    public int longestSquareStreak(int[] nums) {
        for (long x : nums) {
            s.add(x);
        }
        int ans = 0;
        for (long x : s) {
            ans = Math.max(ans, dfs(x));
        }
        return ans < 2 ? -1 : ans;
    }

    private int dfs(long x) {
        if (!s.contains(x)) {
            return 0;
        }
        if (f.containsKey(x)) {
            return f.get(x);
        }
        int ans = 1 + dfs(x * x);
        f.put(x, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<long long> s(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<long long, int> f;
        auto dfs = [&](this auto&& dfs, long long x) -> int {
            if (!s.contains(x)) {
                return 0;
            }
            if (f.contains(x)) {
                return f[x];
            }
            f[x] = 1 + dfs(x * x);
            return f[x];
        };
        for (long long x : s) {
            ans = max(ans, dfs(x));
        }
        return ans < 2 ? -1 : ans;
    }
};
```

#### Go

```go
func longestSquareStreak(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	f := map[int]int{}
	var dfs func(int) int
	dfs = func(x int) int {
		if !s[x] {
			return 0
		}
		if v, ok := f[x]; ok {
			return v
		}
		f[x] = 1 + dfs(x*x)
		return f[x]
	}
	for x := range s {
		if t := dfs(x); ans < t {
			ans = t
		}
	}
	if ans < 2 {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function longestSquareStreak(nums: number[]): number {
    const s = new Set(nums);
    const f = new Map<number, number>();
    const dfs = (x: number): number => {
        if (f.has(x)) {
            return f.get(x)!;
        }
        if (!s.has(x)) {
            return 0;
        }
        f.set(x, 1 + dfs(x ** 2));
        return f.get(x)!;
    };

    for (const x of s) {
        dfs(x);
    }
    const ans = Math.max(...f.values());
    return ans > 1 ? ans : -1;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestSquareStreak = function (nums) {
    const s = new Set(nums);
    const f = new Map();
    const dfs = x => {
        if (f.has(x)) {
            return f.get(x);
        }
        if (!s.has(x)) {
            return 0;
        }
        f.set(x, 1 + dfs(x ** 2));
        return f.get(x);
    };

    for (const x of s) {
        dfs(x);
    }
    const ans = Math.max(...f.values());
    return ans > 1 ? ans : -1;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
