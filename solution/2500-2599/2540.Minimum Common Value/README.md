# [2540. 最小公共值](https://leetcode.cn/problems/minimum-common-value)

[English Version](/solution/2500-2599/2540.Minimum%20Common%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，它们已经按非降序排序，请你返回两个数组的 <strong>最小公共整数</strong>&nbsp;。如果两个数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;没有公共整数，请你返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>如果一个整数在两个数组中都 <strong>至少出现一次</strong>&nbsp;，那么这个整数是数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;<strong>公共</strong>&nbsp;的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [1,2,3], nums2 = [2,4]
<b>输出：</b>2
<b>解释：</b>两个数组的最小公共元素是 2 ，所以我们返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [1,2,3,6], nums2 = [2,3,4,5]
<b>输出：</b>2
<b>解释：</b>两个数组中的公共元素是 2 和 3 ，2 是较小值，所以返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;都是 <strong>非降序</strong>&nbsp;的。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

遍历两个数组，如果两个指针指向的元素相等，则返回该元素；如果两个指针指向的元素不相等，则将指向较小元素的指针右移一位，直到找到相等的元素或者遍历完数组。

时间复杂度 $O(m + n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是两个数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getCommon(self, nums1: List[int], nums2: List[int]) -> int:
        i = j = 0
        m, n = len(nums1), len(nums2)
        while i < m and j < n:
            if nums1[i] == nums2[j]:
                return nums1[i]
            if nums1[i] < nums2[j]:
                i += 1
            else:
                j += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        for (int i = 0, j = 0; i < m && j < n;) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            }
            if (nums1[i] < nums2[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getCommon(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        for (int i = 0, j = 0; i < m && j < n;) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            }
            if (nums1[i] < nums2[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func getCommon(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	for i, j := 0, 0; i < m && j < n; {
		if nums1[i] == nums2[j] {
			return nums1[i]
		}
		if nums1[i] < nums2[j] {
			i++
		} else {
			j++
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function getCommon(nums1: number[], nums2: number[]): number {
    const m = nums1.length;
    const n = nums2.length;
    let i = 0;
    let j = 0;
    while (i < m && j < n) {
        if (nums1[i] === nums2[j]) {
            return nums1[i];
        }
        if (nums1[i] < nums2[j]) {
            i++;
        } else {
            j++;
        }
    }
    return -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn get_common(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let m = nums1.len();
        let n = nums2.len();
        let mut i = 0;
        let mut j = 0;
        while i < m && j < n {
            if nums1[i] == nums2[j] {
                return nums1[i];
            }
            if nums1[i] < nums2[j] {
                i += 1;
            } else {
                j += 1;
            }
        }
        -1
    }
}
```

### **C**

```c
int getCommon(int *nums1, int nums1Size, int *nums2, int nums2Size) {
    int i = 0;
    int j = 0;
    while (i < nums1Size && j < nums2Size) {
        if (nums1[i] == nums2[j]) {
            return nums1[i];
        }
        if (nums1[i] < nums2[j]) {
            i++;
        } else {
            j++;
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
