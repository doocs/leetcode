# [2782. 唯一类别的数量](https://leetcode.cn/problems/number-of-unique-categories)

[English Version](/solution/2700-2799/2782.Number%20of%20Unique%20Categories/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个整数 <code>n</code> 和一个 <code>CategoryHandler</code> 类的对象 <code>categoryHandler</code> 。</p>

<p>有 <code>n</code> 个元素，编号从 <code>0</code> 到 <code>n - 1</code>。每个元素都有一个类别，你的任务是找出唯一类别的数量。</p>

<p><code>CategoryHandler</code> 类包含以下方法，可能对你有帮助：</p>

<ul>
	<li><code>boolean haveSameCategory(integer a, integer b)</code>：如果 <code>a</code> 和 <code>b</code> 属于相同的类别，则返回 <code>true</code>，否则返回 <code>false</code>。同时，如果 <code>a</code> 或 <code>b</code> 不是有效的数字（即大于等于 <code>n</code> 或小于 <code>0</code>），它也会返回 <code>false</code>。</li>
</ul>

<p>返回唯一类别的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 6, categoryHandler = [1,1,2,2,3,3]
<strong>输出：</strong>3
<b>解释：</b>这个示例中有 6 个元素。前两个元素属于类别 1，接下来两个属于类别 2，最后两个元素属于类别 3。所以有 3 个唯一类别。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 5, categoryHandler = [1,2,3,4,5]
<b>输出：</b>5
<b>解释：</b>这个示例中有 5 个元素。每个元素属于一个唯一的类别。所以有 5 个唯一类别。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>n = 3, categoryHandler = [1,1,1]
<b>输出：</b>1
<b>解释：</b>这个示例中有 3 个元素。它们全部属于同一个类别。所以只有 1 个唯一类别。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：并查集**

我们用并查集来维护相同类别的元素，接下来枚举所有的元素对，如果两个元素属于相同的类别，那么就将它们合并到同一个集合中。最后统计并查集中有多少个集合，就是答案。

时间复杂度 $(n^2 \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 是元素的个数，而 $\alpha$ 是阿克曼函数的反函数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
