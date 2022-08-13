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

-   计数
    -   定义一个计数器 counter，存放数组每个元素出现的次数。
    -   然后遍历 counter 中每个元素，累加次数为 1 的所有下标即可。
-   哈希表
    -   遍历 `nums`，累加元素，并使用哈希表记录数字出现的次数。
    -   当数字二次出现时，从累加中删减，二次以上则无视。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOfUnique(self, nums: List[int]) -> int:
        counter = Counter(nums)
        return sum(num for num, cnt in counter.items() if cnt == 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int[] counter = new int[101];
        for (int num : nums) {
            ++counter[num];
        }
        int ans = 0;
        for (int i = 0; i < 101; ++i) {
            if (counter[i] == 1) {
                ans += i;
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
        vector<int> counter(101);
        for (int num : nums) ++counter[num];
        int ans = 0;
        for (int i = 0; i < 101; ++i)
            if (counter[i] == 1)
                ans += i;
        return ans;
    }
};
```

### **Go**

```go
func sumOfUnique(nums []int) int {
	counter := make([]int, 101)
	for _, num := range nums {
		counter[num]++
	}
	ans := 0
	for i := 0; i < 101; i++ {
		if counter[i] == 1 {
			ans += i
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function sumOfUnique(nums: number[]): number {
    let res = 0;
    const map = new Map();
    for (const num of nums) {
        if (map.has(num)) {
            if (map.get(num)) {
                map.set(num, false);
                res -= num;
            }
        } else {
            map.set(num, true);
            res += num;
        }
    }
    return res;
}
```

### **Rust**

-   计数

```rust
impl Solution {
    pub fn sum_of_unique(nums: Vec<i32>) -> i32 {
        let mut arr = [0; 101];
        for num in nums {
            arr[num as usize] += 1;
        }
        let mut res = 0;
        for i in 1..101 {
            if arr[i] == 1 {
                res += i;
            }
        }
        res as i32
    }
}
```

-   哈希表

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

### **...**

```

```

<!-- tabs:end -->
