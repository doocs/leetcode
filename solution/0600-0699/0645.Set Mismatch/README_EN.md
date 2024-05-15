---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0645.Set%20Mismatch/README_EN.md
tags:
    - Bit Manipulation
    - Array
    - Hash Table
    - Sorting
---

# [645. Set Mismatch](https://leetcode.com/problems/set-mismatch)

[中文文档](/solution/0600-0699/0645.Set%20Mismatch/README.md)

## Description

<p>You have a set of integers <code>s</code>, which originally contains all the numbers from <code>1</code> to <code>n</code>. Unfortunately, due to some error, one of the numbers in <code>s</code> got duplicated to another number in the set, which results in <strong>repetition of one</strong> number and <strong>loss of another</strong> number.</p>

<p>You are given an integer array <code>nums</code> representing the data status of this set after the error.</p>

<p>Find the number that occurs twice and the number that is missing and return <em>them in the form of an array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,2,4]
<strong>Output:</strong> [2,3]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,1]
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Mathematics

We denote $s_1$ as the sum of all numbers from $[1,..n]$, $s_2$ as the sum of the numbers in the array $nums$ after removing duplicates, and $s$ as the sum of the numbers in the array $nums$.

Then $s - s_2$ is the duplicate number, and $s_1 - s_2$ is the missing number.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $nums$. Extra space is needed to store the array after de-duplication.

<!-- tabs:start -->

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        n = len(nums)
        s1 = (1 + n) * n // 2
        s2 = sum(set(nums))
        s = sum(nums)
        return [s - s2, s1 - s2]
```

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int s1 = (1 + n) * n / 2;
        int s2 = 0;
        Set<Integer> set = new HashSet<>();
        int s = 0;
        for (int x : nums) {
            if (set.add(x)) {
                s2 += x;
            }
            s += x;
        }
        return new int[] {s - s2, s1 - s2};
    }
}
```

```cpp
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int n = nums.size();
        int s1 = (1 + n) * n / 2;
        int s2 = 0;
        unordered_set<int> set(nums.begin(), nums.end());
        for (int x : set) {
            s2 += x;
        }
        int s = accumulate(nums.begin(), nums.end(), 0);
        return {s - s2, s1 - s2};
    }
};
```

```go
func findErrorNums(nums []int) []int {
	n := len(nums)
	s1 := (1 + n) * n / 2
	s2, s := 0, 0
	set := map[int]bool{}
	for _, x := range nums {
		if !set[x] {
			set[x] = true
			s2 += x
		}
		s += x
	}
	return []int{s - s2, s1 - s2}
}
```

```ts
function findErrorNums(nums: number[]): number[] {
    const n = nums.length;
    const s1 = (n * (n + 1)) >> 1;
    const s2 = [...new Set(nums)].reduce((a, b) => a + b);
    const s = nums.reduce((a, b) => a + b);
    return [s - s2, s1 - s2];
}
```

```rust
use std::collections::HashSet;
impl Solution {
    pub fn find_error_nums(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() as i32;
        let s1 = ((1 + n) * n) / 2;
        let s2 = nums.iter().cloned().collect::<HashSet<i32>>().iter().sum::<i32>();
        let s: i32 = nums.iter().sum();
        vec![s - s2, s1 - s2]
    }
}
```

<!-- tabs:end -->

### Solution 2: Hash Table

We can also use a more intuitive method, using a hash table $cnt$ to count the occurrence of each number in the array $nums$.

Next, iterate through $x \in [1, n]$, if $cnt[x] = 2$, then $x$ is the duplicate number, if $cnt[x] = 0$, then $x$ is the missing number.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        n = len(nums)
        ans = [0] * 2
        for x in range(1, n + 1):
            if cnt[x] == 2:
                ans[0] = x
            if cnt[x] == 0:
                ans[1] = x
        return ans
```

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>(n);
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int[] ans = new int[2];
        for (int x = 1; x <= n; ++x) {
            int t = cnt.getOrDefault(x, 0);
            if (t == 2) {
                ans[0] = x;
            } else if (t == 0) {
                ans[1] = x;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        vector<int> ans(2);
        for (int x = 1; x <= n; ++x) {
            if (cnt[x] == 2) {
                ans[0] = x;
            } else if (cnt[x] == 0) {
                ans[1] = x;
            }
        }
        return ans;
    }
};
```

```go
func findErrorNums(nums []int) []int {
	n := len(nums)
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	ans := make([]int, 2)
	for x := 1; x <= n; x++ {
		if cnt[x] == 2 {
			ans[0] = x
		} else if cnt[x] == 0 {
			ans[1] = x
		}
	}
	return ans
}
```

```ts
function findErrorNums(nums: number[]): number[] {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const ans: number[] = new Array(2).fill(0);
    for (let x = 1; x <= n; ++x) {
        const t = cnt.get(x) || 0;
        if (t === 2) {
            ans[0] = x;
        } else if (t === 0) {
            ans[1] = x;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn find_error_nums(nums: Vec<i32>) -> Vec<i32> {
        let mut xs = 0;
        for (i, x) in nums.iter().enumerate() {
            xs ^= ((i + 1) as i32) ^ x;
        }
        let mut a = 0;
        let lb = xs & -xs;
        for (i, x) in nums.iter().enumerate() {
            if (((i + 1) as i32) & lb) != 0 {
                a ^= (i + 1) as i32;
            }
            if (*x & lb) != 0 {
                a ^= *x;
            }
        }
        let b = xs ^ a;
        for x in nums.iter() {
            if *x == a {
                return vec![a, b];
            }
        }
        vec![b, a]
    }
}
```

<!-- tabs:end -->

### Solution 3: Bit Operation

According to the properties of the XOR operation, for integer $x$, we have $x \oplus x = 0$ and $x \oplus 0 = x$. Therefore, if we perform the XOR operation on all elements in the array $nums$ and all numbers $i \in [1, n]$, we can eliminate the numbers that appear twice, leaving only the XOR result of the missing number and the duplicate number, i.e., $xs = a \oplus b$.

Since these two numbers are not equal, there must be at least one bit in the XOR result that is $1$. We find the lowest bit of $1$ in the XOR result through the $lowbit$ operation, and then divide all numbers in the array $nums$ and all numbers $i \in [1, n]$ into two groups according to whether this bit is $1$. In this way, the two numbers are divided into different groups. The XOR result of one group of numbers is $a$, and the XOR result of the other group is $b$. These two numbers are the answers we are looking for.

Next, we only need to determine which of $a$ and $b$ is the duplicate number and which is the missing number. Therefore, iterate through the array $nums$, for the traversed number $x$, if $x=a$, then $a$ is the duplicate number, return $[a, b]$, otherwise, at the end of the iteration, return $[b, a]$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$, only using a constant size of extra space.

<!-- tabs:start -->

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        xs = 0
        for i, x in enumerate(nums, 1):
            xs ^= i ^ x
        a = 0
        lb = xs & -xs
        for i, x in enumerate(nums, 1):
            if i & lb:
                a ^= i
            if x & lb:
                a ^= x
        b = xs ^ a
        for x in nums:
            if x == a:
                return [a, b]
        return [b, a]
```

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int xs = 0;
        for (int i = 1; i <= n; ++i) {
            xs ^= i ^ nums[i - 1];
        }
        int lb = xs & -xs;
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if ((i & lb) > 0) {
                a ^= i;
            }
            if ((nums[i - 1] & lb) > 0) {
                a ^= nums[i - 1];
            }
        }
        int b = xs ^ a;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == a) {
                return new int[] {a, b};
            }
        }
        return new int[] {b, a};
    }
}
```

```cpp
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int n = nums.size();
        int xs = 0;
        for (int i = 1; i <= n; ++i) {
            xs ^= i ^ nums[i - 1];
        }
        int lb = xs & -xs;
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if (i & lb) {
                a ^= i;
            }
            if (nums[i - 1] & lb) {
                a ^= nums[i - 1];
            }
        }
        int b = xs ^ a;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == a) {
                return {a, b};
            }
        }
        return {b, a};
    }
};
```

```go
func findErrorNums(nums []int) []int {
	xs := 0
	for i, x := range nums {
		xs ^= x ^ (i + 1)
	}
	lb := xs & -xs
	a := 0
	for i, x := range nums {
		if (i+1)&lb != 0 {
			a ^= (i + 1)
		}
		if x&lb != 0 {
			a ^= x
		}
	}
	b := xs ^ a
	for _, x := range nums {
		if x == a {
			return []int{a, b}
		}
	}
	return []int{b, a}
}
```

```ts
function findErrorNums(nums: number[]): number[] {
    const n = nums.length;
    let xs = 0;
    for (let i = 1; i <= n; ++i) {
        xs ^= i ^ nums[i - 1];
    }
    const lb = xs & -xs;
    let a = 0;
    for (let i = 1; i <= n; ++i) {
        if (i & lb) {
            a ^= i;
        }
        if (nums[i - 1] & lb) {
            a ^= nums[i - 1];
        }
    }
    const b = xs ^ a;
    return nums.includes(a) ? [a, b] : [b, a];
}
```

<!-- tabs:end -->

<!-- end -->
