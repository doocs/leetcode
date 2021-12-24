# [519. Random Flip Matrix](https://leetcode.com/problems/random-flip-matrix)

[中文文档](/solution/0500-0599/0519.Random%20Flip%20Matrix/README.md)

## Description

<p>You are given the number of rows <code>n_rows</code>&nbsp;and number of columns <code>n_cols</code>&nbsp;of a&nbsp;2D&nbsp;binary matrix&nbsp;where all values are initially 0.&nbsp;Write a function <code>flip</code>&nbsp;which chooses&nbsp;a 0 value&nbsp;<a href="https://en.wikipedia.org/wiki/Discrete_uniform_distribution" target="_blank">uniformly at random</a>,&nbsp;changes it to 1,&nbsp;and then returns the position <code>[row.id, col.id]</code> of that value. Also, write a function <code>reset</code> which sets all values back to 0.&nbsp;<strong>Try to minimize the number of calls to system&#39;s Math.random()</strong> and optimize the time and&nbsp;space complexity.</p>

<p>Note:</p>

<ol>
	<li><code>1 &lt;= n_rows, n_cols&nbsp;&lt;= 10000</code></li>
	<li><code>0 &lt;= row.id &lt; n_rows</code> and <code>0 &lt;= col.id &lt; n_cols</code></li>
	<li><code>flip</code>&nbsp;will not be called when the matrix has no&nbsp;0 values left.</li>
	<li>the total number of calls to&nbsp;<code>flip</code>&nbsp;and <code>reset</code>&nbsp;will not exceed&nbsp;1000.</li>
</ol>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: 

</strong><span id="example-input-1-1">[&quot;Solution&quot;,&quot;flip&quot;,&quot;flip&quot;,&quot;flip&quot;,&quot;flip&quot;]

</span><span id="example-input-1-2">[[2,3],[],[],[],[]]</span>

<strong>Output: </strong><span id="example-output-1">[null,[0,1],[1,2],[1,0],[1,1]]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: 

</strong><span id="example-input-2-1">[&quot;Solution&quot;,&quot;flip&quot;,&quot;flip&quot;,&quot;reset&quot;,&quot;flip&quot;]

</span><span id="example-input-2-2">[[1,2],[],[],[],[]]</span>

<strong>Output: </strong><span id="example-output-2">[null,[0,0],[0,1],null,[0,0]]</span></pre>

</div>

<p><strong>Explanation of Input Syntax:</strong></p>

<p>The input is two lists:&nbsp;the subroutines called&nbsp;and their&nbsp;arguments. <code>Solution</code>&#39;s constructor&nbsp;has two arguments, <code>n_rows</code> and <code>n_cols</code>.&nbsp;<code>flip</code>&nbsp;and <code>reset</code> have&nbsp;no&nbsp;arguments.&nbsp;Arguments&nbsp;are&nbsp;always wrapped with a list, even if there aren&#39;t any.</p>

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
        return new int[]{idx / n, idx % n};
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
