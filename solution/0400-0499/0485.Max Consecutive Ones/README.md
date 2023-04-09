# [485. 最大连续 1 的个数](https://leetcode.cn/problems/max-consecutive-ones)

[English Version](/solution/0400-0499/0485.Max%20Consecutive%20Ones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制数组 <code>nums</code> ， 计算其中最大连续 <code>1</code> 的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,0,1,1,1]
<strong>输出：</strong>3
<strong>解释：</strong>开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>nums = [1,0,1,1,0,1]
<b>输出：</b>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

遍历数组，记录当前连续 $1$ 的个数 `cnt`，以及最大连续 $1$ 的个数 `ans`。如果当前元素为 $1$，则 `cnt++`，否则更新 `ans`，并且 `cnt=0`。最后返回 `max(ans, cnt)` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
