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

**方法一：枚举 + 二分查找**

根据题目我们可以知道，函数 $f(x, y)$ 是单调递增函数，因此，我们可以枚举 $x$，然后在 $[1,...z]$ 中二分查找 $y$，使得 $f(x, y) = z$。如果找到了，就将 $(x, y)$ 加入答案中。

时间复杂度 $(n \log n)$，空间复杂度 $O(1)$。

**方法二：双指针**

我们可以定义两个指针 $x$ 和 $y$，初始时 $x = 1$, $y = z$。

-   如果 $f(x, y) = z$，我们将 $(x, y)$ 加入答案中，然后 $x \leftarrow x + 1$, $y \leftarrow y - 1$；
-   如果 $f(x, y) \lt z$，此时对任意的 $y' \lt y$，都有 $f(x, y') \lt f(x, y) \lt z$，因此我们不能将 $y$ 减小，只能将 $x$ 增大，所以 $x \leftarrow x + 1$；
-   如果 $f(x, y) \gt z$，此时对任意的 $x' \gt x$，都有 $f(x', y) \gt f(x, y) \gt z$，因此我们不能将 $x$ 增大，只能将 $y$ 减小，所以 $y \leftarrow y - 1$。

循环结束后，返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

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
    def findSolution(self, customfunction: "CustomFunction", z: int) -> List[List[int]]:
        ans = []
        for x in range(1, z + 1):
            y = 1 + bisect_left(range(1, z + 1), z, key=lambda y: customfunction.f(x, y))
            if customfunction.f(x, y) == z:
                ans.append([x, y])
        return ans
```

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
    def findSolution(self, customfunction: "CustomFunction", z: int) -> List[List[int]]:
        ans = []
        x, y = 1, 1000
        while x <= 1000 and y:
            t = customfunction.f(x, y)
            if t < z:
                x += 1
            elif t > z:
                y -= 1
            else:
                ans.append([x, y])
                x, y = x + 1, y - 1
        return ans
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
        List<List<Integer>> ans = new ArrayList<>();
        for (int x = 1; x <= 1000; ++x) {
            int l = 1, r = 1000;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (customfunction.f(x, mid) >= z) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (customfunction.f(x, l) == z) {
                ans.add(Arrays.asList(x, l));
            }
        }
        return ans;
    }
}
```

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
        List<List<Integer>> ans = new ArrayList<>();
        int x = 1, y = 1000;
        while (x <= 1000 && y > 0) {
            int t = customfunction.f(x, y);
            if (t < z) {
                x++;
            } else if (t > z) {
                y--;
            } else {
                ans.add(Arrays.asList(x++, y--));
            }
        }
        return ans;
    }
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
        vector<vector<int>> ans;
        for (int x = 1; x <= 1000; ++x) {
            int l = 1, r = 1000;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (customfunction.f(x, mid) >= z) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (customfunction.f(x, l) == z) {
                ans.push_back({x, l});
            }
        }
        return ans;
    }
};
```

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
        vector<vector<int>> ans;
        int x = 1, y = 1000;
        while (x <= 1000 && y) {
            int t = customfunction.f(x, y);
            if (t < z) {
                x++;
            } else if (t > z) {
                y--;
            } else {
                ans.push_back({x++, y--});
            }
        }
        return ans;
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

func findSolution(customFunction func(int, int) int, z int) (ans [][]int) {
	for x := 1; x <= 1000; x++ {
		y := 1 + sort.Search(999, func(y int) bool { return customFunction(x, y+1) >= z })
		if customFunction(x, y) == z {
			ans = append(ans, []int{x, y})
		}
	}
	return
}
```

```go
/**
 * This is the declaration of customFunction API.
 * @param  x    int
 * @param  x    int
 * @return 	    Returns f(x, y) for any given positive integers x and y.
 *			    Note that f(x, y) is increasing with respect to both x and y.
 *              i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 */

func findSolution(customFunction func(int, int) int, z int) (ans [][]int) {
	x, y := 1, 1000
	for x <= 1000 && y > 0 {
		t := customFunction(x, y)
		if t < z {
			x++
		} else if t > z {
			y--
		} else {
			ans = append(ans, []int{x, y})
			x, y = x+1, y-1
		}
	}
	return
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
    const ans: number[][] = [];
    for (let x = 1; x <= 1000; ++x) {
        let l = 1;
        let r = 1000;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (customfunction.f(x, mid) >= z) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (customfunction.f(x, l) == z) {
            ans.push([x, l]);
        }
    }
    return ans;
}
```

```ts
/**
 * // This is the CustomFunction's API interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *      f(x: number, y: number): number {}
 * }
 */

function findSolution(customfunction: CustomFunction, z: number): number[][] {
    let x = 1;
    let y = 1000;
    const ans: number[][] = [];
    while (x <= 1000 && y) {
        const t = customfunction.f(x, y);
        if (t < z) {
            ++x;
        } else if (t > z) {
            --y;
        } else {
            ans.push([x--, y--]);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
