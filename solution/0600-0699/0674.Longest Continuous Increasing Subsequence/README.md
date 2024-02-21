# [674. 最长连续递增序列](https://leetcode.cn/problems/longest-continuous-increasing-subsequence)

[English Version](/solution/0600-0699/0674.Longest%20Continuous%20Increasing%20Subsequence/README_EN.md)

<!-- tags:数组 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个未经排序的整数数组，找到最长且<strong> 连续递增的子序列</strong>，并返回该序列的长度。</p>

<p><strong>连续递增的子序列</strong> 可以由两个下标 <code>l</code> 和 <code>r</code>（<code>l < r</code>）确定，如果对于每个 <code>l <= i < r</code>，都有 <code>nums[i] < nums[i + 1]</code> ，那么子序列 <code>[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]</code> 就是连续递增子序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,5,4,7]
<strong>输出：</strong>3
<strong>解释：</strong>最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>最长连续递增序列是 [2], 长度为1。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：一次遍历

我们可以遍历数组 $nums$，用变量 $cnt$ 记录当前连续递增序列的长度。初始时 $cnt = 1$。

然后，我们从下标 $i = 1$ 开始，向右遍历数组 $nums$。每次遍历时，如果 $nums[i - 1] < nums[i]$，则说明当前元素可以加入到连续递增序列中，因此令 $cnt = cnt + 1$，然后更新答案为 $ans = \max(ans, cnt)$。否则，说明当前元素无法加入到连续递增序列中，因此令 $cnt = 1$。

遍历结束后，返回答案 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        ans = cnt = 1
        for i, x in enumerate(nums[1:]):
            if nums[i] < x:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 1
        return ans
```

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        for (int i = 1, cnt = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int ans = 1;
        for (int i = 1, cnt = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                ans = max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
};
```

```go
func findLengthOfLCIS(nums []int) int {
	ans, cnt := 1, 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 1
		}
	}
	return ans
}
```

```ts
function findLengthOfLCIS(nums: number[]): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn find_length_of_lcis(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        let mut cnt = 1;
        for i in 1..nums.len() {
            if nums[i - 1] < nums[i] {
                ans = ans.max(cnt + 1);
                cnt += 1;
            } else {
                cnt = 1;
            }
        }
        ans
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findLengthOfLCIS($nums) {
        $ans = 1;
        $cnt = 1;
        for ($i = 1; $i < count($nums); ++$i) {
            if ($nums[$i - 1] < $nums[$i]) {
                $ans = max($ans, ++$cnt);
            } else {
                $cnt = 1;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

### 方法二：双指针

我们也可以用双指针 $i$ 和 $j$ 找到每一段连续递增序列，找出最长的连续递增序列的长度作为答案。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        ans, n = 1, len(nums)
        i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j - 1] < nums[j]:
                j += 1
            ans = max(ans, j - i)
            i = j
        return ans
```

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        int n = nums.length;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j - 1] < nums[j]) {
                ++j;
            }
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int ans = 1;
        int n = nums.size();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j - 1] < nums[j]) {
                ++j;
            }
            ans = max(ans, j - i);
            i = j;
        }
        return ans;
    }
};
```

```go
func findLengthOfLCIS(nums []int) int {
	ans := 1
	n := len(nums)
	for i := 0; i < n; {
		j := i + 1
		for j < n && nums[j-1] < nums[j] {
			j++
		}
		ans = max(ans, j-i)
		i = j
	}
	return ans
}
```

```ts
function findLengthOfLCIS(nums: number[]): number {
    let ans = 1;
    const n = nums.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && nums[j - 1] < nums[j]) {
            ++j;
        }
        ans = Math.max(ans, j - i);
        i = j;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn find_length_of_lcis(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            while j < n && nums[j - 1] < nums[j] {
                j += 1;
            }
            ans = ans.max(j - i);
            i = j;
        }
        ans as i32
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function findLengthOfLCIS($nums) {
        $ans = 1;
        $n = count($nums);
        $i = 0;
        while ($i < $n) {
            $j = $i + 1;
            while ($j < $n && $nums[$j - 1] < $nums[$j]) {
                $j++;
            }
            $ans = max($ans, $j - $i);
            $i = $j;
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
