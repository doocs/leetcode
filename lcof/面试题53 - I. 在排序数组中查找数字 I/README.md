# [面试题53 - I. 在排序数组中查找数字 I](https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)

## 题目描述
统计一个数字在排序数组中出现的次数。

**示例 1:**

```
输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
```

**示例 2:**

```
输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
```

**限制：**

- `0 <= 数组长度 <= 50000`

## 解法
### Python3
```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if not nums:
            return 0
        l, r = 0, len(nums) - 1
        while l <= r:
            m = l + ((r - l) >> 1)
            if nums[m] == target:
                return self._count(nums, l, r, m)
            if nums[m] < target:
                l = m + 1
            else:
                r = m - 1
        return 0
    
    def _count(self, nums, l, r, m) -> int:
        cnt = 0
        for i in range(m, l - 1, -1):
            if nums[i] == nums[m]:
                cnt += 1
            elif nums[i] < nums[m]:
                break
        
        for i in range(m + 1, r + 1):
            if nums[i] == nums[m]:
                cnt += 1
            elif nums[i] > nums[m]:
                break
        return cnt
```

### Java
```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                return count(nums, l, r, m);
            }
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return 0;
    }

    private int count(int[] nums, int l, int r, int m) {
        int cnt = 0;
        for (int i = m; i >= l; --i) {
            if (nums[i] == nums[m]) {
                ++cnt;
            } else if (nums[i] < nums[m]) {
                break;
            }
        }
        for (int i = m + 1; i <= r; ++i) {
            if (nums[i] == nums[m]) {
                ++cnt;
            } else if (nums[i] > nums[m]) {
                break;
            }
        }
        return cnt;
    }
}
```

### JavaScript
```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function(nums, target) {
    if(!nums || !nums.length) return 0
    let left = 0
    let right = nums.length - 1
    let res = 0
    while(left < right) {
        let mid = left + ~~((right-left)/2)
        if(nums[mid] < target) {
            left = mid + 1
        } else if(nums[mid] > target) {
            right = mid
        } else {
            left = mid
            right = mid
            break
        }
    }
    while(nums[left] === target) {
        res++
        left--
    }
    while(nums[++right] === target) {
        res++
    }
    return res

};
```

### ...
```

```
