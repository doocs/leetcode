# [2006. Count Number of Pairs With Absolute Difference K](https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k)

[中文文档](/solution/2000-2099/2006.Count%20Number%20of%20Pairs%20With%20Absolute%20Difference%20K/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of pairs</em> <code>(i, j)</code> <em>where</em> <code>i &lt; j</code> <em>such that</em> <code>|nums[i] - nums[j]| == k</code>.</p>

<p>The value of <code>|x|</code> is defined as:</p>

<ul>
	<li><code>x</code> if <code>x &gt;= 0</code>.</li>
	<li><code>-x</code> if <code>x &lt; 0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,1], k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> The pairs with an absolute difference of 1 are:
- [<strong><u>1</u></strong>,<strong><u>2</u></strong>,2,1]
- [<strong><u>1</u></strong>,2,<strong><u>2</u></strong>,1]
- [1,<strong><u>2</u></strong>,2,<strong><u>1</u></strong>]
- [1,2,<strong><u>2</u></strong>,<strong><u>1</u></strong>]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3], k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no pairs with an absolute difference of 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,5,4], k = 2
<strong>Output:</strong> 3
<b>Explanation:</b> The pairs with an absolute difference of 2 are:
- [<strong><u>3</u></strong>,2,<strong><u>1</u></strong>,5,4]
- [<strong><u>3</u></strong>,2,1,<strong><u>5</u></strong>,4]
- [3,<strong><u>2</u></strong>,1,5,<strong><u>4</u></strong>]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 99</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countKDifference(self, nums: List[int], k: int) -> int:
        n = len(nums)
        return sum(abs(nums[i] - nums[j]) == k for i in range(n) for j in range(i + 1, n))
```

```python
class Solution:
    def countKDifference(self, nums: List[int], k: int) -> int:
        ans = 0
        cnt = Counter()
        for num in nums:
            ans += cnt[num - k] + cnt[num + k]
            cnt[num] += 1
        return ans
```

### **Java**

```java
class Solution {
    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        for (int i = 0, n = nums.length; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        int[] cnt = new int[110];
        for (int num : nums) {
            if (num >= k) {
                ans += cnt[num - k];
            }
            if (num + k <= 100) {
                ans += cnt[num + k];
            }
            ++cnt[num];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countKDifference(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                ans += abs(nums[i] - nums[j]) == k;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countKDifference(vector<int>& nums, int k) {
        int ans = 0;
        int cnt[110]{};
        for (int num : nums) {
            if (num >= k) {
                ans += cnt[num - k];
            }
            if (num + k <= 100) {
                ans += cnt[num + k];
            }
            ++cnt[num];
        }
        return ans;
    }
};
```

### **Go**

```go
func countKDifference(nums []int, k int) int {
	n := len(nums)
	ans := 0
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if abs(nums[i]-nums[j]) == k {
				ans++
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}
```

```go
func countKDifference(nums []int, k int) (ans int) {
	cnt := [110]int{}
	for _, num := range nums {
		if num >= k {
			ans += cnt[num-k]
		}
		if num+k <= 100 {
			ans += cnt[num+k]
		}
		cnt[num]++
	}
	return
}
```

### **TypeScript**

```ts
function countKDifference(nums: number[], k: number): number {
    let ans = 0;
    let cnt = new Map();
    for (let num of nums) {
        ans += (cnt.get(num - k) || 0) + (cnt.get(num + k) || 0);
        cnt.set(num, (cnt.get(num) || 0) + 1);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_k_difference(nums: Vec<i32>, k: i32) -> i32 {
        let mut res = 0;
        let n = nums.len();
        for i in 0..n - 1 {
            for j in i..n {
                if (nums[i] - nums[j]).abs() == k {
                    res += 1;
                }
            }
        }
        res
    }
}
```

```rust
impl Solution {
    pub fn count_k_difference(nums: Vec<i32>, k: i32) -> i32 {
        let mut arr = [0; 101];
        let mut res = 0;
        for num in nums {
            if num - k >= 1 {
                res += arr[(num - k) as usize];
            }
            if num + k <= 100 {
                res += arr[(num + k) as usize]
            }
            arr[num as usize] += 1;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
