# [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence)

[中文文档](/solution/0100-0199/0128.Longest%20Consecutive%20Sequence/README.md)

## Description

<p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest consecutive elements sequence.</em></p>

<p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,4,200,1,3,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest consecutive elements sequence is <code>[1, 2, 3, 4]</code>. Therefore its length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,3,7,2,5,8,4,6,0,1]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

**Solution 1: Sorting**

First, we sort the array, then use a variable $t$ to record the current length of the consecutive sequence, and a variable $ans$ to record the length of the longest consecutive sequence.

Next, we start traversing the array from index $i=1$. For the current element $nums[i]$:

-   If $nums[i] = nums[i-1]$, it means the current element is repeated and does not need to be considered.
-   If $nums[i] = nums[i-1] + 1$, it means the current element can be appended to the previous consecutive sequence to form a longer consecutive sequence. We update $t = t + 1$, and then update the answer $ans = \max(ans, t)$.
-   Otherwise, it means the current element cannot be appended to the previous consecutive sequence, and we reset $t$ to $1$.

Finally, we return the answer $ans$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array.

**Solution 2: Hash Table**

We use a hash table to store all elements in the array, and then traverse each element $x$ in the array. If the predecessor $x-1$ of the current element is not in the hash table, then we start with the current element and continuously try to match $x+1, x+2, x+3, \dots$, until no match is found. The length of the match at this time is the longest consecutive sequence length starting with $x$, and we update the answer accordingly.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return n
        nums.sort()
        ans = t = 1
        for a, b in pairwise(nums):
            if a == b:
                continue
            if a + 1 == b:
                t += 1
                ans = max(ans, t)
            else:
                t = 1
        return ans
```

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s = set(nums)
        ans = 0
        for x in nums:
            if x - 1 not in s:
                y = x + 1
                while y in s:
                    y += 1
                ans = max(ans, y - x)
        return ans
```

### **Java**

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        Arrays.sort(nums);
        int ans = 1, t = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] == nums[i - 1] + 1) {
                ans = Math.max(ans, ++t);
            } else {
                t = 1;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int ans = 0;
        for (int x : nums) {
            if (!s.contains(x - 1)) {
                int y = x + 1;
                while (s.contains(y)) {
                    ++y;
                }
                ans = Math.max(ans, y - x);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        int n = nums.size();
        if (n < 2) {
            return n;
        }
        sort(nums.begin(), nums.end());
        int ans = 1, t = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] == nums[i - 1] + 1) {
                ans = max(ans, ++t);
            } else {
                t = 1;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = 0;
        for (int x : nums) {
            if (!s.count(x - 1)) {
                int y = x + 1;
                while (s.count(y)) {
                    y++;
                }
                ans = max(ans, y - x);
            }
        }
        return ans;
    }
};
```

### **Rust**

```rust
use std::collections::HashSet;

impl Solution {
    #[allow(dead_code)]
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let mut s = HashSet::new();
        let mut ret = 0;

        // Initialize the set
        for num in &nums {
            s.insert(*num);
        }

        for num in &nums {
            if s.contains(&(*num - 1)) {
                continue;
            }
            let mut cur_num = num.clone();
            while s.contains(&cur_num) {
                cur_num += 1;
            }
            // Update the answer
            ret = std::cmp::max(ret, cur_num - num);
        }

        ret
    }
}
```

### **Go**

```go
func longestConsecutive(nums []int) int {
	n := len(nums)
	if n < 2 {
		return n
	}
	sort.Ints(nums)
	ans, t := 1, 1
	for i, x := range nums[1:] {
		if x == nums[i] {
			continue
		}
		if x == nums[i]+1 {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
	}
	return ans
}
```

```go
func longestConsecutive(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for _, x := range nums {
		if !s[x-1] {
			y := x + 1
			for s[y] {
				y++
			}
			ans = max(ans, y-x)
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const n = nums.length;
    if (n < 2) {
        return n;
    }
    nums.sort((a, b) => a - b);
    let ans = 1;
    let t = 1;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === nums[i - 1]) {
            continue;
        }
        if (nums[i] === nums[i - 1] + 1) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const s = new Set(nums);
    let ans = 0;
    for (const x of nums) {
        if (!s.has(x - 1)) {
            let y = x + 1;
            while (s.has(y)) {
                y++;
            }
            ans = Math.max(ans, y - x);
        }
    }
    return ans;
};
```

### **TypeScript**

```ts
function longestConsecutive(nums: number[]): number {
    const n = nums.length;
    if (n < 2) {
        return n;
    }
    let ans = 1;
    let t = 1;
    nums.sort((a, b) => a - b);
    for (let i = 1; i < n; ++i) {
        if (nums[i] === nums[i - 1]) {
            continue;
        }
        if (nums[i] === nums[i - 1] + 1) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
}
```

```ts
function longestConsecutive(nums: number[]): number {
    const s: Set<number> = new Set(nums);
    let ans = 0;
    for (const x of s) {
        if (!s.has(x - 1)) {
            let y = x + 1;
            while (s.has(y)) {
                y++;
            }
            ans = Math.max(ans, y - x);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
