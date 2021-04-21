# [26. 删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array)

[English Version](/solution/0000-0099/0026.Remove%20Duplicates%20from%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个有序数组 <code>nums</code> ，请你<strong><a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a></strong> 删除重复出现的元素，使每个元素 <strong>只出现一次</strong> ，返回删除后数组的新长度。</p>

<p>不要使用额外的数组空间，你必须在 <strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地 </a>修改输入数组 </strong>并在使用 O(1) 额外空间的条件下完成。</p>

<p> </p>

<p><strong>说明:</strong></p>

<p>为什么返回数值是整数，但输出的答案是数组呢?</p>

<p>请注意，输入数组是以<strong>「引用」</strong>方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。</p>

<p>你可以想象内部操作如下:</p>

<pre>
// <strong>nums</strong> 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中<strong> 该长度范围内</strong> 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
</pre>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2]
<strong>输出：</strong>2, nums = [1,2]
<strong>解释：</strong>函数应该返回新的长度 <strong><code>2</code></strong> ，并且原数组 <em>nums </em>的前两个元素被修改为 <strong><code>1</code></strong>, <strong><code>2 </code></strong><code>。</code>不需要考虑数组中超出新长度后面的元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,1,1,1,2,2,3,3,4]
<strong>输出：</strong>5, nums = [0,1,2,3,4]
<strong>解释：</strong>函数应该返回新的长度 <strong><code>5</code></strong> ， 并且原数组 <em>nums </em>的前五个元素被修改为 <strong><code>0</code></strong>, <strong><code>1</code></strong>, <strong><code>2</code></strong>, <strong><code>3</code></strong>, <strong><code>4</code></strong> 。不需要考虑数组中超出新长度后面的元素。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
	<li><code>nums</code> 已按升序排列</li>
</ul>

<p> </p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        cnt, n = 0, len(nums)
        for i in range(1, n):
            if nums[i] == nums[i - 1]:
                cnt += 1
            else:
                nums[i - cnt] = nums[i]
        return n - cnt
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int cnt = 0, n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1]) ++cnt;
            else nums[i - cnt] = nums[i];
        }
        return n - cnt;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
  let cnt = 0;
  const n = nums.length;
  for (let i = 1; i < n; ++i) {
    if (nums[i] == nums[i - 1]) ++cnt;
    else nums[i - cnt] = nums[i];
  }
  return n - cnt;
};
```

### **Go**

```go
func removeDuplicates(nums []int) int {
    cnt := 0
    n := len(nums)
    for i := 1; i < n; i++ {
        if nums[i] == nums[i - 1] {
            cnt++
        } else {
            nums[i - cnt] = nums[i]
        }
    }
    return n - cnt
}
```

### **C++**

```cpp
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int cnt = 0, n = nums.size();
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1]) ++cnt;
            else nums[i - cnt] = nums[i];
        }
        return n - cnt;
    }
};
```

### **C#**

```cs
public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int cnt = 0;
        int n = nums.Length;
        for (int i = 1; i < n; ++i)
        {
            if (nums[i] == nums[i - 1])
            {
                ++cnt;
            }
            else
            {
                nums[i - cnt] = nums[i];
            }
        }
        return n - cnt;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
