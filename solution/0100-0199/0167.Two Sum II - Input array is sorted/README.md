# [167. 两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted)

[English Version](/solution/0100-0199/0167.Two%20Sum%20II%20-%20Input%20array%20is%20sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个已按照<strong><em>升序排列</em>&nbsp;</strong>的有序数组，找到两个数使得它们相加之和等于目标数。</p>

<p>函数应该返回这两个下标值<em> </em>index1 和 index2，其中 index1&nbsp;必须小于&nbsp;index2<em>。</em></p>

<p><strong>说明:</strong></p>

<ul>
	<li>返回的下标值（index1 和 index2）不是从零开始的。</li>
	<li>你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。</li>
</ul>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> numbers = [2, 7, 11, 15], target = 9
<strong>输出:</strong> [1,2]
<strong>解释:</strong> 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针解决。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        low, high = 0, len(numbers) - 1
        while low <= high:
            if numbers[low] + numbers[high] == target:
                return [low + 1, high + 1]
            if numbers[low] + numbers[high] < target:
                low += 1
            else:
                high -= 1
        return [-1, -1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low <= high) {
            if (numbers[low] + numbers[high] == target) {
                return new int[]{low + 1, high + 1};
            }
            if (numbers[low] + numbers[high] < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int low = 0, high = numbers.size() - 1;
        while (low <= high) {
            if (numbers[low] + numbers[high] == target) {
                return {low + 1, high + 1};
            }
            if (numbers[low] + numbers[high] < target) {
                ++low;
            } else {
                --high;
            }
        }
        return {-1, -1};
    }
};
```

### **...**

```

```

<!-- tabs:end -->
