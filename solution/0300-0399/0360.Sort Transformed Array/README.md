# [360. 有序转化数组](https://leetcode-cn.com/problems/sort-transformed-array)

[English Version](/solution/0300-0399/0360.Sort%20Transformed%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个已经<strong>&nbsp;排好序</strong>&nbsp;的整数数组&nbsp;nums&nbsp;和整数&nbsp;a、b、c。对于数组中的每一个数 x，计算函数值&nbsp;f(<em>x</em>) = <em>ax</em><sup>2</sup> + <em>bx</em> + c，请将函数值产生的数组返回。</p>

<p>要注意，返回的这个数组必须按照 <strong>升序排列</strong>，并且我们所期望的解法时间复杂度为&nbsp;<strong>O(<em>n</em>)</strong>。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>nums = [-4,-2,2,4], a = 1, b = 3, c = 5
<strong>输出: </strong>[3,9,15,33]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong>nums = [-4,-2,2,4], a = -1, b = 3, c = 5
<strong>输出: </strong>[-23,-5,1,7]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针。

利用抛物线的性质，i，j 分别指向排序数组首尾，从两端向中间夹逼。

- 若 `a > 0`，抛物线向上，两端具有最大值，比较两端点的较大值，添加到结果数组。
- 若 `a < 0`，抛物线向下，两端具有最小值，比较两端点的较小值，添加到结果数组。
- 若 `a == 0`，合并到以上的任意一种情况均可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortTransformedArray(self, nums: List[int], a: int, b: int, c: int) -> List[int]:
        def f(x):
            return a * x * x + b * x + c

        n = len(nums)
        i, j, k = 0, n - 1, 0 if a < 0 else n - 1
        res = [0] * n
        while i <= j:
            v1, v2 = f(nums[i]), f(nums[j])
            if a < 0:
                if v1 <= v2:
                    res[k] = v1
                    i += 1
                else:
                    res[k] = v2
                    j -= 1
                k += 1
            else:
                if v1 >= v2:
                    res[k] = v1
                    i += 1
                else:
                    res[k] = v2
                    j -= 1
                k -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int i = 0, j = n - 1, k = a < 0 ? 0 : n - 1;
        int[] res = new int[n];
        while (i <= j) {
            int v1 = f(a, b, c, nums[i]), v2 = f(a, b, c, nums[j]);
            if (a < 0) {
                if (v1 <= v2) {
                    res[k] = v1;
                    ++i;
                } else {
                    res[k] = v2;
                    --j;
                }
                ++k;
            } else {
                if (v1 >= v2) {
                    res[k] = v1;
                    ++i;
                } else {
                    res[k] = v2;
                    --j;
                }
                --k;
            }
        }
        return res;
    }

    private int f(int a, int b, int c, int x) {
        return a * x * x + b * x + c;
    }
}
```

### **C++**

```cpp
class Solution {
public:
	vector<int> sortTransformedArray(vector<int> &nums, int a, int b, int c) {
		int n = nums.size();
		int i = 0, j = n - 1, k = a < 0 ? 0 : n - 1;
		vector<int> res(n);
		while (i <= j)
		{
			int v1 = f(a, b, c, nums[i]), v2 = f(a, b, c, nums[j]);
			if (a < 0)
			{
				if (v1 <= v2)
				{
					res[k] = v1;
					++i;
				}
				else
				{
					res[k] = v2;
					--j;
				}
				++k;
			}
			else
			{
				if (v1 >= v2)
				{
					res[k] = v1;
					++i;
				}
				else
				{
					res[k] = v2;
					--j;
				}
				--k;
			}
		}
		return res;
	}

	int f(int a, int b, int c, int x) {
		return a * x * x + b * x + c;
	}
};
```

### **Go**

```go
func sortTransformedArray(nums []int, a int, b int, c int) []int {
	n := len(nums)
	i, j, k := 0, n-1, 0
	if a >= 0 {
		k = n - 1
	}
	res := make([]int, n)
	for i <= j {
		v1, v2 := f(a, b, c, nums[i]), f(a, b, c, nums[j])
		if a < 0 {
			if v1 <= v2 {
				res[k] = v1
				i++
			} else {
				res[k] = v2
				j--
			}
			k++
		} else {
			if v1 >= v2 {
				res[k] = v1
				i++
			} else {
				res[k] = v2
				j--
			}
			k--
		}
	}
	return res
}

func f(a, b, c, x int) int {
	return a*x*x + b*x + c
}
```

### **...**

```

```

<!-- tabs:end -->
