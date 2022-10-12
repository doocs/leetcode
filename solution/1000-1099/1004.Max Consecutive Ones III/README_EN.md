# [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii)

[中文文档](/solution/1000-1099/1004.Max%20Consecutive%20Ones%20III/README.md)

## Description

<p>Given a binary array <code>nums</code> and an integer <code>k</code>, return <em>the maximum number of consecutive </em><code>1</code><em>&#39;s in the array if you can flip at most</em> <code>k</code> <code>0</code>&#39;s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> [1,1,1,0,0,<u><strong>1</strong>,1,1,1,1,<strong>1</strong></u>]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
<strong>Output:</strong> 10
<strong>Explanation:</strong> [0,0,<u>1,1,<strong>1</strong>,<strong>1</strong>,1,1,1,<strong>1</strong>,1,1</u>,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        ans = 0
        cnt = j = 0
        for i, v in enumerate(nums):
            if v == 0:
                cnt += 1
            while cnt > k:
                if nums[j] == 0:
                    cnt -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```python
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        l = r = -1
        while r < len(nums) - 1:
            r += 1
            if nums[r] == 0:
                k -= 1
            if k < 0:
                l += 1
                if nums[l] == 0:
                    k += 1
        return r - l
```

### **Java**

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int j = 0, cnt = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > k) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r++] == 0) {
                --k;
            }
            if (k < 0 && nums[l++] == 0) {
                ++k;
            }
        }
        return r - l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int ans = 0;
        int cnt = 0, j = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > k) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int l = 0, r = 0;
        while (r < nums.size()) {
            if (nums[r++] == 0) --k;
            if (k < 0 && nums[l++] == 0) ++k;
        }
        return r - l;
    }
};
```

### **Go**

```go
func longestOnes(nums []int, k int) int {
	ans := 0
	j, cnt := 0, 0
	for i, v := range nums {
		if v == 0 {
			cnt++
		}
		for cnt > k {
			if nums[j] == 0 {
				cnt--
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func longestOnes(nums []int, k int) int {
	l, r := -1, -1
	for r < len(nums)-1 {
		r++
		if nums[r] == 0 {
			k--
		}
		if k < 0 {
			l++
			if nums[l] == 0 {
				k++
			}
		}
	}
	return r - l
}
```

### **TypeScript**

```ts
function longestOnes(nums: number[], k: number): number {
    const n = nums.length;
    let l = 0;
    for (const num of nums) {
        if (num === 0) {
            k--;
        }
        if (k < 0 && nums[l++] === 0) {
            k++;
        }
    }
    return n - l;
}
```

```ts
function longestOnes(nums: number[], k: number): number {
    const n = nums.length;
    let l = 0;
    let res = k;
    const count = [0, 0];
    for (let r = 0; r < n; r++) {
        count[nums[r]]++;
        res = Math.max(res, r - l);
        while (count[0] > k) {
            count[nums[l]]--;
            l++;
        }
    }
    return Math.max(res, n - l);
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_ones(nums: Vec<i32>, mut k: i32) -> i32 {
        let n = nums.len();
        let mut l = 0;
        for num in nums.iter() {
            if num == &0 {
                k -= 1;
            }
            if k < 0 {
                if nums[l] == 0 {
                    k += 1;
                }
                l += 1;
            }
        }
        (n - l) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
