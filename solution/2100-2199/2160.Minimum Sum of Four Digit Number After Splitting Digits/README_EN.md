# [2160. Minimum Sum of Four Digit Number After Splitting Digits](https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits)

[中文文档](/solution/2100-2199/2160.Minimum%20Sum%20of%20Four%20Digit%20Number%20After%20Splitting%20Digits/README.md)

## Description

<p>You are given a <strong>positive</strong> integer <code>num</code> consisting of exactly four digits. Split <code>num</code> into two new integers <code>new1</code> and <code>new2</code> by using the <strong>digits</strong> found in <code>num</code>. <strong>Leading zeros</strong> are allowed in <code>new1</code> and <code>new2</code>, and <strong>all</strong> the digits found in <code>num</code> must be used.</p>

<ul>
	<li>For example, given <code>num = 2932</code>, you have the following digits: two <code>2</code>&#39;s, one <code>9</code> and one <code>3</code>. Some of the possible pairs <code>[new1, new2]</code> are <code>[22, 93]</code>, <code>[23, 92]</code>, <code>[223, 9]</code> and <code>[2, 329]</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> possible sum of </em><code>new1</code><em> and </em><code>new2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 2932
<strong>Output:</strong> 52
<strong>Explanation:</strong> Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 4009
<strong>Output:</strong> 13
<strong>Explanation:</strong> Some possible pairs [new1, new2] are [0, 49], [490, 0], etc. 
The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1000 &lt;= num &lt;= 9999</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumSum(self, num: int) -> int:
        nums = []
        while num:
            nums.append(num % 10)
            num //= 10
        nums.sort()
        return 10 * (nums[0] + nums[1]) + nums[2] + nums[3]
```

### **Java**

```java
class Solution {
    public int minimumSum(int num) {
        int[] nums = new int[4];
        for (int i = 0; num != 0; ++i) {
            nums[i] = num % 10;
            num /= 10;
        }
        Arrays.sort(nums);
        return 10 * (nums[0] + nums[1]) + nums[2] + nums[3];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSum(int num) {
        vector<int> nums;
        while (num) {
            nums.push_back(num % 10);
            num /= 10;
        }
        sort(nums.begin(), nums.end());
        return 10 * (nums[0] + nums[1]) + nums[2] + nums[3];
    }
};
```

### **Go**

```go
func minimumSum(num int) int {
	var nums []int
	for num > 0 {
		nums = append(nums, num%10)
		num /= 10
	}
	sort.Ints(nums)
	return 10*(nums[0]+nums[1]) + nums[2] + nums[3]
}
```

### **TypeScript**

```ts
function minimumSum(num: number): number {
    const nums = new Array(4).fill(0);
    for (let i = 0; i < 4; i++) {
        nums[i] = num % 10;
        num = Math.floor(num / 10);
    }
    nums.sort((a, b) => a - b);
    return 10 * (nums[0] + nums[1]) + nums[2] + nums[3];
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimum_sum(mut num: i32) -> i32 {
        let mut nums = [0; 4];
        for i in 0..4 {
            nums[i] = num % 10;
            num /= 10;
        }
        nums.sort();
        10 * (nums[0] + nums[1]) + nums[2] + nums[3]
    }
}
```

### **C**

```c
int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int minimumSum(int num) {
    int nums[4] = {0};
    for (int i = 0; i < 4; i++) {
        nums[i] = num % 10;
        num /= 10;
    }
    qsort(nums, 4, sizeof(int), cmp);
    return 10 * (nums[0] + nums[1])  + nums[2] + nums[3];
}
```

### **...**

```

```

<!-- tabs:end -->
