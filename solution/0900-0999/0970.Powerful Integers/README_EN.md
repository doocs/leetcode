---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0970.Powerful%20Integers/README_EN.md
tags:
    - Hash Table
    - Math
    - Enumeration
---

# [970. Powerful Integers](https://leetcode.com/problems/powerful-integers)

[中文文档](/solution/0900-0999/0970.Powerful%20Integers/README.md)

## Description

<p>Given three integers <code>x</code>, <code>y</code>, and <code>bound</code>, return <em>a list of all the <strong>powerful integers</strong> that have a value less than or equal to</em> <code>bound</code>.</p>

<p>An integer is <strong>powerful</strong> if it can be represented as <code>x<sup>i</sup> + y<sup>j</sup></code> for some integers <code>i &gt;= 0</code> and <code>j &gt;= 0</code>.</p>

<p>You may return the answer in <strong>any order</strong>. In your answer, each value should occur <strong>at most once</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 2, y = 3, bound = 10
<strong>Output:</strong> [2,3,4,5,7,9,10]
<strong>Explanation:</strong>
2 = 2<sup>0</sup> + 3<sup>0</sup>
3 = 2<sup>1</sup> + 3<sup>0</sup>
4 = 2<sup>0</sup> + 3<sup>1</sup>
5 = 2<sup>1</sup> + 3<sup>1</sup>
7 = 2<sup>2</sup> + 3<sup>1</sup>
9 = 2<sup>3</sup> + 3<sup>0</sup>
10 = 2<sup>0</sup> + 3<sup>2</sup>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 3, y = 5, bound = 15
<strong>Output:</strong> [2,4,6,8,10,14]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= x, y &lt;= 100</code></li>
	<li><code>0 &lt;= bound &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Enumeration

According to the description of the problem, a powerful integer can be represented as $x^i + y^j$, where $i \geq 0$, $j \geq 0$.

The problem requires us to find all powerful integers that do not exceed $bound$. We notice that the value range of $bound$ does not exceed $10^6$, and $2^{20} = 1048576 \gt 10^6$. Therefore, if $x \geq 2$, then $i$ is at most $20$ to make $x^i + y^j \leq bound$ hold. Similarly, if $y \geq 2$, then $j$ is at most $20$.

Therefore, we can use double loop to enumerate all possible $x^i$ and $y^j$, denoted as $a$ and $b$ respectively, and ensure that $a + b \leq bound$, then $a + b$ is a powerful integer. We use a hash table to store all powerful integers that meet the conditions, and finally convert all elements in the hash table into the answer list and return it.

> Note that if $x=1$ or $y=1$, then the value of $a$ or $b$ is always equal to $1$, and the corresponding loop only needs to be executed once to exit.

The time complexity is $O(\log^2 bound)$, and the space complexity is $O(\log^2 bound)$.

<!-- tabs:start -->

```python
class Solution:
    def powerfulIntegers(self, x: int, y: int, bound: int) -> List[int]:
        ans = set()
        a = 1
        while a <= bound:
            b = 1
            while a + b <= bound:
                ans.add(a + b)
                b *= y
                if y == 1:
                    break
            if x == 1:
                break
            a *= x
        return list(ans)
```

```java
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> ans = new HashSet<>();
        for (int a = 1; a <= bound; a *= x) {
            for (int b = 1; a + b <= bound; b *= y) {
                ans.add(a + b);
                if (y == 1) {
                    break;
                }
            }
            if (x == 1) {
                break;
            }
        }
        return new ArrayList<>(ans);
    }
}
```

```cpp
class Solution {
public:
    vector<int> powerfulIntegers(int x, int y, int bound) {
        unordered_set<int> ans;
        for (int a = 1; a <= bound; a *= x) {
            for (int b = 1; a + b <= bound; b *= y) {
                ans.insert(a + b);
                if (y == 1) {
                    break;
                }
            }
            if (x == 1) {
                break;
            }
        }
        return vector<int>(ans.begin(), ans.end());
    }
};
```

```go
func powerfulIntegers(x int, y int, bound int) (ans []int) {
	s := map[int]struct{}{}
	for a := 1; a <= bound; a *= x {
		for b := 1; a+b <= bound; b *= y {
			s[a+b] = struct{}{}
			if y == 1 {
				break
			}
		}
		if x == 1 {
			break
		}
	}
	for x := range s {
		ans = append(ans, x)
	}
	return ans
}
```

```ts
function powerfulIntegers(x: number, y: number, bound: number): number[] {
    const ans = new Set<number>();
    for (let a = 1; a <= bound; a *= x) {
        for (let b = 1; a + b <= bound; b *= y) {
            ans.add(a + b);
            if (y === 1) {
                break;
            }
        }
        if (x === 1) {
            break;
        }
    }
    return Array.from(ans);
}
```

```js
/**
 * @param {number} x
 * @param {number} y
 * @param {number} bound
 * @return {number[]}
 */
var powerfulIntegers = function (x, y, bound) {
    const ans = new Set();
    for (let a = 1; a <= bound; a *= x) {
        for (let b = 1; a + b <= bound; b *= y) {
            ans.add(a + b);
            if (y === 1) {
                break;
            }
        }
        if (x === 1) {
            break;
        }
    }
    return [...ans];
};
```

<!-- tabs:end -->

<!-- end -->
