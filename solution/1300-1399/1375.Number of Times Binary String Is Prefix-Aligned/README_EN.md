# [1375. Number of Times Binary String Is Prefix-Aligned](https://leetcode.com/problems/number-of-times-binary-string-is-prefix-aligned)

[中文文档](/solution/1300-1399/1375.Number%20of%20Times%20Binary%20String%20Is%20Prefix-Aligned/README.md)

## Description

<p>You have a <strong>1-indexed</strong> binary string of length <code>n</code> where all the bits are <code>0</code> initially. We will flip all the bits of this binary string (i.e., change them from <code>0</code> to <code>1</code>) one by one. You are given a <strong>1-indexed</strong> integer array <code>flips</code> where <code>flips[i]</code> indicates that the bit at index <code>i</code> will be flipped in the <code>i<sup>th</sup></code> step.</p>

<p>A binary string is <strong>prefix-aligned</strong> if, after the <code>i<sup>th</sup></code> step, all the bits in the <strong>inclusive</strong> range <code>[1, i]</code> are ones and all the other bits are zeros.</p>

<p>Return <em>the number of times the binary string is <strong>prefix-aligned</strong> during the flipping process</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> flips = [3,2,4,1,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The binary string is initially &quot;00000&quot;.
After applying step 1: The string becomes &quot;00100&quot;, which is not prefix-aligned.
After applying step 2: The string becomes &quot;01100&quot;, which is not prefix-aligned.
After applying step 3: The string becomes &quot;01110&quot;, which is not prefix-aligned.
After applying step 4: The string becomes &quot;11110&quot;, which is prefix-aligned.
After applying step 5: The string becomes &quot;11111&quot;, which is prefix-aligned.
We can see that the string was prefix-aligned 2 times, so we return 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> flips = [4,1,2,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The binary string is initially &quot;0000&quot;.
After applying step 1: The string becomes &quot;0001&quot;, which is not prefix-aligned.
After applying step 2: The string becomes &quot;1001&quot;, which is not prefix-aligned.
After applying step 3: The string becomes &quot;1101&quot;, which is not prefix-aligned.
After applying step 4: The string becomes &quot;1111&quot;, which is prefix-aligned.
We can see that the string was prefix-aligned 1 time, so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == flips.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>flips</code> is a permutation of the integers in the range <code>[1, n]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numTimesAllBlue(self, flips: List[int]) -> int:
        ans = mx = 0
        for i, v in enumerate(flips, 1):
            mx = max(mx, v)
            if mx == i:
                ans += 1
        return ans
```

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def numTimesAllBlue(self, flips: List[int]) -> int:
        n = len(flips)
        tree = BinaryIndexedTree(n)
        ans = mx = 0
        for v in flips:
            mx = max(mx, v)
            tree.update(v, 1)
            if tree.query(mx) == mx:
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numTimesAllBlue(int[] flips) {
        int ans = 0;
        int mx = 0;
        for (int i = 1; i <= flips.length; ++i) {
            mx = Math.max(mx, flips[i - 1]);
            if (mx == i) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }
}

class Solution {
    public int numTimesAllBlue(int[] flips) {
        int n = flips.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int ans = 0;
        int mx = 0;
        for (int v : flips) {
            mx = Math.max(mx, v);
            tree.update(v, 1);
            if (tree.query(mx) == mx) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numTimesAllBlue(vector<int>& flips) {
        int ans = 0, mx = 0;
        for (int i = 1; i <= flips.size(); ++i) {
            mx = max(mx, flips[i - 1]);
            ans += mx == i;
        }
        return ans;
    }
};
```

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int delta) {
        while (x <= n)
        {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    int numTimesAllBlue(vector<int>& flips) {
        int n = flips.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        int ans = 0, mx = 0;
        for (int v : flips)
        {
            mx = max(mx, v);
            tree->update(v, 1);
            ans += tree->query(mx) == mx;
        }
        return ans;
    }
};
```

### **Go**

```go
func numTimesAllBlue(flips []int) int {
	ans, mx := 0, 0
	for i := 1; i <= len(flips); i++ {
		mx = max(mx, flips[i-1])
		if mx == i {
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func numTimesAllBlue(flips []int) int {
	n := len(flips)
	tree := newBinaryIndexedTree(n)
	ans, mx := 0, 0
	for _, v := range flips {
		mx = max(mx, v)
		tree.update(v, 1)
		if tree.query(mx) == mx {
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
