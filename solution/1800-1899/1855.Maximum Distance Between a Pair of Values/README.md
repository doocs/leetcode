# [1855. 下标对中的最大距离](https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values)

[English Version](/solution/1800-1899/1855.Maximum%20Distance%20Between%20a%20Pair%20of%20Values/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>非递增</strong> 的整数数组 <code>nums1</code>​​​​​​ 和 <code>nums2</code>​​​​​​ ，数组下标均 <strong>从 0 开始</strong> 计数。</p>

<p>下标对 <code>(i, j)</code> 中 <code>0 &lt;= i &lt; nums1.length</code> 且 <code>0 &lt;= j &lt; nums2.length</code> 。如果该下标对同时满足 <code>i &lt;= j</code> 且 <code>nums1[i] &lt;= nums2[j]</code> ，则称之为 <strong>有效</strong> 下标对，该下标对的 <strong>距离</strong> 为 <code>j - i</code>​​ 。​​</p>

<p>返回所有 <strong>有效</strong> 下标对<em> </em><code>(i, j)</code><em> </em>中的 <strong>最大距离</strong> 。如果不存在有效下标对，返回 <code>0</code> 。</p>

<p>一个数组 <code>arr</code> ，如果每个 <code>1 &lt;= i &lt; arr.length</code> 均有 <code>arr[i-1] &gt;= arr[i]</code> 成立，那么该数组是一个 <strong>非递增</strong> 数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
<strong>输出：</strong>2
<strong>解释：</strong>有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,2,2], nums2 = [10,10,1]
<strong>输出：</strong>1
<strong>解释：</strong>有效下标对是 (0,0), (0,1) 和 (1,1) 。
最大距离是 1 ，对应下标对 (0,1) 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
<strong>输出：</strong>2
<strong>解释：</strong>有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[j] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1</code> 和 <code>nums2</code> 都是 <strong>非递增</strong> 数组</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

假设 `nums1`、`nums2` 的长度分别为 $m$ 和 $n$。

遍历数组 `nums1`，对于每个数字 `nums1[i]`，二分查找 `nums2` 在 $[i,n)$ 范围内的数字，找到**最后一个**大于等于 `nums1[i]` 的位置 $j$，计算此位置与 $i$ 的距离，并更新最大距离值 `ans`。

时间复杂度 $O(m\log n)$，其中 $m$ 和 $n$ 分别为 `nums1` 和 `nums2` 的长度。

**方法二：双指针**

在方法一中，我们只利用到 `nums2` 是非递增数组这一条件，实际上，`nums1` 也是非递增数组，我们可以用双指针 $i$ 和 $j$ 来遍历 `nums1` 和 `nums2`。

时间复杂度 $O(m+n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 0
        nums2 = nums2[::-1]
        for i, v in enumerate(nums1):
            j = len(nums2) - bisect_left(nums2, v) - 1
            ans = max(ans, j - i)
        return ans
```

```python
class Solution:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        m, n = len(nums1), len(nums2)
        ans = i = j = 0
        while i < m:
            while j < n and nums1[i] <= nums2[j]:
                j += 1
            ans = max(ans, j - i - 1)
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < m; ++i) {
            int left = i, right = n - 1;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if (nums2[mid] >= nums1[i]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans = Math.max(ans, left - i);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int ans = 0;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < n && nums1[i] <= nums2[j]) {
                ++j;
            }
            ans = Math.max(ans, j - i - 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDistance(vector<int>& nums1, vector<int>& nums2) {
        int ans = 0;
        reverse(nums2.begin(), nums2.end());
        for (int i = 0; i < nums1.size(); ++i) {
            int j = nums2.size() - (lower_bound(nums2.begin(), nums2.end(), nums1[i]) - nums2.begin()) - 1;
            ans = max(ans, j - i);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxDistance(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        int ans = 0;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < n && nums1[i] <= nums2[j]) {
                ++j;
            }
            ans = max(ans, j - i - 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxDistance(nums1 []int, nums2 []int) int {
	ans, n := 0, len(nums2)
	for i, num := range nums1 {
		left, right := i, n-1
		for left < right {
			mid := (left + right + 1) >> 1
			if nums2[mid] >= num {
				left = mid
			} else {
				right = mid - 1
			}
		}
		if ans < left-i {
			ans = left - i
		}
	}
	return ans
}
```

```go
func maxDistance(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	ans := 0
	for i, j := 0, 0; i < m; i++ {
		for j < n && nums1[i] <= nums2[j] {
			j++
		}
		if ans < j-i-1 {
			ans = j - i - 1
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var maxDistance = function (nums1, nums2) {
    let ans = 0;
    let m = nums1.length;
    let n = nums2.length;
    for (let i = 0; i < m; ++i) {
        let left = i;
        let right = n - 1;
        while (left < right) {
            const mid = (left + right + 1) >> 1;
            if (nums2[mid] >= nums1[i]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        ans = Math.max(ans, left - i);
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var maxDistance = function (nums1, nums2) {
    let ans = 0;
    const m = nums1.length;
    const n = nums2.length;
    for (let i = 0, j = 0; i < m; ++i) {
        while (j < n && nums1[i] <= nums2[j]) {
            j++;
        }
        ans = Math.max(ans, j - i - 1);
    }
    return ans;
};
```

### **TypeScript**

```ts
function maxDistance(nums1: number[], nums2: number[]): number {
    let ans = 0;
    let m = nums1.length;
    let n = nums2.length;
    for (let i = 0; i < m; ++i) {
        let left = i;
        let right = n - 1;
        while (left < right) {
            const mid = (left + right + 1) >> 1;
            if (nums2[mid] >= nums1[i]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        ans = Math.max(ans, left - i);
    }
    return ans;
}
```

```ts
function maxDistance(nums1: number[], nums2: number[]): number {
    let ans = 0;
    const m = nums1.length;
    const n = nums2.length;
    for (let i = 0, j = 0; i < m; ++i) {
        while (j < n && nums1[i] <= nums2[j]) {
            j++;
        }
        ans = Math.max(ans, j - i - 1);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_distance(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let m = nums1.len();
        let n = nums2.len();
        let mut res = 0;
        for i in 0..m {
            let mut left = i;
            let mut right = n;
            while left < right {
                let mid = left + (right - left) / 2;
                if nums2[mid] >= nums1[i] {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            res = res.max((left - i - 1) as i32)
        }
        res
    }
}
```

```rust
impl Solution {
    pub fn max_distance(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let m = nums1.len();
        let n = nums2.len();
        let mut res = 0;
        let mut j = 0;
        for i in 0..m {
            while j < n && nums1[i] <= nums2[j] {
                j += 1
            }
            res = res.max((j - i - 1) as i32)
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
