# [970. 强整数](https://leetcode.cn/problems/powerful-integers)

[English Version](/solution/0900-0999/0970.Powerful%20Integers/README_EN.md)

<!-- tags:哈希表,数学,枚举 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定三个整数 <code>x</code>&nbsp;、&nbsp;<code>y</code>&nbsp;和<em>&nbsp;</em><code>bound</code><em>&nbsp;</em>，返回 <em>值小于或等于&nbsp;<code>bound</code>&nbsp;的所有&nbsp;<strong>强整数</strong>&nbsp;组成的列表</em>&nbsp;。</p>

<p>如果某一整数可以表示为&nbsp;<code>x<sup>i</sup>&nbsp;+ y<sup>j</sup></code>&nbsp;，其中整数&nbsp;<code>i &gt;= 0</code> 且&nbsp;<code>j &gt;= 0</code>，那么我们认为该整数是一个&nbsp;<strong>强整数</strong>&nbsp;。</p>

<p>你可以按 <strong>任何顺序</strong> 返回答案。在你的回答中，每个值 <strong>最多</strong> 出现一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2, y = 3, bound = 10
<strong>输出：</strong>[2,3,4,5,7,9,10]
<strong>解释： </strong>
2 = 2<sup>0</sup> + 3<sup>0</sup>
3 = 2<sup>1</sup> + 3<sup>0</sup>
4 = 2<sup>0</sup> + 3<sup>1</sup>
5 = 2<sup>1</sup> + 3<sup>1</sup>
7 = 2<sup>2</sup> + 3<sup>1</sup>
9 = 2<sup>3</sup> + 3<sup>0</sup>
10 = 2<sup>0</sup> + 3<sup>2</sup></pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>x = 3, y = 5, bound = 15
<strong>输出：</strong>[2,4,6,8,10,14]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x, y &lt;= 100</code></li>
	<li><code>0 &lt;= bound &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：哈希表 + 枚举

根据题目描述，一个强整数可以表示成 $x^i + y^j$，其中 $i \geq 0$, $j \geq 0$。

题目需要我们找出所有不超过 $bound$ 的强整数，我们注意到 $bound$ 的取值范围不超过 $10^6$，而 $2^{20} = 1048576 \gt 10^6$。因此，如果 $x \geq 2$，那么 $i$ 最大不超过 $20$，才有可能使得 $x^i + y^j \leq bound$ 成立。同理，如果 $y \geq 2$，那么 $j$ 最大不超过 $20$。

因此我们可以使用双重循环，枚举所有可能的 $x^i$ 和 $y^j$，分别记为 $a$ 和 $b$，并保证 $a + b \leq bound$，此时 $a + b$ 即为一个强整数。我们使用哈希表存储所有满足条件的强整数，最后将哈希表中的所有元素转换成答案列表返回即可。

> 注意，如果 $x=1$ 或者 $y=1$，那么 $a$ 或者 $b$ 的值恒等于 $1$，对应的循环只需要执行一次即可退出。

时间复杂度 $O(\log^2 bound)$，空间复杂度 $O(\log^2 bound)$。

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
