# [1237. 找出给定方程的正整数解](https://leetcode.cn/problems/find-positive-integer-solution-for-a-given-equation)

[English Version](/solution/1200-1299/1237.Find%20Positive%20Integer%20Solution%20for%20a%20Given%20Equation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个函数  <code>f(x, y)</code> 和一个目标结果 <code>z</code>，函数公式未知，请你计算方程 <code>f(x,y) == z</code> 所有可能的正整数 <strong>数对</strong> <code>x</code> 和 <code>y</code>。满足条件的结果数对可以按任意顺序返回。</p>

<p>尽管函数的具体式子未知，但它是单调递增函数，也就是说：</p>

<ul>
	<li><code>f(x, y) < f(x + 1, y)</code></li>
	<li><code>f(x, y) < f(x, y + 1)</code></li>
</ul>

<p>函数接口定义如下：</p>

<pre>
interface CustomFunction {
public:
  // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
  int f(int x, int y);
};</pre>

<p>你的解决方案将按如下规则进行评判：</p>

<ul>
	<li>判题程序有一个由 <code>CustomFunction</code> 的 <code>9</code> 种实现组成的列表，以及一种为特定的 <code>z</code> 生成所有有效数对的答案的方法。</li>
	<li>判题程序接受两个输入：<code>function_id</code>（决定使用哪种实现测试你的代码）以及目标结果 <code>z</code> 。</li>
	<li>判题程序将会调用你实现的 <code>findSolution</code> 并将你的结果与答案进行比较。</li>
	<li>如果你的结果与答案相符，那么解决方案将被视作正确答案，即 <code>Accepted</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>function_id = 1, z = 5
<strong>输出：</strong>[[1,4],[2,3],[3,2],[4,1]]
<strong>解释：</strong>function_id = 1 暗含的函数式子为 f(x, y) = x + y
以下 x 和 y 满足 f(x, y) 等于 5：
x=1, y=4 -> f(1, 4) = 1 + 4 = 5
x=2, y=3 -> f(2, 3) = 2 + 3 = 5
x=3, y=2 -> f(3, 2) = 3 + 2 = 5
x=4, y=1 -> f(4, 1) = 4 + 1 = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>function_id = 2, z = 5
<strong>输出：</strong>[[1,5],[5,1]]
<strong>解释：</strong>function_id = 2 暗含的函数式子为 f(x, y) = x * y
以下 x 和 y 满足 f(x, y) 等于 5：
x=1, y=5 -> f(1, 5) = 1 * 5 = 5
x=5, y=1 -> f(5, 1) = 5 * 1 = 5</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= function_id <= 9</code></li>
	<li><code>1 <= z <= 100</code></li>
	<li>题目保证 <code>f(x, y) == z</code> 的解处于 <code>1 <= x, y <= 1000</code> 的范围内。</li>
	<li>在 <code>1 <= x, y <= 1000</code> 的前提下，题目保证 <code>f(x, y)</code> 是一个 32 位有符号整数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
"""
   This is the custom function interface.
   You should not implement it, or speculate about its implementation
   class CustomFunction:
       # Returns f(x, y) for any given positive integers x and y.
       # Note that f(x, y) is increasing with respect to both x and y.
       # i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
       def f(self, x, y):

"""


class Solution:
    def findSolution(self, customfunction: 'CustomFunction', z: int) -> List[List[int]]:
        res = []
        for x in range(1, 1001):
            left, right = 1, 1000
            while left < right:
                mid = (left + right) >> 1
                if customfunction.f(x, mid) >= z:
                    right = mid
                else:
                    left = mid + 1
            if customfunction.f(x, left) == z:
                res.append([x, left])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/*
 * // This is the custom function interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *     // Returns f(x, y) for any given positive integers x and y.
 *     // Note that f(x, y) is increasing with respect to both x and y.
 *     // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 *     public int f(int x, int y);
 * };
 */

class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= 1000; ++i) {
            int left = 1, right = 1000;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (customfunction.f(i, mid) >= z) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (customfunction.f(i, left) == z) {
                res.add(Arrays.asList(i, left));
            }
        }
        return res;
    }
}
```

### **TypeScript**

```ts
/**
 * // This is the CustomFunction's API interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *      f(x: number, y: number): number {}
 * }
 */

function findSolution(customfunction: CustomFunction, z: number): number[][] {
    // 二分
    let ans = [];
    for (let i = 1; i <= 1000; i++) {
        let left = 1,
            right = 1000;
        while (left < right) {
            let mid = (left + right) >> 1;
            if (customfunction.f(i, mid) >= z) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (customfunction.f(i, left) == z) {
            ans.push([i, left]);
        }
    }
    return ans;
}
```

### **C++**

```cpp
/*
 * // This is the custom function interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 * public:
 *     // Returns f(x, y) for any given positive integers x and y.
 *     // Note that f(x, y) is increasing with respect to both x and y.
 *     // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 *     int f(int x, int y);
 * };
 */

class Solution {
public:
    vector<vector<int>> findSolution(CustomFunction& customfunction, int z) {
        vector<vector<int>> res;
        for (int i = 1; i <= 1000; ++i) {
            int left = 1, right = 1000;
            while (left < right) {
                int mid = left + right >> 1;
                if (customfunction.f(i, mid) >= z) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (customfunction.f(i, left) == z) {
                res.push_back({i, left});
            }
        }
        return res;
    }
};
```

### **Go**

```go
/**
 * This is the declaration of customFunction API.
 * @param  x    int
 * @param  x    int
 * @return 	    Returns f(x, y) for any given positive integers x and y.
 *			    Note that f(x, y) is increasing with respect to both x and y.
 *              i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 */

func findSolution(customFunction func(int, int) int, z int) [][]int {
	res := [][]int{}
	for i := 1; i <= 1000; i++ {
		left, right := 1, 1000
		for left < right {
			mid := (left + right) >> 1
			if customFunction(i, mid) >= z {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if customFunction(i, left) == z {
			res = append(res, []int{i, left})
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
