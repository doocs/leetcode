# [1785. Minimum Elements to Add to Form a Given Sum](https://leetcode.com/problems/minimum-elements-to-add-to-form-a-given-sum)

[中文文档](/solution/1700-1799/1785.Minimum%20Elements%20to%20Add%20to%20Form%20a%20Given%20Sum/README.md)

## Description

<p>You are given an integer array <code>nums</code> and two integers <code>limit</code> and <code>goal</code>. The array <code>nums</code> has an interesting property that <code>abs(nums[i]) &lt;= limit</code>.</p>

<p>Return <em>the minimum number of elements you need to add to make the sum of the array equal to </em><code>goal</code>. The array must maintain its property that <code>abs(nums[i]) &lt;= limit</code>.</p>

<p>Note that <code>abs(x)</code> equals <code>x</code> if <code>x &gt;= 0</code>, and <code>-x</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1,1], limit = 3, goal = -4
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can add -2 and -3, then the sum of the array will be 1 - 1 + 1 - 2 - 3 = -4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-10,9,1], limit = 100, goal = 0
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 10<sup>6</sup></code></li>
	<li><code>-limit &lt;= nums[i] &lt;= limit</code></li>
	<li><code>-10<sup>9</sup> &lt;= goal &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minElements(self, nums: List[int], limit: int, goal: int) -> int:
        d = abs(sum(nums) - goal)
        return (d + limit - 1) // limit
```

### **Java**

```java
class Solution {
    public int minElements(int[] nums, int limit, int goal) {
        // long s = Arrays.stream(nums).asLongStream().sum();
        long s = 0;
        for (int v : nums) {
            s += v;
        }
        long d = Math.abs(s - goal);
        return (int) ((d + limit - 1) / limit);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minElements(vector<int>& nums, int limit, int goal) {
        long long s = accumulate(nums.begin(), nums.end(), 0ll);
        long long d = abs(s - goal);
        return (d + limit - 1) / limit;
    }
};
```

### **Go**

```go
func minElements(nums []int, limit int, goal int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	d := abs(s - goal)
	return (d + limit - 1) / limit
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function minElements(nums: number[], limit: number, goal: number): number {
    const sum = nums.reduce((r, v) => r + v, 0);
    const diff = Math.abs(goal - sum);
    return Math.floor((diff + limit - 1) / limit);
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_elements(nums: Vec<i32>, limit: i32, goal: i32) -> i32 {
        let limit = limit as i64;
        let goal = goal as i64;
        let mut sum = 0;
        for &num in nums.iter() {
            sum += num as i64;
        }
        let diff = (goal - sum).abs();
        ((diff + limit - 1) / limit) as i32
    }
}
```

### **C**

```c
int minElements(int *nums, int numsSize, int limit, int goal) {
    long long sum = 0;
    for (int i = 0; i < numsSize; i++) {
        sum += nums[i];
    }
    long long diff = labs(goal - sum);
    return (diff + limit - 1) / limit;
}
```

### **...**

```

```

<!-- tabs:end -->
