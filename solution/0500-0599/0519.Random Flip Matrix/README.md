# [519. 随机翻转矩阵](https://leetcode.cn/problems/random-flip-matrix)

[English Version](/solution/0500-0599/0519.Random%20Flip%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的二元矩阵 <code>matrix</code> ，且所有值被初始化为 <code>0</code> 。请你设计一个算法，随机选取一个满足&nbsp;<code>matrix[i][j] == 0</code> 的下标&nbsp;<code>(i, j)</code> ，并将它的值变为 <code>1</code> 。所有满足 <code>matrix[i][j] == 0</code> 的下标 <code>(i, j)</code> 被选取的概率应当均等。</p>

<p>尽量最少调用内置的随机函数，并且优化时间和空间复杂度。</p>

<p>实现 <code>Solution</code> 类：</p>

<ul>
	<li><code>Solution(int m, int n)</code> 使用二元矩阵的大小 <code>m</code> 和 <code>n</code> 初始化该对象</li>
	<li><code>int[] flip()</code> 返回一个满足&nbsp;<code>matrix[i][j] == 0</code> 的随机下标 <code>[i, j]</code> ，并将其对应格子中的值变为 <code>1</code></li>
	<li><code>void reset()</code> 将矩阵中所有的值重置为 <code>0</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["Solution", "flip", "flip", "flip", "reset", "flip"]
[[3, 1], [], [], [], [], []]
<strong>输出</strong>
[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

<strong>解释</strong>
Solution solution = new Solution(3, 1);
solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li>
	<li>每次调用<code>flip</code> 时，矩阵中至少存在一个值为 0 的格子。</li>
	<li>最多调用 <code>1000</code> 次 <code>flip</code> 和 <code>reset</code> 方法。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
