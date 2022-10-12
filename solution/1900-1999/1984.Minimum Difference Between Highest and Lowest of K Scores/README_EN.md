# [1984. Minimum Difference Between Highest and Lowest of K Scores](https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores)

[中文文档](/solution/1900-1999/1984.Minimum%20Difference%20Between%20Highest%20and%20Lowest%20of%20K%20Scores/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>, where <code>nums[i]</code> represents the score of the <code>i<sup>th</sup></code> student. You are also given an integer <code>k</code>.</p>

<p>Pick the scores of any <code>k</code> students from the array so that the <strong>difference</strong> between the <strong>highest</strong> and the <strong>lowest</strong> of the <code>k</code> scores is <strong>minimized</strong>.</p>

<p>Return <em>the <strong>minimum</strong> possible difference</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [90], k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is one way to pick score(s) of one student:
- [<strong><u>90</u></strong>]. The difference between the highest and lowest score is 90 - 90 = 0.
The minimum possible difference is 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,4,1,7], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are six ways to pick score(s) of two students:
- [<strong><u>9</u></strong>,<strong><u>4</u></strong>,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
- [<strong><u>9</u></strong>,4,<strong><u>1</u></strong>,7]. The difference between the highest and lowest score is 9 - 1 = 8.
- [<strong><u>9</u></strong>,4,1,<strong><u>7</u></strong>]. The difference between the highest and lowest score is 9 - 7 = 2.
- [9,<strong><u>4</u></strong>,<strong><u>1</u></strong>,7]. The difference between the highest and lowest score is 4 - 1 = 3.
- [9,<strong><u>4</u></strong>,1,<strong><u>7</u></strong>]. The difference between the highest and lowest score is 7 - 4 = 3.
- [9,4,<strong><u>1</u></strong>,<strong><u>7</u></strong>]. The difference between the highest and lowest score is 7 - 1 = 6.
The minimum possible difference is 2.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        return min(nums[i + k - 1] - nums[i] for i in range(len(nums) - k + 1))
```

### **Java**

```java
class Solution {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 100000;
        for (int i = 0; i < nums.length - k + 1; ++i) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minimumDifference(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = nums[n - 1] - nums[0];
    for (let i = 0; i + k - 1 < n; i++) {
        ans = Math.min(nums[i + k - 1] - nums[i], ans);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = 1e5;
        for (int i = 0; i < nums.size() - k + 1; ++i)
            ans = min(ans, nums[i + k - 1] - nums[i]);
        return ans;
    }
};
```

### **Go**

```go
func minimumDifference(nums []int, k int) int {
	sort.Ints(nums)
	ans := 100000
	for i := 0; i < len(nums)-k+1; i++ {
		ans = min(ans, nums[i+k-1]-nums[i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimum_difference(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let k = k as usize;
        let mut res = i32::MAX;
        for i in 0..=nums.len() - k {
            res = res.min(nums[i + k - 1] - nums[i]);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
