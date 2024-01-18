# [2782. Number of Unique Categories](https://leetcode.com/problems/number-of-unique-categories)

[中文文档](/solution/2700-2799/2782.Number%20of%20Unique%20Categories/README.md)

## Description

<p>You are given an integer <code>n</code> and an object <code>categoryHandler</code> of class <code>CategoryHandler</code>.</p>

<p>There are <code>n&nbsp;</code>elements, numbered from <code>0</code> to <code>n - 1</code>. Each element has a category, and your task is to find the number of unique categories.</p>

<p>The class <code>CategoryHandler</code> contains the following function, which may help you:</p>

<ul>
	<li><code>boolean haveSameCategory(integer a, integer b)</code>: Returns <code>true</code> if <code>a</code> and <code>b</code> are in the same category and <code>false</code> otherwise. Also, if either <code>a</code> or <code>b</code> is not a valid number (i.e. it&#39;s greater than or equal to <code>n</code>or less than <code>0</code>), it returns <code>false</code>.</li>
</ul>

<p>Return <em>the number of unique categories.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6, categoryHandler = [1,1,2,2,3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 6 elements in this example. The first two elements belong to category 1, the second two belong to category 2, and the last two elements belong to category 3. So there are 3 unique categories.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, categoryHandler = [1,2,3,4,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 elements in this example. Each element belongs to a unique category. So there are 5 unique categories.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, categoryHandler = [1,1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There are 3 elements in this example. All of them belong to one category. So there is only 1 unique category.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
# Definition for a category handler.
# class CategoryHandler:
#     def haveSameCategory(self, a: int, b: int) -> bool:
#         pass
class Solution:
    def numberOfCategories(
        self, n: int, categoryHandler: Optional['CategoryHandler']
    ) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for a in range(n):
            for b in range(a + 1, n):
                if categoryHandler.haveSameCategory(a, b):
                    p[find(a)] = find(b)
        return sum(i == x for i, x in enumerate(p))
```

```java
/**
 * Definition for a category handler.
 * class CategoryHandler {
 *     public CategoryHandler(int[] categories);
 *     public boolean haveSameCategory(int a, int b);
 * };
 */
class Solution {
    private int[] p;

    public int numberOfCategories(int n, CategoryHandler categoryHandler) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int a = 0; a < n; ++a) {
            for (int b = a + 1; b < n; ++b) {
                if (categoryHandler.haveSameCategory(a, b)) {
                    p[find(a)] = find(b);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i == p[i]) {
                ++ans;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
/**
 * Definition for a category handler.
 * class CategoryHandler {
 * public:
 *     CategoryHandler(vector<int> categories);
 *     bool haveSameCategory(int a, int b);
 * };
 */
class Solution {
public:
    int numberOfCategories(int n, CategoryHandler* categoryHandler) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (int a = 0; a < n; ++a) {
            for (int b = a + 1; b < n; ++b) {
                if (categoryHandler->haveSameCategory(a, b)) {
                    p[find(a)] = find(b);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += i == p[i];
        }
        return ans;
    }
};
```

```go
/**
 * Definition for a category handler.
 * type CategoryHandler interface {
 *  HaveSameCategory(int, int) bool
 * }
 */
func numberOfCategories(n int, categoryHandler CategoryHandler) (ans int) {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for a := 0; a < n; a++ {
		for b := a + 1; b < n; b++ {
			if categoryHandler.HaveSameCategory(a, b) {
				p[find(a)] = find(b)
			}
		}
	}
	for i, x := range p {
		if i == x {
			ans++
		}
	}
	return
}
```

```ts
/**
 * Definition for a category handler.
 * class CategoryHandler {
 *     constructor(categories: number[]);
 *     public haveSameCategory(a: number, b: number): boolean;
 * }
 */
function numberOfCategories(n: number, categoryHandler: CategoryHandler): number {
    const p: number[] = new Array(n).fill(0).map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let a = 0; a < n; ++a) {
        for (let b = a + 1; b < n; ++b) {
            if (categoryHandler.haveSameCategory(a, b)) {
                p[find(a)] = find(b);
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (i === p[i]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
