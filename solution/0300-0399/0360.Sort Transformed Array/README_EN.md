# [360. Sort Transformed Array](https://leetcode.com/problems/sort-transformed-array)

[中文文档](/solution/0300-0399/0360.Sort%20Transformed%20Array/README.md)

## Description

<p>Given a <strong>sorted</strong> integer array <code>nums</code> and three integers <code>a</code>, <code>b</code> and <code>c</code>, apply a quadratic function of the form <code>f(x) = ax<sup>2</sup> + bx + c</code> to each element <code>nums[i]</code> in the array, and return <em>the array in a sorted order</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [-4,-2,2,4], a = 1, b = 3, c = 5
<strong>Output:</strong> [3,9,15,33]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [-4,-2,2,4], a = -1, b = 3, c = 5
<strong>Output:</strong> [-23,-5,1,7]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>-100 &lt;= nums[i], a, b, c &lt;= 100</code></li>
	<li><code>nums</code> is sorted in <strong>ascending</strong> order.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it in <code>O(n)</code> time?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortTransformedArray(
        self, nums: List[int], a: int, b: int, c: int
    ) -> List[int]:
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
    vector<int> sortTransformedArray(vector<int>& nums, int a, int b, int c) {
        int n = nums.size();
        int i = 0, j = n - 1, k = a < 0 ? 0 : n - 1;
        vector<int> res(n);
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
