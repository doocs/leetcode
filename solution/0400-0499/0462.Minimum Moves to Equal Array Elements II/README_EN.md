# [462. Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii)

[中文文档](/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README.md)

## Description

<p>Given an integer array <code>nums</code> of size <code>n</code>, return <em>the minimum number of moves required to make all array elements equal</em>.</p>

<p>In one move, you can increment or decrement an element of the array by <code>1</code>.</p>

<p>Test cases are designed so that the answer will fit in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Only two moves are needed (remember each move increments or decrements one element):
[<u>1</u>,2,3]  =&gt;  [2,2,<u>3</u>]  =&gt;  [2,2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,10,2,9]
<strong>Output:</strong> 16
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        nums.sort()
        k = nums[len(nums) >> 1]
        return sum(abs(v - k) for v in nums)
```

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        def move(i):
            v = nums[i]
            a = v * i - s[i]
            b = s[-1] - s[i + 1] - v * (n - i - 1)
            return a + b

        nums.sort()
        s = [0] + list(accumulate(nums))
        n = len(nums)
        return min(move(i) for i in range(n))
```

### **Java**

```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int k = nums[nums.length >> 1];
        int ans = 0;
        for (int v : nums) {
            ans += Math.abs(v - k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMoves2(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int k = nums[nums.size() >> 1];
        int ans = 0;
        for (int& v : nums) ans += abs(v - k);
        return ans;
    }
};
```

### **Go**

```go
func minMoves2(nums []int) int {
	sort.Ints(nums)
	k := nums[len(nums)>>1]
	ans := 0
	for _, v := range nums {
		ans += abs(v - k)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function minMoves2(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const mid = nums[nums.length >> 1];
    return nums.reduce((r, v) => r + Math.abs(v - mid), 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_moves2(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let mid = nums[nums.len() / 2];
        let mut res = 0;
        for num in nums.iter() {
            res += (num - mid).abs();
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
