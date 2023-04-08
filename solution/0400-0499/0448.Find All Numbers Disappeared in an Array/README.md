# [448. 找到所有数组中消失的数字](https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array)

[English Version](/solution/0400-0499/0448.Find%20All%20Numbers%20Disappeared%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个含 <code>n</code> 个整数的数组 <code>nums</code> ，其中 <code>nums[i]</code> 在区间 <code>[1, n]</code> 内。请你找出所有在 <code>[1, n]</code> 范围内但没有出现在 <code>nums</code> 中的数字，并以数组的形式返回结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,3,2,7,8,2,3,1]
<strong>输出：</strong>[5,6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>[2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= n</code></li>
</ul>

<p><strong>进阶：</strong>你能在不使用额外空间且时间复杂度为<em> </em><code>O(n)</code><em> </em>的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

我们可以使用数组或哈希表记录数组中的数字，然后遍历 `[1, n]` 区间内的数字，若数字不存在于数组或哈希表中，则说明数组中缺失该数字，将其添加到结果列表中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

**方法二：原地修改**

我们可以遍历数组 $nums$，将 $|nums[i]|-1$ 位置的数字标记为负数，表示数组 $nums[i]$ 出现过。最后遍历数组 $nums$，若 $nums[i]$ 为正数，则说明数组中缺失 $i+1$，将其添加到结果列表中。

遍历结束后，返回结果列表即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        s = set(nums)
        return [x for x in range(1, len(nums) + 1) if x not in s]
```

```python
class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        for x in nums:
            i = abs(x) - 1
            if nums[i] > 0:
                nums[i] *= -1
        return [i + 1 for i in range(len(nums)) if nums[i] > 0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        boolean[] s = new boolean[n + 1];
        for (int x : nums) {
            s[x] = true;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!s[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int x : nums) {
            int i = Math.abs(x) - 1;
            if (nums[i] > 0) {
                nums[i] *= -1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int n = nums.size();
        bool s[n + 1];
        memset(s, false, sizeof(s));
        for (int& x : nums) {
            s[x] = true;
        }
        vector<int> ans;
        for (int i = 1; i <= n; ++i) {
            if (!s[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int n = nums.size();
        for (int& x : nums) {
            int i = abs(x) - 1;
            if (nums[i] > 0) {
                nums[i] = -nums[i];
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findDisappearedNumbers(nums []int) (ans []int) {
	n := len(nums)
	s := make([]bool, n+1)
	for _, x := range nums {
		s[x] = true
	}
	for i := 1; i <= n; i++ {
		if !s[i] {
			ans = append(ans, i)
		}
	}
	return
}
```

```go
func findDisappearedNumbers(nums []int) (ans []int) {
	n := len(nums)
	for _, x := range nums {
		i := abs(x) - 1
		if nums[i] > 0 {
			nums[i] = -nums[i]
		}
	}
	for i := 0; i < n; i++ {
		if nums[i] > 0 {
			ans = append(ans, i+1)
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function findDisappearedNumbers(nums: number[]): number[] {
    const n = nums.length;
    const s: boolean[] = new Array(n + 1).fill(false);
    for (const x of nums) {
        s[x] = true;
    }
    const ans: number[] = [];
    for (let i = 1; i <= n; ++i) {
        if (!s[i]) {
            ans.push(i);
        }
    }
    return ans;
}
```

```ts
function findDisappearedNumbers(nums: number[]): number[] {
    const n = nums.length;
    for (const x of nums) {
        const i = Math.abs(x) - 1;
        if (nums[i] > 0) {
            nums[i] *= -1;
        }
    }
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (nums[i] > 0) {
            ans.push(i + 1);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
