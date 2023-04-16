# [977. 有序数组的平方](https://leetcode.cn/problems/squares-of-a-sorted-array)

[English Version](/solution/0900-0999/0977.Squares%20of%20a%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个按 <strong>非递减顺序</strong> 排序的整数数组 <code>nums</code>，返回 <strong>每个数字的平方</strong> 组成的新数组，要求也按 <strong>非递减顺序</strong> 排序。</p>

<ul>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-4,-1,0,3,10]
<strong>输出：</strong>[0,1,9,16,100]
<strong>解释：</strong>平方后，数组变为 [16,1,0,9,100]
排序后，数组变为 [0,1,9,16,100]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-7,-3,2,3,11]
<strong>输出：</strong>[4,9,9,49,121]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code><span>1 <= nums.length <= </span>10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
	<li><code>nums</code> 已按 <strong>非递减顺序</strong> 排序</li>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>请你<span style="color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;">设计时间复杂度为 <code>O(n)</code> 的算法解决本问题</span></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**暴力**：

1. 遍历数组，并将元素修改为对应的平方值。
2. 排序，返回。

_分析_：

因为 `nums` 中存在负数，`-10` 与 `5` 转换为平方值之后，`-10` 反而要更大，因此需要额外进行一次排序。

**双指针**：

该过程需要原数组保持不变动，对此声明一个等长数组存储计算结果，作为返回值。

声明头尾指针，并进行比较，哪方指针所指向元素的平方值更大，哪方平方值进入返回数组当中，并移动对应指针。重复比较过程，直到头指针超过尾指针。

由于是头尾指针，平方值获取过程是**从大到小**，对此存入数组的过程是**逆序**的。

```txt
SORTED-SQUARES(A)
    n = A.length
    i = 0
    j = n - 1
    k = n - 1
    let r[0..n]be a new array
    while i < j
        if A[i] * A[i] > A[j] * A[j]
            r[k] = A[i] * A[i]
            i += 1
        else
            r[k] = A[j] * A[j]
            j -= 1
        k -= 1
    return r
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        n = len(nums)
        res = [0] * n
        i, j, k = 0, n - 1, n - 1
        while i <= j:
            if nums[i] * nums[i] > nums[j] * nums[j]:
                res[k] = nums[i] * nums[i]
                i += 1
            else:
                res[k] = nums[j] * nums[j]
                j -= 1
            k -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0, j = n - 1, k = n - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                res[k--] = nums[i] * nums[i];
                ++i;
            } else {
                res[k--] = nums[j] * nums[j];
                --j;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        int n = nums.size();
        vector<int> res(n);
        for (int i = 0, j = n - 1, k = n - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                res[k--] = nums[i] * nums[i];
                ++i;
            } else {
                res[k--] = nums[j] * nums[j];
                --j;
            }
        }
        return res;
    }
};
```

### **Go**

```go
func sortedSquares(nums []int) []int {
	n := len(nums)
	res := make([]int, n)
	for i, j, k := 0, n-1, n-1; i <= j; {
		if nums[i]*nums[i] > nums[j]*nums[j] {
			res[k] = nums[i] * nums[i]
			i++
		} else {
			res[k] = nums[j] * nums[j]
			j--
		}
		k--
	}
	return res
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortedSquares = function (nums) {
    const n = nums.length;
    const res = new Array(n);
    for (let i = 0, j = n - 1, k = n - 1; i <= j; ) {
        if (nums[i] * nums[i] > nums[j] * nums[j]) {
            res[k--] = nums[i] * nums[i];
            ++i;
        } else {
            res[k--] = nums[j] * nums[j];
            --j;
        }
    }
    return res;
};
```

### **Rust**

```rust
impl Solution {
    pub fn sorted_squares(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut l = 0;
        let mut r = n - 1;
        let mut res = vec![0; n];
        for i in (0..n).rev() {
            let a = nums[l] * nums[l];
            let b = nums[r] * nums[r];
            if a < b {
                res[i] = b;
                r -= 1;
            } else {
                res[i] = a;
                l += 1;
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
     * @return Integer[]
     */
    function sortedSquares($nums) {
        $i = 0;
        $j = $k = count($nums) - 1;
        $rs = array_fill(0, count($nums), -1);
        while ($i <= $j) {
            $max1 = $nums[$i] * $nums[$i];
            $max2 = $nums[$j] * $nums[$j];
            if ($max1 > $max2) {
                $rs[$k] = $max1;
                $i++;
            } else {
                $rs[$k] = $max2;
                $j--;
            }
            $k--;
        }
        return $rs;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
