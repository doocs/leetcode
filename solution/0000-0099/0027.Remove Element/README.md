---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0027.Remove%20Element/README.md
tags:
    - 数组
    - 双指针
---

<!-- problem:start -->

# [27. 移除元素](https://leetcode.cn/problems/remove-element)

[English Version](/solution/0000-0099/0027.Remove%20Element/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>nums</code><em>&nbsp;</em>和一个值 <code>val</code>，你需要 <strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a></strong> 移除所有数值等于&nbsp;<code>val</code><em>&nbsp;</em>的元素。元素的顺序可能发生改变。然后返回&nbsp;<code>nums</code>&nbsp;中与&nbsp;<code>val</code>&nbsp;不同的元素的数量。</p>

<p>假设 <code>nums</code> 中不等于 <code>val</code> 的元素数量为 <code>k</code>，要通过此题，您需要执行以下操作：</p>

<ul>
	<li>更改 <code>nums</code> 数组，使 <code>nums</code> 的前 <code>k</code> 个元素包含不等于 <code>val</code> 的元素。<code>nums</code> 的其余元素和 <code>nums</code> 的大小并不重要。</li>
	<li>返回 <code>k</code>。</li>
</ul>

<p><strong>用户评测：</strong></p>

<p>评测机将使用以下代码测试您的解决方案：</p>

<pre>
int[] nums = [...]; // 输入数组
int val = ...; // 要移除的值
int[] expectedNums = [...]; // 长度正确的预期答案。
                            // 它以不等于 val 的值排序。

int k = removeElement(nums, val); // 调用你的实现

assert k == expectedNums.length;
sort(nums, 0, k); // 排序 nums 的前 k 个元素
for (int i = 0; i &lt; actualLength; i++) {
    assert nums[i] == expectedNums[i];
}</pre>

<p>如果所有的断言都通过，你的解决方案将会 <strong>通过</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,2,3], val = 3
<strong>输出：</strong>2, nums = [2,2,_,_]
<strong>解释：</strong>你的函数函数应该返回 k = 2, 并且 nums<em> </em>中的前两个元素均为 2。
你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,2,2,3,0,4,2], val = 2
<strong>输出：</strong>5, nums = [0,1,4,0,3,_,_,_]
<strong>解释：</strong>你的函数应该返回 k = 5，并且 nums 中的前五个元素为 0,0,1,3,4。
注意这五个元素可以任意顺序返回。
你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>0 &lt;= val &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们用变量 $k$ 记录当前不等于 $val$ 的元素个数。

遍历数组 $nums$，如果当前元素 $x$ 不等于 $val$，则将 $x$ 赋值给 $nums[k]$，并将 $k$ 自增 $1$。

最后返回 $k$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        k = 0
        for x in nums:
            if x != val:
                nums[k] = x
                k += 1
        return k
```

#### Java

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int x : nums) {
            if (x != val) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int k = 0;
        for (int x : nums) {
            if (x != val) {
                nums[k++] = x;
            }
        }
        return k;
    }
};
```

#### Go

```go
func removeElement(nums []int, val int) int {
	k := 0
	for _, x := range nums {
		if x != val {
			nums[k] = x
			k++
		}
	}
	return k
}
```

#### TypeScript

```ts
function removeElement(nums: number[], val: number): number {
    let k: number = 0;
    for (const x of nums) {
        if (x !== val) {
            nums[k++] = x;
        }
    }
    return k;
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_element(nums: &mut Vec<i32>, val: i32) -> i32 {
        let mut k = 0;
        for i in 0..nums.len() {
            if nums[i] != val {
                nums[k] = nums[i];
                k += 1;
            }
        }
        k as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function (nums, val) {
    let k = 0;
    for (const x of nums) {
        if (x !== val) {
            nums[k++] = x;
        }
    }
    return k;
};
```

#### C#

```cs
public class Solution {
    public int RemoveElement(int[] nums, int val) {
        int k = 0;
        foreach (int x in nums) {
            if (x != val) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $val
     * @return Integer
     */
    function removeElement(&$nums, $val) {
        for ($i = count($nums) - 1; $i >= 0; $i--) {
            if ($nums[$i] == $val) {
                array_splice($nums, $i, 1);
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
