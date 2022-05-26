# [75. 颜色分类](https://leetcode.cn/problems/sort-colors)

[English Version](/solution/0000-0099/0075.Sort%20Colors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含红色、白色和蓝色、共&nbsp;<code>n</code><em> </em>个元素的数组<meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a></strong>对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。</p>

<p>我们使用整数 <code>0</code>、&nbsp;<code>1</code> 和 <code>2</code> 分别表示红色、白色和蓝色。</p>

<ul>
</ul>

<p>必须在不使用库的sort函数的情况下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,0,2,1,1,0]
<strong>输出：</strong>[0,0,1,1,2,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,0,1]
<strong>输出：</strong>[0,1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> 为 <code>0</code>、<code>1</code> 或 <code>2</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以不使用代码库中的排序函数来解决这道题吗？</li>
	<li>你能想出一个仅使用常数空间的一趟扫描算法吗？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**计数：**

-   遍历 `nums`，记录其中 `0`、`1` 和 `2` 出现的次数。
-   依照记录的数字，按照顺序重新填充 `nums`。

**双指针：**

数组元素只存在 `0`、`1` 和 `2` 三种，因此将 `0` 移动至数组头部，`2` 移动至数组尾部，排序便完成了。

-   安排两个变量，分别指向数组头部与尾部。
-   遍历数组，分三种情况：
    -   `0`：与头指针数值交换，并向前一步，遍历指针向前。
    -   `2`：与尾指针数值交换，并向后一步。**遍历指针不变**（还需要处理交换上来的数值）。
    -   `1`：遍历指针向前。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
