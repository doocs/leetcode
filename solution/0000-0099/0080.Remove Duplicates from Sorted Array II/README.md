---
comments: true
difficulty: 中等
tags:
    - 数组
    - 双指针
---

<!-- problem:start -->

# [80. 删除有序数组中的重复项 II](https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii)

[English Version](/solution/0000-0099/0080.Remove%20Duplicates%20from%20Sorted%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个按&nbsp;<strong>非递减顺序&nbsp;</strong>排列的整数数组 <code>nums</code>，请你&nbsp;<a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"><strong>原地</strong></a>&nbsp;删除部分重复元素，使得每个不同的元素&nbsp;<strong>最多出现两次</strong>。元素的&nbsp;<strong>相对顺序&nbsp;</strong>应保持&nbsp;<strong>不变</strong>。</p>

<p>由于在某些语言中无法改变数组的长度，因此必须将结果放在数组 <code>nums</code> 的<strong>前半部分</strong>。更准确地说，如果删除重复元素后有 <code>k</code> 个元素，那么 <code>nums</code> 的前 <code>k</code> 个元素应该保存最终结果。前 <code>k</code> 个元素之后的内容无关紧要。</p>

<p>将最终结果放入 <code>nums</code> 的前 <code>k</code> 个位置后，返回 <code>k</code>。</p>

<p><strong>不要&nbsp;</strong>为另一个数组分配额外空间。你必须使用 O(1) 的额外内存，<strong>通过&nbsp;<a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a>&nbsp;修改输入数组&nbsp;</strong>来完成此操作。</p>

<p><strong>自定义判题：</strong></p>

<p>判题程序将使用以下代码测试你的解答：</p>

<pre>
int[] nums = [...]; // 输入数组
int[] expectedNums = [...]; // 长度正确的预期答案

int k = removeDuplicates(nums); // 调用你的实现

assert k == expectedNums.length;
for (int i = 0; i &lt; k; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>如果所有断言都通过，那么你的解答将被&nbsp;<strong>接受</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,2,2,3]
<strong>输出：</strong>5, nums = [1,1,2,2,3,_]
<strong>解释：</strong>你的函数应该返回 k = 5，并且 nums 的前五个元素分别为 1、1、2、2 和 3。
返回的 k 之后的元素是什么并不重要（因此用下划线表示）。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,1,1,1,1,2,3,3]
<strong>输出：</strong>7, nums = [0,0,1,1,2,3,3,_,_]
<strong>解释：</strong>你的函数应该返回 k = 7，并且 nums 的前七个元素分别为 0、0、1、1、2、3 和 3。
返回的 k 之后的元素是什么并不重要（因此用下划线表示）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> 已按&nbsp;<strong>非递减&nbsp;</strong>顺序排列。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

<!-- thinking:start -->

> **思考**
>
> 另开数组、每个值最多抄写两次可以完成，但题目要求原地修改且额外空间为 $O(1)$。 $n \le 3 \times 10^4$，应当一趟完成。
>
> 由于数组有序，相同值必然相邻。问题 26 保留一次，本题保留至多两次。用 $k$ 表示已保留前缀的长度：当前值 $x$ 可以写入，当且仅当已保留不足两个，或它与倒数第二个保留值不同——否则即为第三次出现。写入 $\textit{nums}[k]$ 后令 $k$ 加一，扫完返回 $k$。

<!-- thinking:end -->

我们用一个变量 $k$ 记录当前已经处理好的数组的长度，初始时 $k=0$，表示空数组。

然后我们从左到右遍历数组，对于遍历到的每个元素 $x$，如果 $k \lt 2$ 或者 $x \neq \textit{nums}[k-2]$，我们就将 $x$ 放到 $\textit{nums}[k]$ 的位置，然后 $k$ 自增 $1$。否则， $x$ 与 $\textit{nums}[k-2]$ 相同，我们直接跳过这个元素。继续遍历，直到遍历完整个数组。

这样，当遍历结束时， $\textit{nums}$ 中前 $k$ 个元素就是我们要求的答案，且 $k$ 就是答案的长度。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

补充：

原问题要求最多相同的数字最多出现 $2$ 次，我们可以扩展至相同的数字最多保留 $k$ 个。

- 由于相同的数字最多保留 $k$ 个，那么原数组的前 $k$ 个元素我们可以直接保留；
- 对于后面的数字，能够保留的前提是：当前数字 $x$ 与前面已保留的数字的倒数第 $k$ 个元素比较，不同则保留，相同则跳过。

相似题目：

- [26. 删除有序数组中的重复项](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0026.Remove%20Duplicates%20from%20Sorted%20Array/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeDuplicates(self, nums: list[int]) -> int:
        k = 0
        for x in nums:
            if k < 2 or x != nums[k - 2]:
                nums[k] = x
                k += 1
        return k
```

#### Java

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int x : nums) {
            if (k < 2 || x != nums[k - 2]) {
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
    int removeDuplicates(vector<int>& nums) {
        int k = 0;
        for (int x : nums) {
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
};
```

#### Go

```go
func removeDuplicates(nums []int) int {
	k := 0
	for _, x := range nums {
		if k < 2 || x != nums[k-2] {
			nums[k] = x
			k++
		}
	}
	return k
}
```

#### TypeScript

```ts
function removeDuplicates(nums: number[]): number {
    let k = 0;
    for (const x of nums) {
        if (k < 2 || x !== nums[k - 2]) {
            nums[k++] = x;
        }
    }
    return k;
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
        let mut k = 0;
        for i in 0..nums.len() {
            if k < 2 || nums[i] != nums[k - 2] {
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
 * @return {number}
 */
var removeDuplicates = function (nums) {
    let k = 0;
    for (const x of nums) {
        if (k < 2 || x !== nums[k - 2]) {
            nums[k++] = x;
        }
    }
    return k;
};
```

#### C#

```cs
public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int k = 0;
        foreach (int x in nums) {
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
