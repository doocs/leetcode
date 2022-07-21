# [面试题 21. 调整数组顺序使奇数位于偶数前面](https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

## 题目描述

<p>输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>nums =&nbsp;[1,2,3,4]
<strong>输出：</strong>[1,3,2,4] 
<strong>注：</strong>[3,1,2,4] 也是正确的答案之一。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= nums.length &lt;= 50000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10000</code></li>
</ol>

## 解法

**双指针**

定义两个指针，分别指向数组左右边缘。

-   查看左指针所指向的元素。
    -   若为 **奇数**，则左指针往右移动。
    -   若为 **偶数**，则与右指针交换元素，并将右指针往左移动。
-   重复该过程，直到左指针超过右指针。

```txt
EXCHANGE(n)
    l = 0
    r = n.length - 1
    while l < r
        if n[l] % 2 == 0
            t = n[l]
            n[l] = n[r]
            n[r] = t
            r--
        else
            l++
    return n
```

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exchange(self, nums: List[int]) -> List[int]:
        p, q = 0, len(nums) - 1
        while p < q:
            if nums[p] & 1 == 1:
                p += 1
                continue
            if nums[q] & 1 == 0:
                q -= 1
                continue
            nums[p], nums[q] = nums[q], nums[p]
        return nums
```

### **Java**

```java
class Solution {
    public int[] exchange(int[] nums) {
        int p = 0, q = nums.length - 1;
        while (p < q) {
            if ((nums[p] & 1) == 1) {
                ++p;
                continue;
            }
            if ((nums[q] & 1) == 0) {
                --q;
                continue;
            }
            swap(nums, p, q);
        }
        return nums;
    }

    private void swap(int[] nums, int p, int q) {
        int t = nums[p];
        nums[p] = nums[q];
        nums[q] = t;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var exchange = function (nums) {
    let left = 0;
    let right = nums.length - 1;
    while (left < right) {
        let c = nums[left];
        nums[left] = nums[right];
        nums[right] = c;
        while (nums[left] % 2) {
            left++;
        }
        while (nums[right] % 2 === 0) {
            right--;
        }
    }
    return nums;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> exchange(vector<int>& nums) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) == 1) {
                ++left;
            }
            while (left < right && (nums[right] & 1) == 0) {
                --right;
            }
            swap(nums[left], nums[right]);
        }
        return nums;
    }
};
```

### **TypeScript**

```ts
function exchange(nums: number[]): number[] {
    let l = 0;
    let r = nums.length - 1;
    while (l < r) {
        if (nums[l] % 2 === 0) {
            [nums[l], nums[r]] = [nums[r], nums[l]];
            r--;
        } else {
            l++;
        }
    }
    return nums;
}
```

### **Rust**

```rust
impl Solution {
    pub fn exchange(mut nums: Vec<i32>) -> Vec<i32> {
        if nums.len() == 0 {
            return nums;
        }
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l < r {
            if nums[l] % 2 == 0 {
                nums.swap(l, r);
                r -= 1;
            } else {
                l += 1;
            }
        }
        nums
    }
}
```

### **C#**

```cs
public class Solution {
    public int[] Excahnge(int[] nums) {
        int p = 0, q = nums.Length - 1;
        while (p < q) {
            if (nums[p] % 2 == 1) {
                p += 1;
                continue;
            }
            if (nums[q] % 2 == 0) {
                q -= 1;
                continue;
            }
            nums[p] = nums[p] + nums[q];
            nums[q] = nums[p] - nums[q];
            nums[p] = nums[p] - nums[q];
        }
        return nums;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
