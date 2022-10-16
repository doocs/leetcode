# [2442. Count Number of Distinct Integers After Reverse Operations](https://leetcode.com/problems/count-number-of-distinct-integers-after-reverse-operations)

[中文文档](/solution/2400-2499/2442.Count%20Number%20of%20Distinct%20Integers%20After%20Reverse%20Operations/README.md)

## Description

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>You have to take each integer in the array, <strong>reverse its digits</strong>, and add it to the end of the array. You should apply this operation to the original integers in <code>nums</code>.</p>

<p>Return <em>the number of <strong>distinct</strong> integers in the final array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,13,10,12,31]
<strong>Output:</strong> 6
<strong>Explanation:</strong> After including the reverse of each number, the resulting array is [1,13,10,12,31,<u>1,31,1,21,13</u>].
The reversed integers that were added to the end of the array are underlined. Note that for the integer 10, after reversing it, it becomes 01 which is just 1.
The number of distinct integers in this array is 6 (The numbers 1, 10, 12, 13, 21, and 31).</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> After including the reverse of each number, the resulting array is [2,2,2,<u>2,2,2</u>].
The number of distinct integers in this array is 1 (The number 2).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
