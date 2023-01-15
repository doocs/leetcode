# [2293. Min Max Game](https://leetcode.com/problems/min-max-game)

[中文文档](/solution/2200-2299/2293.Min%20Max%20Game/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> whose length is a power of <code>2</code>.</p>

<p>Apply the following algorithm on <code>nums</code>:</p>

<ol>
	<li>Let <code>n</code> be the length of <code>nums</code>. If <code>n == 1</code>, <strong>end</strong> the process. Otherwise, <strong>create</strong> a new <strong>0-indexed</strong> integer array <code>newNums</code> of length <code>n / 2</code>.</li>
	<li>For every <strong>even</strong> index <code>i</code> where <code>0 &lt;= i &lt; n / 2</code>, <strong>assign</strong> the value of <code>newNums[i]</code> as <code>min(nums[2 * i], nums[2 * i + 1])</code>.</li>
	<li>For every <strong>odd</strong> index <code>i</code> where <code>0 &lt;= i &lt; n / 2</code>, <strong>assign</strong> the value of <code>newNums[i]</code> as <code>max(nums[2 * i], nums[2 * i + 1])</code>.</li>
	<li><strong>Replace</strong> the array <code>nums</code> with <code>newNums</code>.</li>
	<li><strong>Repeat</strong> the entire process starting from step 1.</li>
</ol>

<p>Return <em>the last number that remains in </em><code>nums</code><em> after applying the algorithm.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2293.Min%20Max%20Game/images/example1drawio-1.png" style="width: 500px; height: 240px;" />
<pre>
<strong>Input:</strong> nums = [1,3,5,2,4,8,2,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The following arrays are the results of applying the algorithm repeatedly.
First: nums = [1,5,4,2]
Second: nums = [1,4]
Third: nums = [1]
1 is the last remaining number, so we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 is already the last remaining number, so we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1024</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums.length</code> is a power of <code>2</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minMaxGame(self, nums: List[int]) -> int:
        n = len(nums)
        while n > 1:
            n >>= 1
            for i in range(n):
                a, b = nums[i << 1], nums[i << 1 | 1]
                nums[i] = min(a, b) if i % 2 == 0 else max(a, b)
        return nums[0]
```

### **Java**

```java
class Solution {
    public int minMaxGame(int[] nums) {
        for (int n = nums.length; n > 1;) {
            n >>= 1;
            for (int i = 0; i < n; ++i) {
                int a = nums[i << 1], b = nums[i << 1 | 1];
                nums[i] = i % 2 == 0 ? Math.min(a, b) : Math.max(a, b);
            }
        }
        return nums[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMaxGame(vector<int>& nums) {
        for (int n = nums.size(); n > 1;) {
            n >>= 1;
            for (int i = 0; i < n; ++i) {
                int a = nums[i << 1], b = nums[i << 1 | 1];
                nums[i] = i % 2 == 0 ? min(a, b) : max(a, b);
            }
        }
        return nums[0];
    }
};
```

### **Go**

```go
func minMaxGame(nums []int) int {
	for n := len(nums); n > 1; {
		n >>= 1
		for i := 0; i < n; i++ {
			a, b := nums[i<<1], nums[i<<1|1]
			if i%2 == 0 {
				nums[i] = min(a, b)
			} else {
				nums[i] = max(a, b)
			}
		}
	}
	return nums[0]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minMaxGame(nums: number[]): number {
    for (let n = nums.length; n > 1; ) {
        n >>= 1;
        for (let i = 0; i < n; ++i) {
            const a = nums[i << 1];
            const b = nums[(i << 1) | 1];
            nums[i] = i % 2 == 0 ? Math.min(a, b) : Math.max(a, b);
        }
    }
    return nums[0];
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_max_game(mut nums: Vec<i32>) -> i32 {
        let mut n = nums.len();
        while n != 1 {
            n >>= 1;
            for i in 0..n {
                nums[i] = (if i & 1 == 1 {
                    i32::max
                } else {
                    i32::min
                })(nums[i << 1], nums[i << 1 | 1])
            }
        }
        nums[0]
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))
#define max(a, b) (((a) > (b)) ? (a) : (b))

int minMaxGame(int *nums, int numsSize) {
    while (numsSize != 1) {
        numsSize >>= 1;
        for (int i = 0; i < numsSize; i++) {
            int a = nums[i << 1];
            int b = nums[i << 1 | 1];
            nums[i] = i & 1 ? max(a, b) : min(a, b);
        }
    }
    return nums[0];
}
```

### **...**

```

```

<!-- tabs:end -->
