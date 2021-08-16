# [448. 找到所有数组中消失的数字](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array)

[English Version](/solution/0400-0499/0448.Find%20All%20Numbers%20Disappeared%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个范围在&nbsp; 1 &le; a[i] &le; <em>n</em> (&nbsp;<em>n</em> = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。</p>

<p>找到所有在 [1, <em>n</em>] 范围之间没有出现在数组中的数字。</p>

<p>您能在不使用额外空间且时间复杂度为<em>O(n)</em>的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。</p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入:</strong>
[4,3,2,7,8,2,3,1]

<strong>输出:</strong>
[5,6]
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

- 遍历输入数组的每个元素一次。
- 把 `abs(nums[i]) - 1` 索引位置的元素标记为负数。即 `nums[abs(nums[i]) - 1] *= -1`。
- 然后遍历数组，若当前数组元素 `nums[i]` 为负数，说明我们在数组中存在数字 `i+1`。否则，说明数组不存在数字 `i+1`，添加到结果列表中。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        for num in nums:
            idx = abs(num) - 1
            if nums[idx] > 0:
                nums[idx] *= -1
        return [i + 1 for i, v in enumerate(nums) if v > 0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) {
                nums[idx] *= -1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function findDisappearedNumbers(nums: number[]): number[] {
    for (let i = 0; i < nums.length; i++) {
        let idx = Math.abs(nums[i]) - 1;
        if (nums[idx] > 0) {
            nums[idx] *= -1;
        }
    }
    let ans = [];
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            ans.push(i + 1);
        }
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int> &nums) {
        int n = nums.size();
        for (int i = 0; i < n; ++i)
        {
            int idx = abs(nums[i]) - 1;
            if (nums[idx] > 0)
                nums[idx] *= -1;
        }
        vector<int> res;
        for (int i = 0; i < n; ++i)
        {
            if (nums[i] > 0)
                res.push_back(i + 1);
        }
        return res;
    }
};
```

### **Go**

```go
func findDisappearedNumbers(nums []int) []int {
	for _, num := range nums {
		idx := abs(num) - 1
		if nums[idx] > 0 {
			nums[idx] *= -1
		}
	}
	var res []int
	for i, num := range nums {
		if num > 0 {
			res = append(res, i+1)
		}
	}
	return res
}

func abs(a int) int {
	if a > 0 {
		return a
	}
	return -a
}
```

### **...**

```

```

<!-- tabs:end -->
