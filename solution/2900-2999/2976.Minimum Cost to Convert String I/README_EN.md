# [2976. Minimum Cost to Convert String I](https://leetcode.com/problems/minimum-cost-to-convert-string-i)

[中文文档](/solution/2900-2999/2976.Minimum%20Cost%20to%20Convert%20String%20I/README.md)

<!-- tags:Graph,Array,String,Shortest Path -->

## Description

<p>You are given two <strong>0-indexed</strong> strings <code>source</code> and <code>target</code>, both of length <code>n</code> and consisting of <strong>lowercase</strong> English letters. You are also given two <strong>0-indexed</strong> character arrays <code>original</code> and <code>changed</code>, and an integer array <code>cost</code>, where <code>cost[i]</code> represents the cost of changing the character <code>original[i]</code> to the character <code>changed[i]</code>.</p>

<p>You start with the string <code>source</code>. In one operation, you can pick a character <code>x</code> from the string and change it to the character <code>y</code> at a cost of <code>z</code> <strong>if</strong> there exists <strong>any</strong> index <code>j</code> such that <code>cost[j] == z</code>, <code>original[j] == x</code>, and <code>changed[j] == y</code>.</p>

<p>Return <em>the <strong>minimum</strong> cost to convert the string </em><code>source</code><em> to the string </em><code>target</code><em> using <strong>any</strong> number of operations. If it is impossible to convert</em> <code>source</code> <em>to</em> <code>target</code>, <em>return</em> <code>-1</code>.</p>

<p><strong>Note</strong> that there may exist indices <code>i</code>, <code>j</code> such that <code>original[j] == original[i]</code> and <code>changed[j] == changed[i]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> source = &quot;abcd&quot;, target = &quot;acbe&quot;, original = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;c&quot;,&quot;e&quot;,&quot;d&quot;], changed = [&quot;b&quot;,&quot;c&quot;,&quot;b&quot;,&quot;e&quot;,&quot;b&quot;,&quot;e&quot;], cost = [2,5,5,1,2,20]
<strong>Output:</strong> 28
<strong>Explanation:</strong> To convert the string &quot;abcd&quot; to string &quot;acbe&quot;:
- Change value at index 1 from &#39;b&#39; to &#39;c&#39; at a cost of 5.
- Change value at index 2 from &#39;c&#39; to &#39;e&#39; at a cost of 1.
- Change value at index 2 from &#39;e&#39; to &#39;b&#39; at a cost of 2.
- Change value at index 3 from &#39;d&#39; to &#39;e&#39; at a cost of 20.
The total cost incurred is 5 + 1 + 2 + 20 = 28.
It can be shown that this is the minimum possible cost.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> source = &quot;aaaa&quot;, target = &quot;bbbb&quot;, original = [&quot;a&quot;,&quot;c&quot;], changed = [&quot;c&quot;,&quot;b&quot;], cost = [1,2]
<strong>Output:</strong> 12
<strong>Explanation:</strong> To change the character &#39;a&#39; to &#39;b&#39; change the character &#39;a&#39; to &#39;c&#39; at a cost of 1, followed by changing the character &#39;c&#39; to &#39;b&#39; at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of &#39;a&#39; to &#39;b&#39;, a total cost of 3 * 4 = 12 is incurred.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> source = &quot;abcd&quot;, target = &quot;abce&quot;, original = [&quot;a&quot;], changed = [&quot;e&quot;], cost = [10000]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to convert source to target because the value at index 3 cannot be changed from &#39;d&#39; to &#39;e&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= source.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>source</code>, <code>target</code> consist of lowercase English letters.</li>
	<li><code>1 &lt;= cost.length == original.length == changed.length &lt;= 2000</code></li>
	<li><code>original[i]</code>, <code>changed[i]</code> are lowercase English letters.</li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>original[i] != changed[i]</code></li>
</ul>

## Solutions

### Solution 1: Floyd Algorithm

According to the problem description, we can consider each letter as a node, and the conversion cost between each pair of letters as a directed edge. We first initialize a $26 \times 26$ two-dimensional array $g$, where $g[i][j]$ represents the minimum cost of converting letter $i$ to letter $j$. Initially, $g[i][j] = \infty$, and if $i = j$, then $g[i][j] = 0$.

Next, we traverse the arrays $original$, $changed$, and $cost$. For each index $i$, we update the cost $cost[i]$ of converting $original[i]$ to $changed[i]$ to $g[original[i]][changed[i]]$, taking the minimum value.

Then, we use the Floyd algorithm to calculate the minimum cost between any two nodes in $g$. Finally, we traverse the strings $source$ and $target$. If $source[i] \neq target[i]$ and $g[source[i]][target[i]] \geq \infty$, it means that the conversion cannot be completed, so we return $-1$. Otherwise, we add $g[source[i]][target[i]]$ to the answer.

After the traversal ends, we return the answer.

The time complexity is $O(m + n + |\Sigma|^3)$, and the space complexity is $O(|\Sigma|^2)$. Where $m$ and $n$ are the lengths of the arrays $original$ and $source$ respectively; and $|\Sigma|$ is the size of the alphabet, that is, $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def minimumCost(
        self,
        source: str,
        target: str,
        original: List[str],
        changed: List[str],
        cost: List[int],
    ) -> int:
        g = [[inf] * 26 for _ in range(26)]
        for i in range(26):
            g[i][i] = 0
        for x, y, z in zip(original, changed, cost):
            x = ord(x) - ord('a')
            y = ord(y) - ord('a')
            g[x][y] = min(g[x][y], z)
        for k in range(26):
            for i in range(26):
                for j in range(26):
                    g[i][j] = min(g[i][j], g[i][k] + g[k][j])
        ans = 0
        for a, b in zip(source, target):
            if a != b:
                x, y = ord(a) - ord('a'), ord(b) - ord('a')
                if g[x][y] >= inf:
                    return -1
                ans += g[x][y]
        return ans
```

```java
class Solution {
    public long minimumCost(
        String source, String target, char[] original, char[] changed, int[] cost) {
        final int inf = 1 << 29;
        int[][] g = new int[26][26];
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(g[i], inf);
            g[i][i] = 0;
        }
        for (int i = 0; i < original.length; ++i) {
            int x = original[i] - 'a';
            int y = changed[i] - 'a';
            int z = cost[i];
            g[x][y] = Math.min(g[x][y], z);
        }
        for (int k = 0; k < 26; ++k) {
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < 26; ++j) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        long ans = 0;
        int n = source.length();
        for (int i = 0; i < n; ++i) {
            int x = source.charAt(i) - 'a';
            int y = target.charAt(i) - 'a';
            if (x != y) {
                if (g[x][y] >= inf) {
                    return -1;
                }
                ans += g[x][y];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minimumCost(string source, string target, vector<char>& original, vector<char>& changed, vector<int>& cost) {
        const int inf = 1 << 29;
        int g[26][26];
        for (int i = 0; i < 26; ++i) {
            fill(begin(g[i]), end(g[i]), inf);
            g[i][i] = 0;
        }

        for (int i = 0; i < original.size(); ++i) {
            int x = original[i] - 'a';
            int y = changed[i] - 'a';
            int z = cost[i];
            g[x][y] = min(g[x][y], z);
        }

        for (int k = 0; k < 26; ++k) {
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < 26; ++j) {
                    g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }

        long long ans = 0;
        int n = source.length();
        for (int i = 0; i < n; ++i) {
            int x = source[i] - 'a';
            int y = target[i] - 'a';
            if (x != y) {
                if (g[x][y] >= inf) {
                    return -1;
                }
                ans += g[x][y];
            }
        }
        return ans;
    }
};
```

```go
func minimumCost(source string, target string, original []byte, changed []byte, cost []int) (ans int64) {
	const inf = 1 << 29
	g := make([][]int, 26)
	for i := range g {
		g[i] = make([]int, 26)
		for j := range g[i] {
			if i == j {
				g[i][j] = 0
			} else {
				g[i][j] = inf
			}
		}
	}

	for i := 0; i < len(original); i++ {
		x := int(original[i] - 'a')
		y := int(changed[i] - 'a')
		z := cost[i]
		g[x][y] = min(g[x][y], z)
	}

	for k := 0; k < 26; k++ {
		for i := 0; i < 26; i++ {
			for j := 0; j < 26; j++ {
				g[i][j] = min(g[i][j], g[i][k]+g[k][j])
			}
		}
	}
	n := len(source)
	for i := 0; i < n; i++ {
		x := int(source[i] - 'a')
		y := int(target[i] - 'a')
		if x != y {
			if g[x][y] >= inf {
				return -1
			}
			ans += int64(g[x][y])
		}
	}
	return
}
```

```ts
function minimumCost(
    source: string,
    target: string,
    original: string[],
    changed: string[],
    cost: number[],
): number {
    const g: number[][] = Array.from({ length: 26 }, () => Array(26).fill(Infinity));
    for (let i = 0; i < 26; ++i) {
        g[i][i] = 0;
    }
    for (let i = 0; i < original.length; ++i) {
        let x: number = original[i].charCodeAt(0) - 'a'.charCodeAt(0);
        let y: number = changed[i].charCodeAt(0) - 'a'.charCodeAt(0);
        let z: number = cost[i];
        g[x][y] = Math.min(g[x][y], z);
    }

    for (let k = 0; k < 26; ++k) {
        for (let i = 0; i < 26; ++i) {
            for (let j = 0; j < 26; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }

    let ans: number = 0;
    let n: number = source.length;
    for (let i = 0; i < n; ++i) {
        let x: number = source.charCodeAt(i) - 'a'.charCodeAt(0);
        let y: number = target.charCodeAt(i) - 'a'.charCodeAt(0);
        if (x !== y) {
            if (g[x][y] >= Infinity) {
                return -1;
            }
            ans += g[x][y];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
