# [面试题11. 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

## 题目描述
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 `[3,4,5,1,2]` 为 `[1,2,3,4,5]` 的一个旋转，该数组的最小值为 1。  

**示例 1：**

```
输入：[3,4,5,1,2]
输出：1
```

**示例 2：**

```
输入：[2,2,2,0,1]
输出：0
```

## 解法
### Python3
```python
class Solution:
    def minArray(self, numbers: List[int]) -> int:
        if len(numbers) == 1 or numbers[0] < numbers[-1]:
            return numbers[0]
        left, right = 0, len(numbers) - 1
        while (right - left > 1):
            mid = left + ((right - left) >> 1)
            if numbers[mid] == numbers[left] == numbers[right]:
                return min(numbers[left: right + 1])
            if numbers[mid] >= numbers[left]:
                left = mid
            elif numbers[mid] <= numbers[right]:
                right = mid
        return numbers[right]
```

### Java
```java
class Solution {
    public int minArray(int[] numbers) {
        int len = numbers.length;
        if (len == 1 || numbers[0] < numbers[len - 1]) {
            return numbers[0];
        }

        int left = 0, right = len - 1;
        while (right - left > 1) {
            int mid = left + ((right - left) >> 1);
            if (numbers[left] == numbers[mid] && numbers[mid] == numbers[right]) {
                return findMin(numbers, left, right);
            }
            if (numbers[mid] >= numbers[left]) {
                left = mid;
            } else if (numbers[mid] <= numbers[right]) {
                right = mid;
            }
        }
        return numbers[right];
    }

    private int findMin(int[] numbers, int left, int right) {
        int min = numbers[left];
        for (int i = left + 1; i < right; ++i) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }
}
```

### JavaScript
```js
/**
 * @param {number[]} numbers
 * @return {number}
 */
var minArray = function(numbers) {
    // return Math.min(...numbers)
    let left = 0
    let right = numbers.length-1
    while(left < right) {
        let mid = left + ~~((right - left)/2)
        if(numbers[mid] > numbers[right]) {
            left = mid + 1
        } else if(numbers[mid] === numbers[right]) {
            right--
        } else {
            right = mid
        }
    }
    return numbers[left]
};
```

### ...
```

```
