---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0961.N-Repeated%20Element%20in%20Size%202N%20Array/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [961. N-Repeated Element in Size 2N Array](https://leetcode.com/problems/n-repeated-element-in-size-2n-array)

[中文文档](/solution/0900-0999/0961.N-Repeated%20Element%20in%20Size%202N%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> with the following properties:</p>

<ul>
	<li><code>nums.length == 2 * n</code>.</li>
	<li><code>nums</code> contains <code>n + 1</code> <strong>unique</strong> elements.</li>
	<li>Exactly one element of <code>nums</code> is repeated <code>n</code> times.</li>
</ul>

<p>Return <em>the element that is repeated </em><code>n</code><em> times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3,3]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,1,2,5,3,2]
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [5,1,5,2,5,3,5,4]
<strong>Output:</strong> 5
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5000</code></li>
	<li><code>nums.length == 2 * n</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> contains <code>n + 1</code> <strong>unique</strong> elements and one of them is repeated exactly <code>n</code> times.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

Since the array $\textit{nums}$ has a total of $2n$ elements, with $n + 1$ distinct elements, and one element repeated $n$ times, this means the remaining $n$ elements in the array are all distinct.

Therefore, we only need to iterate through the array $\textit{nums}$ and use a hash table $s$ to record the elements we've encountered. When we encounter an element $x$, if $x$ already exists in the hash table $s$, it means $x$ is the repeated element, and we can directly return $x$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def repeatedNTimes(self, nums: List[int]) -> int:
        s = set()
        for x in nums:
            if x in s:
                return x
            s.add(x)
```

#### Java

```java
class Solution {
    public int repeatedNTimes(int[] nums) {
        Set<Integer> s = new HashSet<>(nums.length / 2 + 1);
        for (int i = 0;; ++i) {
            if (!s.add(nums[i])) {
                return nums[i];
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {
        unordered_set<int> s;
        for (int i = 0;; ++i) {
            if (s.count(nums[i])) {
                return nums[i];
            }
            s.insert(nums[i]);
        }
    }
};
```

#### Go

```go
func repeatedNTimes(nums []int) int {
	s := map[int]bool{}
	for i := 0; ; i++ {
		if s[nums[i]] {
			return nums[i]
		}
		s[nums[i]] = true
	}
}
```

#### TypeScript

```ts
function repeatedNTimes(nums: number[]): number {
    const s: Set<number> = new Set();
    for (const x of nums) {
        if (s.has(x)) {
            return x;
        }
        s.add(x);
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var repeatedNTimes = function (nums) {
    const s = new Set();
    for (const x of nums) {
        if (s.has(x)) {
            return x;
        }
        s.add(x);
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Mathematics

According to the problem description, half of the elements in the array $\textit{nums}$ are the same. If we view the array as a circular arrangement, then there is at most $1$ other element between two identical elements.

Therefore, we iterate through the array $\textit{nums}$ starting from index $2$. For each index $i$, we compare $\textit{nums}[i]$ with $\textit{nums}[i - 1]$ and $\textit{nums}[i - 2]$. If they are equal, we return that value.

If we don't find the repeated element in the above process, then the repeated element must be $\textit{nums}[0]$, and we can directly return $\textit{nums}[0]$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def repeatedNTimes(self, nums: List[int]) -> int:
        for i in range(2, len(nums)):
            if nums[i] == nums[i - 1] or nums[i] == nums[i - 2]:
                return nums[i]
        return nums[0]
```

#### Java

```java
class Solution {
    public int repeatedNTimes(int[] nums) {
        for (int i = 2; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2]) {
                return nums[i];
            }
        }
        return nums[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {
        for (int i = 2; i < nums.size(); ++i) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2]) {
                return nums[i];
            }
        }
        return nums[0];
    }
};
```

#### Go

```go
func repeatedNTimes(nums []int) int {
	for i := 2; i < len(nums); i++ {
		if nums[i] == nums[i-1] || nums[i] == nums[i-2] {
			return nums[i]
		}
	}
	return nums[0]
}
```

#### TypeScript

```ts
function repeatedNTimes(nums: number[]): number {
    for (let i = 2; i < nums.length; ++i) {
        if (nums[i] === nums[i - 1] || nums[i] === nums[i - 2]) {
            return nums[i];
        }
    }
    return nums[0];
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var repeatedNTimes = function (nums) {
    for (let i = 2; i < nums.length; ++i) {
        if (nums[i] === nums[i - 1] || nums[i] === nums[i - 2]) {
            return nums[i];
        }
    }
    return nums[0];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
