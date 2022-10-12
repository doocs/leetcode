# [519. Random Flip Matrix](https://leetcode.com/problems/random-flip-matrix)

[中文文档](/solution/0500-0599/0519.Random%20Flip%20Matrix/README.md)

## Description

<p>There is an <code>m x n</code> binary grid <code>matrix</code> with all the values set <code>0</code> initially. Design an algorithm to randomly pick an index <code>(i, j)</code> where <code>matrix[i][j] == 0</code> and flips it to <code>1</code>. All the indices <code>(i, j)</code> where <code>matrix[i][j] == 0</code> should be equally likely to be returned.</p>

<p>Optimize your algorithm to minimize the number of calls made to the <strong>built-in</strong> random function of your language and optimize the time and space complexity.</p>

<p>Implement the <code>Solution</code> class:</p>

<ul>
	<li><code>Solution(int m, int n)</code> Initializes the object with the size of the binary matrix <code>m</code> and <code>n</code>.</li>
	<li><code>int[] flip()</code> Returns a random index <code>[i, j]</code> of the matrix where <code>matrix[i][j] == 0</code> and flips it to <code>1</code>.</li>
	<li><code>void reset()</code> Resets all the values of the matrix to be <code>0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;, &quot;flip&quot;, &quot;flip&quot;, &quot;flip&quot;, &quot;reset&quot;, &quot;flip&quot;]
[[3, 1], [], [], [], [], []]
<strong>Output</strong>
[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

<strong>Explanation</strong>
Solution solution = new Solution(3, 1);
solution.flip();  // return [1, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
solution.flip();  // return [2, 0], Since [1,0] was returned, [2,0] and [0,0]
solution.flip();  // return [0, 0], Based on the previously returned indices, only [0,0] can be returned.
solution.reset(); // All the values are reset to 0 and can be returned.
solution.flip();  // return [2, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li>
	<li>There will be at least one free cell for each call to <code>flip</code>.</li>
	<li>At most <code>1000</code> calls will be made to <code>flip</code> and <code>reset</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def __init__(self, m: int, n: int):
        self.m = m
        self.n = n
        self.total = m * n
        self.mp = {}

    def flip(self) -> List[int]:
        self.total -= 1
        x = random.randint(0, self.total)
        idx = self.mp.get(x, x)
        self.mp[x] = self.mp.get(self.total, self.total)
        return [idx // self.n, idx % self.n]

    def reset(self) -> None:
        self.total = self.m * self.n
        self.mp.clear()


# Your Solution object will be instantiated and called as such:
# obj = Solution(m, n)
# param_1 = obj.flip()
# obj.reset()
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int total;
    private Random rand = new Random();
    private Map<Integer, Integer> mp = new HashMap<>();

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
    }

    public int[] flip() {
        int x = rand.nextInt(total--);
        int idx = mp.getOrDefault(x, x);
        mp.put(x, mp.getOrDefault(total, total));
        return new int[] {idx / n, idx % n};
    }

    public void reset() {
        total = m * n;
        mp.clear();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(m, n);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */
```

### **...**

```

```

<!-- tabs:end -->
