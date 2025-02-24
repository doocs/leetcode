---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/README_EN.md
rating: 1418
source: Weekly Contest 215 Q1
tags:
    - Design
    - Array
    - Hash Table
    - Data Stream
---

<!-- problem:start -->

# [1656. Design an Ordered Stream](https://leetcode.com/problems/design-an-ordered-stream)

[中文文档](/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/README.md)

## Description

<!-- description:start -->

<p>There is a stream of <code>n</code> <code>(idKey, value)</code> pairs arriving in an <strong>arbitrary</strong> order, where <code>idKey</code> is an integer between <code>1</code> and <code>n</code> and <code>value</code> is a string. No two pairs have the same <code>id</code>.</p>

<p>Design a stream that returns the values in <strong>increasing order of their IDs</strong> by returning a <strong>chunk</strong> (list) of values after each insertion. The concatenation of all the <strong>chunks</strong> should result in a list of the sorted values.</p>

<p>Implement the <code>OrderedStream</code> class:</p>

<ul>
	<li><code>OrderedStream(int n)</code> Constructs the stream to take <code>n</code> values.</li>
	<li><code>String[] insert(int idKey, String value)</code> Inserts the pair <code>(idKey, value)</code> into the stream, then returns the <strong>largest possible chunk</strong> of currently inserted values that appear next in the order.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/images/q1.gif" style="width: 682px; height: 240px;" /></strong></p>

<pre>
<strong>Input</strong>
[&quot;OrderedStream&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;insert&quot;]
[[5], [3, &quot;ccccc&quot;], [1, &quot;aaaaa&quot;], [2, &quot;bbbbb&quot;], [5, &quot;eeeee&quot;], [4, &quot;ddddd&quot;]]
<strong>Output</strong>
[null, [], [&quot;aaaaa&quot;], [&quot;bbbbb&quot;, &quot;ccccc&quot;], [], [&quot;ddddd&quot;, &quot;eeeee&quot;]]

<strong>Explanation</strong>
// Note that the values ordered by ID is [&quot;aaaaa&quot;, &quot;bbbbb&quot;, &quot;ccccc&quot;, &quot;ddddd&quot;, &quot;eeeee&quot;].
OrderedStream os = new OrderedStream(5);
os.insert(3, &quot;ccccc&quot;); // Inserts (3, &quot;ccccc&quot;), returns [].
os.insert(1, &quot;aaaaa&quot;); // Inserts (1, &quot;aaaaa&quot;), returns [&quot;aaaaa&quot;].
os.insert(2, &quot;bbbbb&quot;); // Inserts (2, &quot;bbbbb&quot;), returns [&quot;bbbbb&quot;, &quot;ccccc&quot;].
os.insert(5, &quot;eeeee&quot;); // Inserts (5, &quot;eeeee&quot;), returns [].
os.insert(4, &quot;ddddd&quot;); // Inserts (4, &quot;ddddd&quot;), returns [&quot;ddddd&quot;, &quot;eeeee&quot;].
// Concatentating all the chunks returned:
// [] + [&quot;aaaaa&quot;] + [&quot;bbbbb&quot;, &quot;ccccc&quot;] + [] + [&quot;ddddd&quot;, &quot;eeeee&quot;] = [&quot;aaaaa&quot;, &quot;bbbbb&quot;, &quot;ccccc&quot;, &quot;ddddd&quot;, &quot;eeeee&quot;]
// The resulting order is the same as the order above.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= id &lt;= n</code></li>
	<li><code>value.length == 5</code></li>
	<li><code>value</code>&nbsp;consists only of lowercase letters.</li>
	<li>Each call to <code>insert</code>&nbsp;will have a unique <code>id.</code></li>
	<li>Exactly <code>n</code> calls will be made to <code>insert</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Array Simulation

We can use an array $\textit{data}$ of length $n + 1$ to simulate this stream, where $\textit{data}[i]$ represents the value of $\textit{id} = i$. At the same time, we use a pointer $\textit{ptr}$ to represent the current position. Initially, $\textit{ptr} = 1$.

When inserting a new $(\textit{idKey}, \textit{value})$ pair, we update $\textit{data}[\textit{idKey}]$ to $\textit{value}$. Then, starting from $\textit{ptr}$, we sequentially add $\textit{data}[\textit{ptr}]$ to the answer until $\textit{data}[\textit{ptr}]$ is empty.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the data stream.

<!-- tabs:start -->

#### Python3

```python
class OrderedStream:

    def __init__(self, n: int):
        self.ptr = 1
        self.data = [None] * (n + 1)

    def insert(self, idKey: int, value: str) -> List[str]:
        self.data[idKey] = value
        ans = []
        while self.ptr < len(self.data) and self.data[self.ptr]:
            ans.append(self.data[self.ptr])
            self.ptr += 1
        return ans


# Your OrderedStream object will be instantiated and called as such:
# obj = OrderedStream(n)
# param_1 = obj.insert(idKey,value)
```

#### Java

```java
class OrderedStream {
    private int ptr = 1;
    private String[] data;

    public OrderedStream(int n) {
        data = new String[n + 1];
    }

    public List<String> insert(int idKey, String value) {
        data[idKey] = value;
        List<String> ans = new ArrayList<>();
        while (ptr < data.length && data[ptr] != null) {
            ans.add(data[ptr++]);
        }
        return ans;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */
```

#### C++

```cpp
class OrderedStream {
public:
    OrderedStream(int n) {
        ptr = 1;
        data = vector<string>(n + 1);
    }

    vector<string> insert(int idKey, string value) {
        data[idKey] = value;
        vector<string> ans;
        while (ptr < data.size() && !data[ptr].empty()) {
            ans.push_back(data[ptr++]);
        }
        return ans;
    }

private:
    int ptr;
    vector<string> data;
};

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream* obj = new OrderedStream(n);
 * vector<string> param_1 = obj->insert(idKey,value);
 */
```

#### Go

```go
type OrderedStream struct {
	ptr  int
	data []string
}

func Constructor(n int) OrderedStream {
	return OrderedStream{
		ptr:  1,
		data: make([]string, n+1),
	}
}

func (this *OrderedStream) Insert(idKey int, value string) []string {
	this.data[idKey] = value
	var ans []string
	for this.ptr < len(this.data) && this.data[this.ptr] != "" {
		ans = append(ans, this.data[this.ptr])
		this.ptr++
	}
	return ans
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Insert(idKey,value);
 */
```

#### TypeScript

```ts
class OrderedStream {
    private ptr: number;
    private data: string[];

    constructor(n: number) {
        this.ptr = 1;
        this.data = Array(n + 1);
    }

    insert(idKey: number, value: string): string[] {
        this.data[idKey] = value;
        const ans: string[] = [];
        while (this.data[this.ptr]) {
            ans.push(this.data[this.ptr++]);
        }
        return ans;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * var obj = new OrderedStream(n)
 * var param_1 = obj.insert(idKey,value)
 */
```

#### Rust

```rust
struct OrderedStream {
    ptr: usize,
    data: Vec<Option<String>>,
}

impl OrderedStream {
    fn new(n: i32) -> Self {
        OrderedStream {
            ptr: 1,
            data: vec![None; (n + 1) as usize],
        }
    }

    fn insert(&mut self, id_key: i32, value: String) -> Vec<String> {
        self.data[id_key as usize] = Some(value);
        let mut ans = Vec::new();
        while self.ptr < self.data.len() && self.data[self.ptr].is_some() {
            ans.push(self.data[self.ptr].take().unwrap());
            self.ptr += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
