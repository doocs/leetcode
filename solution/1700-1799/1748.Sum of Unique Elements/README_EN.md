# [1748. Sum of Unique Elements](https://leetcode.com/problems/sum-of-unique-elements)

[中文文档](/solution/1700-1799/1748.Sum%20of%20Unique%20Elements/README.md)

## Description

<p>You are given an integer array <code>nums</code>. The unique elements of an array are the elements that appear <strong>exactly once</strong> in the array.</p>

<p>Return <em>the <strong>sum</strong> of all the unique elements of </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The unique elements are [1,3], and the sum is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no unique elements, and the sum is 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 15
<strong>Explanation:</strong> The unique elements are [1,2,3,4,5], and the sum is 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOfUnique(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return sum(x for x, v in cnt.items() if v == 1)
```

### **Java**

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
