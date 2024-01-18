# [964. Least Operators to Express Number](https://leetcode.com/problems/least-operators-to-express-number)

[中文文档](/solution/0900-0999/0964.Least%20Operators%20to%20Express%20Number/README.md)

## Description

<p>Given a single positive integer <code>x</code>, we will write an expression of the form <code>x (op1) x (op2) x (op3) x ...</code> where each operator <code>op1</code>, <code>op2</code>, etc. is either addition, subtraction, multiplication, or division (<code>+</code>, <code>-</code>, <code>*</code>, or <code>/)</code>. For example, with <code>x = 3</code>, we might write <code>3 * 3 / 3 + 3 - 3</code> which is a value of <font face="monospace">3</font>.</p>

<p>When writing such an expression, we adhere to the following conventions:</p>

<ul>
	<li>The division operator (<code>/</code>) returns rational numbers.</li>
	<li>There are no parentheses placed anywhere.</li>
	<li>We use the usual order of operations: multiplication and division happen before addition and subtraction.</li>
	<li>It is not allowed to use the unary negation operator (<code>-</code>). For example, &quot;<code>x - x</code>&quot; is a valid expression as it only uses subtraction, but &quot;<code>-x + x</code>&quot; is not because it uses negation.</li>
</ul>

<p>We would like to write an expression with the least number of operators such that the expression equals the given <code>target</code>. Return the least number of operators used.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 3, target = 19
<strong>Output:</strong> 5
<strong>Explanation:</strong> 3 * 3 + 3 * 3 + 3 / 3.
The expression contains 5 operations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 5, target = 501
<strong>Output:</strong> 8
<strong>Explanation:</strong> 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.
The expression contains 8 operations.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> x = 100, target = 100000000
<strong>Output:</strong> 3
<strong>Explanation:</strong> 100 * 100 * 100 * 100.
The expression contains 3 operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= x &lt;= 100</code></li>
	<li><code>1 &lt;= target &lt;= 2 * 10<sup>8</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def leastOpsExpressTarget(self, x: int, target: int) -> int:
        @cache
        def dfs(v: int) -> int:
            if x >= v:
                return min(v * 2 - 1, 2 * (x - v))
            k = 2
            while x**k < v:
                k += 1
            if x**k - v < v:
                return min(k + dfs(x**k - v), k - 1 + dfs(v - x ** (k - 1)))
            return k - 1 + dfs(v - x ** (k - 1))

        return dfs(target)
```

```java
class Solution {
    private int x;
    private Map<Integer, Integer> f = new HashMap<>();

    public int leastOpsExpressTarget(int x, int target) {
        this.x = x;
        return dfs(target);
    }

    private int dfs(int v) {
        if (x >= v) {
            return Math.min(v * 2 - 1, 2 * (x - v));
        }
        if (f.containsKey(v)) {
            return f.get(v);
        }
        int k = 2;
        long y = (long) x * x;
        while (y < v) {
            y *= x;
            ++k;
        }
        int ans = k - 1 + dfs(v - (int) (y / x));
        if (y - v < v) {
            ans = Math.min(ans, k + dfs((int) y - v));
        }
        f.put(v, ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int leastOpsExpressTarget(int x, int target) {
        unordered_map<int, int> f;
        function<int(int)> dfs = [&](int v) -> int {
            if (x >= v) {
                return min(v * 2 - 1, 2 * (x - v));
            }
            if (f.count(v)) {
                return f[v];
            }
            int k = 2;
            long long y = x * x;
            while (y < v) {
                y *= x;
                ++k;
            }
            int ans = k - 1 + dfs(v - y / x);
            if (y - v < v) {
                ans = min(ans, k + dfs(y - v));
            }
            f[v] = ans;
            return ans;
        };
        return dfs(target);
    }
};
```

```go
func leastOpsExpressTarget(x int, target int) int {
	f := map[int]int{}
	var dfs func(int) int
	dfs = func(v int) int {
		if x > v {
			return min(v*2-1, 2*(x-v))
		}
		if val, ok := f[v]; ok {
			return val
		}
		k := 2
		y := x * x
		for y < v {
			y *= x
			k++
		}
		ans := k - 1 + dfs(v-y/x)
		if y-v < v {
			ans = min(ans, k+dfs(y-v))
		}
		f[v] = ans
		return ans
	}
	return dfs(target)
}
```

```ts
function leastOpsExpressTarget(x: number, target: number): number {
    const f: Map<number, number> = new Map();
    const dfs = (v: number): number => {
        if (x > v) {
            return Math.min(v * 2 - 1, 2 * (x - v));
        }
        if (f.has(v)) {
            return f.get(v)!;
        }
        let k = 2;
        let y = x * x;
        while (y < v) {
            y *= x;
            ++k;
        }
        let ans = k - 1 + dfs(v - Math.floor(y / x));
        if (y - v < v) {
            ans = Math.min(ans, k + dfs(y - v));
        }
        f.set(v, ans);
        return ans;
    };
    return dfs(target);
}
```

<!-- tabs:end -->

<!-- end -->
