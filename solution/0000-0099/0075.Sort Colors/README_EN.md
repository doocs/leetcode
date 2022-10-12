# [75. Sort Colors](https://leetcode.com/problems/sort-colors)

[中文文档](/solution/0000-0099/0075.Sort%20Colors/README.md)

## Description

<p>Given an array <code>nums</code> with <code>n</code> objects colored red, white, or blue, sort them <strong><a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> </strong>so that objects of the same color are adjacent, with the colors in the order red, white, and blue.</p>

<p>We will use the integers <code>0</code>, <code>1</code>, and <code>2</code> to represent the color red, white, and blue, respectively.</p>

<p>You must solve this problem without using the library&#39;s sort function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,2,1,1,0]
<strong>Output:</strong> [0,0,1,1,2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,1]
<strong>Output:</strong> [0,1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> is either <code>0</code>, <code>1</code>, or <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong>&nbsp;Could you come up with a one-pass algorithm using only&nbsp;constant extra space?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        i, j = -1, len(nums)
        cur = 0
        while cur < j:
            if nums[cur] == 0:
                i += 1
                nums[cur], nums[i] = nums[i], nums[cur]
                cur += 1
            elif nums[cur] == 1:
                cur += 1
            else:
                j -= 1
                nums[cur], nums[j] = nums[j], nums[cur]
```

### **Java**

```java
class Solution {
    public void sortColors(int[] nums) {
        int i = -1, j = nums.length;
        int cur = 0;
        while (cur < j) {
            if (nums[cur] == 0) {
                swap(nums, cur++, ++i);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums, cur, --j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **TypeScript**

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    let n = nums.length;
    if (n < 2) return;
    let p0 = 0,
        p2 = n - 1;
    let p1 = 0;
    while (p1 <= p2) {
        if (nums[p1] == 0) {
            [nums[p0], nums[p1]] = [nums[p1], nums[p0]];
            p0++;
            p1++;
        } else if (nums[p1] == 1) {
            p1++;
        } else {
            [nums[p1], nums[p2]] = [nums[p2], nums[p1]];
            p2--;
        }
    }
}
```

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    const n = nums.length;
    let l = -1;
    let r = n;
    let i = 0;
    while (i < r) {
        if (nums[i] === 2) {
            r--;
            [nums[r], nums[i]] = [nums[i], nums[r]];
        } else {
            if (nums[i] === 0) {
                l++;
                [nums[l], nums[i]] = [nums[i], nums[l]];
            }
            i++;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void sortColors(vector<int>& nums) {
        int i = -1, j = nums.size(), cur = 0;
        while (cur < j) {
            if (nums[cur] == 0) {
                swap(nums[++i], nums[cur++]);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums[cur], nums[--j]);
            }
        }
    }
};
```

### **Go**

```go
func sortColors(nums []int) {
	i, j, cur := -1, len(nums), 0
	for cur < j {
		if nums[cur] == 0 {
			i++
			nums[cur], nums[i] = nums[i], nums[cur]
			cur++
		} else if nums[cur] == 1 {
			cur++
		} else {
			j--
			nums[cur], nums[j] = nums[j], nums[cur]
		}
	}
}
```

### **Rust**

```rust
impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let mut l = 0;
        let mut r = nums.len() - 1;
        let mut i = 0;
        while i <= r {
            match nums[i] {
                2 => {
                    nums.swap(i, r);
                    match r {
                        0 => return,
                        _ => r -= 1,
                    }
                }
                n => {
                    if n == 0 {
                        nums.swap(i, l);
                        l += 1;
                    }
                    i += 1;
                }
            }
        }
    }
}
```

```rust
impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let mut count = [0, 0, 0];
        for num in nums.iter() {
            count[*num as usize] += 1;
        }
        count[1] += count[0];
        count[2] += count[1];

        for i in 0..count[0] {
            nums[i] = 0;
        }
        for i in count[0]..count[1] {
            nums[i] = 1;
        }
        for i in count[1]..count[2] {
            nums[i] = 2;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
