# [2442. 反转之后不同整数的数目](https://leetcode.cn/problems/count-number-of-distinct-integers-after-reverse-operations)

[English Version](/solution/2400-2499/2442.Count%20Number%20of%20Distinct%20Integers%20After%20Reverse%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>正</strong> 整数组成的数组 <code>nums</code> 。</p>

<p>你必须取出数组中的每个整数，<strong>反转其中每个数位</strong>，并将反转后得到的数字添加到数组的末尾。这一操作只针对 <code>nums</code> 中原有的整数执行。</p>

<p>返回结果数组中 <strong>不同</strong> 整数的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,13,10,12,31]
<strong>输出：</strong>6
<strong>解释：</strong>反转每个数字后，结果数组是 [1,13,10,12,31,<em><strong>1,31,1,21,13</strong></em>] 。
反转后得到的数字添加到数组的末尾并按斜体加粗表示。注意对于整数 10 ，反转之后会变成 01 ，即 1 。
数组中不同整数的数目为 6（数字 1、10、12、13、21 和 31）。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>反转每个数字后，结果数组是 [2,2,2,<em><strong>2,2,2</strong></em>] 。
数组中不同整数的数目为 1（数字 2）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们先用哈希表记录数组中的所有整数，然后遍历数组中的每个整数，对其进行反转，将反转后的整数添加到哈希表中，最后返回哈希表的大小即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDistinctIntegers(self, nums: List[int]) -> int:
        s = set(nums)
        for x in nums:
            y = int(str(x)[::-1])
            s.add(y)
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countDistinctIntegers(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        for (int x : nums) {
            int y = 0;
            while (x > 0) {
                y = y * 10 + x % 10;
                x /= 10;
            }
            s.add(y);
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countDistinctIntegers(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        for (int x : nums) {
            int y = 0;
            while (x) {
                y = y * 10 + x % 10;
                x /= 10;
            }
            s.insert(y);
        }
        return s.size();
    }
};
```

### **Go**

```go
func countDistinctIntegers(nums []int) int {
	s := map[int]struct{}{}
	for _, x := range nums {
		s[x] = struct{}{}
	}
	for _, x := range nums {
		y := 0
		for x > 0 {
			y = y*10 + x%10
			x /= 10
		}
		s[y] = struct{}{}
	}
	return len(s)
}
```

### **TypeScript**

```ts
function countDistinctIntegers(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i < n; i++) {
        nums.push(Number([...(nums[i] + '')].reverse().join('')));
    }
    return new Set(nums).size;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn count_distinct_integers(nums: Vec<i32>) -> i32 {
        let mut set = HashSet::new();
        for i in 0..nums.len() {
            let mut num = nums[i];
            set.insert(num);
            set.insert({
                let mut item = 0;
                while num > 0 {
                    item = item * 10 + num % 10;
                    num /= 10;
                }
                item
            });
        }
        set.len() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
