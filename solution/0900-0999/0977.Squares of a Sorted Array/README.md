# [977. 有序数组的平方](https://leetcode.cn/problems/squares-of-a-sorted-array)

[English Version](/solution/0900-0999/0977.Squares%20of%20a%20Sorted%20Array/README_EN.md)

<!-- tags:数组,双指针,排序 -->

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

### 方法一：双指针

由于数组 $nums$ 已经按照非递减顺序排好序，所以数组中负数的平方值是递减的，正数的平方值是递增的。我们可以使用双指针，分别指向数组的两端，每次比较两个指针指向的元素的平方值，将较大的平方值放入结果数组的末尾。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        ans = []
        i, j = 0, len(nums) - 1
        while i <= j:
            a = nums[i] * nums[i]
            b = nums[j] * nums[j]
            if a > b:
                ans.append(a)
                i += 1
            else:
                ans.append(b)
                j -= 1
        return ans[::-1]
```

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, k = n - 1; i <= j; --k) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if (a > b) {
                ans[k] = a;
                ++i;
            } else {
                ans[k] = b;
                --j;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0, j = n - 1, k = n - 1; i <= j; --k) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if (a > b) {
                ans[k] = a;
                ++i;
            } else {
                ans[k] = b;
                --j;
            }
        }
        return ans;
    }
};
```

```go
func sortedSquares(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	for i, j, k := 0, n-1, n-1; i <= j; k-- {
		a := nums[i] * nums[i]
		b := nums[j] * nums[j]
		if a > b {
			ans[k] = a
			i++
		} else {
			ans[k] = b
			j--
		}
	}
	return ans
}
```

```rust
impl Solution {
    pub fn sorted_squares(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut ans = vec![0; n];
        let (mut i, mut j) = (0, n - 1);
        for k in (0..n).rev() {
            let a = nums[i] * nums[i];
            let b = nums[j] * nums[j];
            if a > b {
                ans[k] = a;
                i += 1;
            } else {
                ans[k] = b;
                j -= 1;
            }
        }
        ans
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortedSquares = function (nums) {
    const n = nums.length;
    const ans = Array(n).fill(0);
    for (let i = 0, j = n - 1, k = n - 1; i <= j; --k) {
        const [a, b] = [nums[i] * nums[i], nums[j] * nums[j]];
        if (a > b) {
            ans[k] = a;
            ++i;
        } else {
            ans[k] = b;
            --j;
        }
    }
    return ans;
};
```

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function sortedSquares($nums) {
        $n = count($nums);
        $ans = array_fill(0, $n, 0);
        for ($i = 0, $j = $n - 1, $k = $n - 1; $i <= $j; --$k) {
            $a = $nums[$i] * $nums[$i];
            $b = $nums[$j] * $nums[$j];
            if ($a > $b) {
                $ans[$k] = $a;
                ++$i;
            } else {
                $ans[$k] = $b;
                --$j;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
