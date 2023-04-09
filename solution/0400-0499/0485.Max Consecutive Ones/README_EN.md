# [485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones)

[中文文档](/solution/0400-0499/0485.Max%20Consecutive%20Ones/README.md)

## Description

<p>Given a binary array <code>nums</code>, return <em>the maximum number of consecutive </em><code>1</code><em>&#39;s in the array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,1,0,1]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        cnt = ans = 0
        for v in nums:
            if v == 1:
                cnt += 1
            else:
                ans = max(ans, cnt)
                cnt = 0
        return max(ans, cnt)
```

### **Java**

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0, ans = 0;
        for (int v : nums) {
            if (v == 1) {
                ++cnt;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }
        return Math.max(cnt, ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int cnt = 0, ans = 0;
        for (int v : nums) {
            if (v == 1) {
                ++cnt;
            } else {
                ans = max(ans, cnt);
                cnt = 0;
            }
        }
        return max(ans, cnt);
    }
};
```

### **Go**

```go
func findMaxConsecutiveOnes(nums []int) int {
	ans, cnt := 0, 0
	for _, v := range nums {
		if v == 1 {
			cnt++
		} else {
			ans = max(ans, cnt)
			cnt = 0
		}
	}
	return max(ans, cnt)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function (nums) {
    let res = 0,
        t = 0;
    for (let num of nums) {
        if (num == 1) {
            ++t;
        } else {
            res = Math.max(res, t);
            t = 0;
        }
    }
    return Math.max(res, t);
};
```

### **TypeScript**

```ts
function findMaxConsecutiveOnes(nums: number[]): number {
    let res = 0;
    let count = 0;
    for (const num of nums) {
        if (num === 0) {
            res = Math.max(res, count);
            count = 0;
        } else {
            count++;
        }
    }
    return Math.max(res, count);
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_max_consecutive_ones(nums: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut count = 0;
        for num in nums {
            if num == 0 {
                res = res.max(count);
                count = 0;
            } else {
                count += 1;
            }
        }
        res.max(count)
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findMaxConsecutiveOnes($nums) {
        $tmp = $max = 0;
        for ($i = 0; $i < count($nums); $i++) {
            if ($nums[$i] == 1) $tmp++;
            else {
                $max = max($tmp, $max);
                $tmp = 0;
            }
        }
        return max($tmp, $max);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
