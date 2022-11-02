# [2455. Average Value of Even Numbers That Are Divisible by Three](https://leetcode.com/problems/average-value-of-even-numbers-that-are-divisible-by-three)

[中文文档](/solution/2400-2499/2455.Average%20Value%20of%20Even%20Numbers%20That%20Are%20Divisible%20by%20Three/README.md)

## Description

<p>Given an integer array <code>nums</code> of <strong>positive</strong> integers, return <em>the average value of all even integers that are divisible by</em> <code>3</code><i>.</i></p>

<p>Note that the <strong>average</strong> of <code>n</code> elements is the <strong>sum</strong> of the <code>n</code> elements divided by <code>n</code> and <strong>rounded down</strong> to the nearest integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,6,10,12,15]
<strong>Output:</strong> 9
<strong>Explanation:</strong> 6 and 12 are even numbers that are divisible by 3. (6 + 12) / 2 = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,7,10]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no single number that satisfies the requirement, so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def averageValue(self, nums: List[int]) -> int:
        s = n = 0
        for v in nums:
            if v % 6 == 0:
                s += v
                n += 1
        return 0 if n == 0 else s // n
```

### **Java**

```java
class Solution {
    public int averageValue(int[] nums) {
        int s = 0, n = 0;
        for (int v : nums) {
            if (v % 6 == 0) {
                s += v;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int averageValue(vector<int>& nums) {
        int s = 0, n = 0;
        for (int v : nums) {
            if (v % 6 == 0) {
                s += v;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
};
```

### **Go**

```go
func averageValue(nums []int) int {
	s, n := 0, 0
	for _, v := range nums {
		if v%6 == 0 {
			s += v
			n++
		}
	}
	if n == 0 {
		return 0
	}
	return s / n
}
```

### **C**

```c
int averageValue(int *nums, int numsSize) {
    int sum = 0;
    int n = 0;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] % 6 == 0) {
            sum += nums[i];
            n++;
        }
    }

    if (n == 0) {
        return 0;
    }
    return sum / n;
}
```

### **TypeScript**

```ts
function averageValue(nums: number[]): number {
    let sum = 0;
    let n = 0;
    for (const num of nums) {
        if (num % 6 === 0) {
            sum += num;
            n++;
        }
    }

    if (n === 0) {
        return 0;
    }
    return Math.floor(sum / n);
}
```

### **Rust**

```rust
impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let mut sum = 0;
        let mut n = 0;
        for num in nums.iter() {
            if num % 6 == 0 {
                sum += num;
                n += 1;
            }
        }

        if n == 0 {
            return 0;
        }
        sum / n
    }
}
```

### **...**

```

```

<!-- tabs:end -->
