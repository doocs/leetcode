# [2550. Count Collisions of Monkeys on a Polygon](https://leetcode.com/problems/count-collisions-of-monkeys-on-a-polygon)

[中文文档](/solution/2500-2599/2550.Count%20Collisions%20of%20Monkeys%20on%20a%20Polygon/README.md)

## Description

<p>There is a regular convex polygon with <code>n</code> vertices. The vertices are labeled from <code>0</code> to <code>n - 1</code> in a clockwise direction, and each vertex has <strong>exactly one monkey</strong>. The following figure shows a convex polygon of <code>6</code> vertices.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2550.Count%20Collisions%20of%20Monkeys%20on%20a%20Polygon/images/hexagon.jpg" style="width: 300px; height: 293px;" />
<p>Each monkey moves simultaneously to a neighboring vertex. A neighboring vertex for a vertex <code>i</code> can be:</p>

<ul>
	<li>the vertex <code>(i + 1) % n</code> in the clockwise direction, or</li>
	<li>the vertex <code>(i - 1 + n) % n</code> in the counter-clockwise direction.</li>
</ul>

<p>A <strong>collision</strong> happens if at least two monkeys reside on the same vertex after the movement or intersect&nbsp;on an edge.</p>

<p>Return <em>the number of ways the monkeys can move so that at least <strong>one collision</strong></em> <em> happens</em>. Since the answer may be very large, return it modulo <code>10<sup>9 </sup>+ 7</code>.</p>

<p><strong>Note</strong> that each monkey can only move once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 8 total possible movements.
Two ways such that they collide at some point are:
- Monkey 1 moves in a clockwise direction; monkey 2 moves in an anticlockwise direction; monkey 3 moves in a clockwise direction. Monkeys 1 and 2 collide.
- Monkey 1 moves in an anticlockwise direction; monkey 2 moves in an anticlockwise direction; monkey 3 moves in a clockwise direction. Monkeys 1 and 3 collide.
It can be shown 6 total movements result in a collision.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 14
<strong>Explanation:</strong> It can be shown that there are 14 ways for the monkeys to collide.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def monkeyMove(self, n: int) -> int:
        mod = 10**9 + 7
        return (pow(2, n, mod) - 2) % mod
```

### **Java**

```java
class Solution {
    public int monkeyMove(int n) {
        final int mod = (int) 1e9 + 7;
        return (int) (qmi(2, n, mod) - 2 + mod) % mod;
    }

    public long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int monkeyMove(int n) {
        const int mod = 1e9 + 7;
        return (qmi(2, n, mod) - 2 + mod) % mod;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};
```

### **Go**

```go
func monkeyMove(n int) int {
	const mod = 1e9 + 7
	return (qmi(2, n, mod) - 2 + mod) % mod
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}
```

### **TypeScript**

```ts
function monkeyMove(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    return Number((qmi(2n, n, mod) - 2n + mod) % mod);
}

function qmi(a: bigint, k: number, p: bigint): bigint {
    let res = 1n;
    while (k) {
        if ((k & 1) === 1) {
            res = (res * a) % p;
        }
        k >>= 1;
        a = (a * a) % p;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
