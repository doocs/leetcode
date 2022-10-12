# [900. RLE Iterator](https://leetcode.com/problems/rle-iterator)

[中文文档](/solution/0900-0999/0900.RLE%20Iterator/README.md)

## Description

<p>We can use run-length encoding (i.e., <strong>RLE</strong>) to encode a sequence of integers. In a run-length encoded array of even length <code>encoding</code> (<strong>0-indexed</strong>), for all even <code>i</code>, <code>encoding[i]</code> tells us the number of times that the non-negative integer value <code>encoding[i + 1]</code> is repeated in the sequence.</p>

<ul>
	<li>For example, the sequence <code>arr = [8,8,8,5,5]</code> can be encoded to be <code>encoding = [3,8,2,5]</code>. <code>encoding = [3,8,0,9,2,5]</code> and <code>encoding = [2,8,1,8,2,5]</code> are also valid <strong>RLE</strong> of <code>arr</code>.</li>
</ul>

<p>Given a run-length encoded array, design an iterator that iterates through it.</p>

<p>Implement the <code>RLEIterator</code> class:</p>

<ul>
	<li><code>RLEIterator(int[] encoded)</code> Initializes the object with the encoded array <code>encoded</code>.</li>
	<li><code>int next(int n)</code> Exhausts the next <code>n</code> elements and returns the last element exhausted in this way. If there is no element left to exhaust, return <code>-1</code> instead.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;RLEIterator&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;]
[[[3, 8, 0, 9, 2, 5]], [2], [1], [1], [2]]
<strong>Output</strong>
[null, 8, 8, 5, -1]

<strong>Explanation</strong>
RLEIterator rLEIterator = new RLEIterator([3, 8, 0, 9, 2, 5]); // This maps to the sequence [8,8,8,5,5].
rLEIterator.next(2); // exhausts 2 terms of the sequence, returning 8. The remaining sequence is now [8, 5, 5].
rLEIterator.next(1); // exhausts 1 term of the sequence, returning 8. The remaining sequence is now [5, 5].
rLEIterator.next(1); // exhausts 1 term of the sequence, returning 5. The remaining sequence is now [5].
rLEIterator.next(2); // exhausts 2 terms, returning -1. This is because the first term exhausted was 5,
but the second term did not exist. Since the last term exhausted does not exist, we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= encoding.length &lt;= 1000</code></li>
	<li><code>encoding.length</code> is even.</li>
	<li><code>0 &lt;= encoding[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>1000</code> calls will be made to <code>next</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class RLEIterator:
    def __init__(self, encoding: List[int]):
        self.encoding = encoding
        self.i = 0
        self.curr = 0

    def next(self, n: int) -> int:
        while self.i < len(self.encoding):
            if self.curr + n > self.encoding[self.i]:
                n -= self.encoding[self.i] - self.curr
                self.curr = 0
                self.i += 2
            else:
                self.curr += n
                return self.encoding[self.i + 1]
        return -1


# Your RLEIterator object will be instantiated and called as such:
# obj = RLEIterator(encoding)
# param_1 = obj.next(n)
```

### **Java**

```java
class RLEIterator {
    private int[] encoding;
    private int curr;
    private int i;

    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        curr = 0;
        i = 0;
    }

    public int next(int n) {
        while (i < encoding.length) {
            if (curr + n > encoding[i]) {
                n -= encoding[i] - curr;
                i += 2;
                curr = 0;
            } else {
                curr += n;
                return encoding[i + 1];
            }
        }
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */
```

### **C++**

```cpp
class RLEIterator {
public:
    vector<int> encoding;
    int curr;
    int i;

    RLEIterator(vector<int>& encoding) {
        this->encoding = encoding;
        this->curr = 0;
        this->i = 0;
    }

    int next(int n) {
        while (i < encoding.size()) {
            if (curr + n > encoding[i]) {
                n -= encoding[i] - curr;
                curr = 0;
                i += 2;
            } else {
                curr += n;
                return encoding[i + 1];
            }
        }
        return -1;
    }
};

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator* obj = new RLEIterator(encoding);
 * int param_1 = obj->next(n);
 */
```

### **Go**

```go
type RLEIterator struct {
	encoding []int
	curr     int
	i        int
}

func Constructor(encoding []int) RLEIterator {
	return RLEIterator{encoding: encoding, curr: 0, i: 0}
}

func (this *RLEIterator) Next(n int) int {
	for this.i < len(this.encoding) {
		if this.curr+n > this.encoding[this.i] {
			n -= this.encoding[this.i] - this.curr
			this.curr = 0
			this.i += 2
		} else {
			this.curr += n
			return this.encoding[this.i+1]
		}
	}
	return -1
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * obj := Constructor(encoding);
 * param_1 := obj.Next(n);
 */
```

### **...**

```

```

<!-- tabs:end -->
