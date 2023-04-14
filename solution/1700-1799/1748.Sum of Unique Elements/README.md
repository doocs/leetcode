# [1748. 唯一元素的和](https://leetcode.cn/problems/sum-of-unique-elements)

[English Version](/solution/1700-1799/1748.Sum%20of%20Unique%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。数组中唯一元素是那些只出现 <strong>恰好一次</strong> 的元素。</p>

<p>请你返回 <code>nums</code> 中唯一元素的 <strong>和</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,2]
<b>输出：</b>4
<b>解释：</b>唯一元素为 [1,3] ，和为 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,1,1,1,1]
<b>输出：</b>0
<b>解释：</b>没有唯一元素，和为 0 。
</pre>

<p><strong>示例 3 ：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>15
<b>解释：</b>唯一元素为 [1,2,3,4,5] ，和为 15 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们可以用数组或哈希表 `cnt` 统计数组 `nums` 中每个数字出现的次数，然后遍历 `cnt`，对于出现次数为 1 的数字，将其加入答案。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(M)$。其中 $n$ 和 $m$ 分别是数组 `nums` 的长度和 `nums` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOfUnique(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return sum(x for x, v in cnt.items() if v == 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int x = 0; x < 101; ++x) {
            if (cnt[x] == 1) {
                ans += x;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int ans = 0;
        int[] cnt = new int[101];
        for (int x : nums) {
            if (++cnt[x] == 1) {
                ans += x;
            } else if (cnt[x] == 2) {
                ans -= x;
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
    int sumOfUnique(vector<int>& nums) {
        int cnt[101]{};
        for (int& x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int x = 0; x < 101; ++x) {
            if (cnt[x] == 1) {
                ans += x;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int sumOfUnique(vector<int>& nums) {
        int ans = 0;
        int cnt[101]{};
        for (int& x : nums) {
            if (++cnt[x] == 1) {
                ans += x;
            } else if (cnt[x] == 2) {
                ans -= x;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func sumOfUnique(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := 0; x < 101; x++ {
		if cnt[x] == 1 {
			ans += x
		}
	}
	return
}
```

```go
func sumOfUnique(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] == 1 {
			ans += x
		} else if cnt[x] == 2 {
			ans -= x
		}
	}
	return
}
```

### **TypeScript**

```ts
function sumOfUnique(nums: number[]): number {
    const cnt = new Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let ans = 0;
    for (let x = 0; x < 101; ++x) {
        if (cnt[x] == 1) {
            ans += x;
        }
    }
    return ans;
}
```

```ts
function sumOfUnique(nums: number[]): number {
    let ans = 0;
    const cnt = new Array(101).fill(0);
    for (const x of nums) {
        if (++cnt[x] === 1) {
            ans += x;
        } else if (cnt[x] === 2) {
            ans -= x;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn sum_of_unique(nums: Vec<i32>) -> i32 {
        let mut cnt = [0; 101];
        for x in nums {
            cnt[x as usize] += 1;
        }
        let mut ans = 0;
        for x in 1..101 {
            if cnt[x] == 1 {
                ans += x;
            }
        }
        ans as i32
    }
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn sum_of_unique(nums: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut map = HashMap::new();
        for num in nums {
            if map.contains_key(&num) {
                if *map.get(&num).unwrap() {
                    map.insert(num, false);
                    res -= num;
                }
            } else {
                map.insert(num, true);
                res += num;
            }
        }
        res
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
    function sumOfUnique($nums) {
        $sum = 0;
        for ($i = 0; $i < count($nums); $i++) {
            $hashtable[$nums[$i]] += 1;
            if ($hashtable[$nums[$i]] == 1) $sum += $nums[$i];
            if ($hashtable[$nums[$i]] == 2) $sum -= $nums[$i];
        }
        return $sum;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
