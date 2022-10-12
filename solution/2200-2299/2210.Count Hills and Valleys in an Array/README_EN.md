# [2210. Count Hills and Valleys in an Array](https://leetcode.com/problems/count-hills-and-valleys-in-an-array)

[中文文档](/solution/2200-2299/2210.Count%20Hills%20and%20Valleys%20in%20an%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. An index <code>i</code> is part of a <strong>hill</strong> in <code>nums</code> if the closest non-equal neighbors of <code>i</code> are smaller than <code>nums[i]</code>. Similarly, an index <code>i</code> is part of a <strong>valley</strong> in <code>nums</code> if the closest non-equal neighbors of <code>i</code> are larger than <code>nums[i]</code>. Adjacent indices <code>i</code> and <code>j</code> are part of the <strong>same</strong> hill or valley if <code>nums[i] == nums[j]</code>.</p>

<p>Note that for an index to be part of a hill or valley, it must have a non-equal neighbor on <strong>both</strong> the left and right of the index.</p>

<p>Return <i>the number of hills and valleys in </i><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,1,1,6,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
At index 0: There is no non-equal neighbor of 2 on the left, so index 0 is neither a hill nor a valley.
At index 1: The closest non-equal neighbors of 4 are 2 and 1. Since 4 &gt; 2 and 4 &gt; 1, index 1 is a hill. 
At index 2: The closest non-equal neighbors of 1 are 4 and 6. Since 1 &lt; 4 and 1 &lt; 6, index 2 is a valley.
At index 3: The closest non-equal neighbors of 1 are 4 and 6. Since 1 &lt; 4 and 1 &lt; 6, index 3 is a valley, but note that it is part of the same valley as index 2.
At index 4: The closest non-equal neighbors of 6 are 1 and 5. Since 6 &gt; 1 and 6 &gt; 5, index 4 is a hill.
At index 5: There is no non-equal neighbor of 5 on the right, so index 5 is neither a hill nor a valley. 
There are 3 hills and valleys so we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,6,5,5,4,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
At index 0: There is no non-equal neighbor of 6 on the left, so index 0 is neither a hill nor a valley.
At index 1: There is no non-equal neighbor of 6 on the left, so index 1 is neither a hill nor a valley.
At index 2: The closest non-equal neighbors of 5 are 6 and 4. Since 5 &lt; 6 and 5 &gt; 4, index 2 is neither a hill nor a valley.
At index 3: The closest non-equal neighbors of 5 are 6 and 4. Since 5 &lt; 6 and 5 &gt; 4, index 3 is neither a hill nor a valley.
At index 4: The closest non-equal neighbors of 4 are 5 and 1. Since 4 &lt; 5 and 4 &gt; 1, index 4 is neither a hill nor a valley.
At index 5: There is no non-equal neighbor of 1 on the right, so index 5 is neither a hill nor a valley.
There are 0 hills and valleys so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

First make the consecutive duplicate value to be unique with the side values.

```python
class Solution:
    def countHillValley(self, nums: List[int]) -> int:
        arr = [nums[0]]
        for v in nums[1:]:
            if v != arr[-1]:
                arr.append(v)
        return sum(
            (arr[i] < arr[i - 1]) == (arr[i] < arr[i + 1])
            for i in range(1, len(arr) - 1)
        )
```

```python
class Solution:
    def countHillValley(self, nums: List[int]) -> int:
        ans = j = 0
        for i in range(1, len(nums) - 1):
            if nums[i] == nums[i + 1]:
                continue
            if nums[i] > nums[j] and nums[i] > nums[i + 1]:
                ans += 1
            if nums[i] < nums[j] and nums[i] < nums[i + 1]:
                ans += 1
            j = i
        return ans
```

### **Java**

Use two pointers to solve the problem

```java
class Solution {
    public int countHillValley(int[] nums) {
        int ans = 0;
        for (int i = 1, j = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            if (nums[i] > nums[j] && nums[i] > nums[i + 1]) {
                ++ans;
            }
            if (nums[i] < nums[j] && nums[i] < nums[i + 1]) {
                ++ans;
            }
            j = i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countHillValley(vector<int>& nums) {
        int ans = 0;
        for (int i = 1, j = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == nums[i + 1]) continue;
            if (nums[i] > nums[j] && nums[i] > nums[i + 1]) ++ans;
            if (nums[i] < nums[j] && nums[i] < nums[i + 1]) ++ans;
            j = i;
        }
        return ans;
    }
};
```

### **Go**

```go
func countHillValley(nums []int) int {
	ans := 0
	for i, j := 1, 0; i < len(nums)-1; i++ {
		if nums[i] == nums[i+1] {
			continue
		}
		if nums[i] > nums[j] && nums[i] > nums[i+1] {
			ans++
		}
		if nums[i] < nums[j] && nums[i] < nums[i+1] {
			ans++
		}
		j = i
	}
	return ans
}
```

### **TypeScript**

```ts
function countHillValley(nums: number[]): number {
    const n = nums.length;
    let res = 0;
    let prev = nums[0];
    for (let i = 1; i < n - 1; i++) {
        const num = nums[i];
        const next = nums[i + 1];
        if (num == next) {
            continue;
        }
        if ((num > prev && num > next) || (num < prev && num < next)) {
            res += 1;
        }
        prev = num;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_hill_valley(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = 0;
        let mut prev = nums[0];
        for i in 1..n - 1 {
            let num = nums[i];
            let next = nums[i + 1];
            if num == next {
                continue;
            }
            if num > prev && num > next || num < prev && num < next {
                res += 1;
            }
            prev = num;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
