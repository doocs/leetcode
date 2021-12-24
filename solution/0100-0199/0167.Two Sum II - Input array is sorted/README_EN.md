# [167. Two Sum II - Input array is sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted)

[中文文档](/solution/0100-0199/0167.Two%20Sum%20II%20-%20Input%20array%20is%20sorted/README.md)

## Description

<p>Given an array of integers <code>numbers</code> that is already <strong><em>sorted in ascending order</em></strong>, find two numbers such that they add up to a specific <code>target</code> number.</p>

<p>Return the indices of the two numbers (<strong>1-indexed</strong>) as an integer array <code>answer</code> of size <code>2</code>, where <code>1 &lt;= answer[0] &lt; answer[1] &lt;= numbers.length</code>.</p>

<p>You may assume that each input would have <strong>exactly one solution</strong> and you <strong>may not</strong> use the same element twice.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> numbers = [2,7,11,15], target = 9
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> numbers = [2,3,4], target = 6
<strong>Output:</strong> [1,3]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> numbers = [-1,0], target = -1
<strong>Output:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> is sorted in <strong>increasing order</strong>.</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li><strong>Only one valid answer exists.</strong></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        left, right = 0, len(numbers) - 1
        while left < right:
            if numbers[left] + numbers[right] == target:
                return [left + 1, right + 1]
            if numbers[left] + numbers[right] < target:
                left += 1
            else:
                right -= 1
        return [-1, -1]
```

### **Java**

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{left + 1, right + 1};
            }
            if (numbers[left] + numbers[right] < target) {
                ++left;
            } else {
                --right;
            }
        }
        return new int[]{-1, -1};
    }
}
```

### **TypeScript**

```ts
function twoSum(numbers: number[], target: number): number[] {
    for (let right = numbers.length - 1; right >= 0; --right) {
        let left = numbers.indexOf(target - numbers[right]);
        if (left > -1 && left < right) {
            return [left + 1, right + 1];
        }
    }
    return [-1, -1];
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int left = 0, right = numbers.size() - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return {left + 1, right + 1};
            }
            if (numbers[left] + numbers[right] < target) ++left;
            else --right;
        }
        return {-1, -1};
    }
};
```

### **Go**

```go
func twoSum(numbers []int, target int) []int {
	left, right := 0, len(numbers)-1
	for left < right {
		if numbers[left]+numbers[right] == target {
			return []int{left + 1, right + 1}
		}
		if numbers[left]+numbers[right] < target {
			left++
		} else {
			right--
		}
	}
	return []int{-1, -1}
}
```

### **...**

```

```

<!-- tabs:end -->
