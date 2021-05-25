# [面试题 45. 把数组排成最小的数](https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

**示例 1:**

```
输入: [10,2]
输出: "102"
```

**示例  2:**

```
输入: [3,30,34,5,9]
输出: "3033459"
```

**提示:**

- `0 < nums.length <= 100`

**说明:**

- 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
- 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0。

## 解法

<!-- 这里可写通用的实现逻辑 -->

自定义排序比较器。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
import functools

class Solution:
    def minNumber(self, nums: List[int]) -> str:
        if not nums:
            return ''

        def compare(s1, s2):
            if s1 + s2 < s2 + s1:
                return -1
            if s1 + s2 > s2 + s1:
                return 1
            return 0

        return ''.join(sorted([str(x) for x in nums], key=functools.cmp_to_key(compare)))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        return Arrays.stream(nums).mapToObj(String::valueOf).sorted((s1, s2) -> (s1 + s2).compareTo(s2 + s1)).reduce((s1, s2) -> s1 + s2).get();
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {string}
 */
var minNumber = function (nums) {
  nums.sort((a, b) => {
    let s1 = a + "" + b;
    let s2 = b + "" + a;
    if (s1 < s2) {
      return -1;
    } else return 1;
  });
  return nums.join("");
};
```

### **C++**

```cpp
class Solution {
public:
    string minNumber(vector<int>& nums) {
        int n = nums.size();
        vector<string> strs(n);
        for (int i = 0; i < n; ++i) {
            strs[i] = to_string(nums[i]);
        }
        sort(strs.begin(), strs.end(), [](const string& s1, const string& s2) {
            return s1 + s2 < s2 + s1;
        });
        string ans;
        for (int i = 0; i < n; ++i) {
            ans += strs[i];
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
